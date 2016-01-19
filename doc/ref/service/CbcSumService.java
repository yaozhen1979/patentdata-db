package com.uxb2b.anz02.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxb2b.anz02.helper.ConfigHelper;
import com.uxb2b.anz02.helper.DecApplySumHelper;
import com.uxb2b.anz02.helper.SequenceHelper;
import com.uxb2b.anz02.model.DecApply;
import com.uxb2b.anz02.model.DecApplySum;
import com.uxb2b.anz02.vo.CbcSumVO;

public class CbcSumService extends BaseService {

    private static Logger log = LoggerFactory.getLogger(CbcSumService.class);

    private static final int OVER_CBC_SEND_TIME = 1;

    /*
     * TODO:額度累計作業
     */
    public CbcSumVO totalAmountOfApply(DecApply decApply) throws Exception {

        String cbcLastSendTime = ConfigHelper.getCbcLastSendTime();
        String openMApply = ConfigHelper.getOpenMApply();

        CbcSumVO cbcSumVO = null;

        // 1.1
        if (compareCbcSendTime(cbcLastSendTime) != OVER_CBC_SEND_TIME) {
            log.info("額度累計作業開始");
            DecApplySum decApplySum = DecApplySumHelper
                    .findByCondition(decApply);

            int compareAmountTwd = decApplySum.getAmountTwd().compareTo(
                    new BigDecimal(500000));
            // TODO:1.1.1額度累計超過台幣50萬
            if (compareAmountTwd == 1) {
                log.info("額度累計超過台幣50萬");
                if (StringUtils.equals(decApply.getSendCbcDs(), "W")
                        || (StringUtils.equals(decApply.getSendCbcDs(), "M") && StringUtils
                                .equals(openMApply, "YES"))) {
                    // TODO:1.1.1.1
                    log.info("資料來源=W,資料來源=M && 媒體申報=YES");
                    String cbcSerialNo = getCbcSerialNo(SequenceHelper
                            .getNextCbcSerialNo());

                } else if (StringUtils.equals(decApply.getSendCbcDs(), "M")
                        && StringUtils.equals(openMApply, "NO")) {
                    // TODO:1.1.1.2
                    log.info("資料來源=M,媒體申報=NO");
                }

            } else {
                // TODO:1.1.2額度累計未超過台幣50萬
                log.info("額度累計未超過台幣50萬");
            }

        } else {
            // 1.2
            log.info("已超過系統設定每日送央行最晚時間");
            decApply.setStatus(DecApply.TOTAL_AMOUNT_APPLY_FAILED);
            new DecApplyService().update(decApply);
            cbcSumVO = new CbcSumVO(CbcSumVO.OVER_CBC_APPLY_TIME, "超過CBC申報時間");
        }

        return cbcSumVO;

    }

    /**
     * CBC_DEC 中 CBC_SERIAL_NO 長度為7碼, 編碼規則為 "西年元之尾碼" + "日(365)" + "每日重新產生之流水編碼"
     * EX: 33110001
     * @throws Exception 
     */
    public String getCbcSerialNo(String nextCbcSerialNo) throws RuntimeException {
        
        if (StringUtils.isBlank(nextCbcSerialNo)) {
            throw new RuntimeException("未能取得央行主檔收件編號.");
        }
        
        String lastYearNum = String.valueOf(
                Calendar.getInstance().get(Calendar.YEAR)).substring(3);
        String dayOfYear = String.valueOf(Calendar.getInstance().get(
                Calendar.DAY_OF_YEAR));
        String cbcSeqNo = StringUtils.leftPad(nextCbcSerialNo, 3, "0");
        
        String cbcSerialNO = lastYearNum + dayOfYear + cbcSeqNo;
        log.debug("新收件編號:" + cbcSerialNO);
        
        return cbcSerialNO;
    }

    /**
     * 判斷:系統設定每日送央行最晚時間
     * 
     * @return
     * @throws NumberFormatException
     */
    public int compareCbcSendTime(String cbcLastSendTime)
            throws NumberFormatException {

        if (StringUtils.isBlank(cbcLastSendTime)) {
            log.info("未設定每日送央行最晚時間");
            return OVER_CBC_SEND_TIME;
        }

        Date nowTime = new Date();
        String[] time = cbcLastSendTime.split(":");
        int cbcHour = Integer.parseInt(time[0]);
        int cbcMiniute = Integer.parseInt(time[1]);
        Calendar cbcDate = Calendar.getInstance();
        cbcDate.set(cbcDate.get(Calendar.YEAR), cbcDate.get(Calendar.MONTH),
                cbcDate.get(Calendar.DAY_OF_MONTH), cbcHour, cbcMiniute, 0);

        log.info("Send Time:" + nowTime);
        log.info("CBC Time:" + cbcDate.getTime());

        return nowTime.compareTo(cbcDate.getTime());
    }

}
