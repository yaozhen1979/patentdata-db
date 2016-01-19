package com.uxb2b.anz02.service;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;

import com.uxb2b.anz02.Constant;
import com.uxb2b.anz02.helper.DecApplySumHelper;
import com.uxb2b.anz02.helper.SequenceHelper;
import com.uxb2b.anz02.model.DecApply;
import com.uxb2b.anz02.model.DecApplySum;
import com.uxb2b.anz02.util.DateUtils;

public class DecApplySumService extends BaseService {

    private static final int OVER_TOTAL_AMOUNT = 1;
    private static final int UNDER_AMOUNT = -1;

    /**
     * 
     * @param decApply
     */
    public boolean totalAmountofTrans(DecApply decApply) throws Exception {

        /*
         *  提供傳入值:申請書編號 依申請書編號取得【申請書主檔】資料
         * 
         * @本筆交易金額=【申請書主檔】.等值新台幣金額  依申請書申請義務人ID,申請額度日期,業務別查詢【交易累計檔】資料  更新資料庫：
         */
        DecApplySum decApplySum = DecApplySumHelper.findByCondition(decApply);

        // TODO:取消累計交易申請:如何知道是取消交易 ???

        if (decApplySum == null) {

            decApplySum = new DecApplySum();
            decApplySum.setDecApplySumId(SequenceHelper
                    .getNextDecApplySumSerialId());
            decApplySum.setDeclarantIdNo(decApply.getDeclarantIdNo());
            decApplySum.setReportDate(decApply.getReportDate());
            decApplySum.setBizType(decApply.getBizType());

            // TODO: @本筆交易金額 ???
            decApplySum.setAmountTwd(decApply.getAmountTwd());
            // TODO:【申請書主檔】.等值新台幣金額 ???
            decApplySum.setAmountUsd(decApply.getAmountUsd());

            if (decApply.getAmountTwd().compareTo(new BigDecimal(500000)) == UNDER_AMOUNT) {
                decApplySum.setNeedDecDocFlag(Constant.IS_SYSTEM_NO);
            } else {
                decApplySum.setNeedDecDocFlag(Constant.IS_SYSTEM_YES);
            }

            decApplySum.setDecDocFinishFlag(Constant.IS_UNFINISH);

            if (decApply.getAmountTwd().compareTo(new BigDecimal(400000)) == UNDER_AMOUNT) {
                decApplySum.setInAddReportFlag(Constant.IS_SYSTEM_NO);
            } else {
                decApplySum.setInAddReportFlag(Constant.IS_SYSTEM_YES);
            }

            decApplySum.setDeclarantType(decApply.getDeclarantType());
            decApplySum.setDeclarant(decApply.getDeclarant());

            this.save(decApplySum);

            new DecApplySumListService().saveTransLog(decApply, decApplySum);

        } else {

            log.info("更新【交易累計檔】");

            // TODO: @本筆交易金額 ???
            BigDecimal currentAmountTwd = decApplySum.getAmountTwd();
            decApplySum.setAmountTwd(currentAmountTwd.add(decApply
                    .getAmountTwd()));
            // TODO:【申請書主檔】.等值新台幣金額 ???
            BigDecimal currentAmountUsd = decApplySum.getAmountUsd();
            decApplySum.setAmountUsd(currentAmountUsd.add(decApply
                    .getAmountUsd()));

            if (decApply.getAmountTwd().compareTo(new BigDecimal(500000)) == UNDER_AMOUNT) {
                decApplySum.setNeedDecDocFlag(Constant.IS_SYSTEM_NO);
            } else {
                decApplySum.setNeedDecDocFlag(Constant.IS_SYSTEM_YES);
            }

            decApplySum.setDecDocFinishFlag(Constant.IS_UNFINISH);

            if (decApply.getAmountTwd().compareTo(new BigDecimal(400000)) == UNDER_AMOUNT) {
                decApplySum.setInAddReportFlag(Constant.IS_SYSTEM_NO);
            } else {
                decApplySum.setInAddReportFlag(Constant.IS_SYSTEM_YES);
            }

            this.update(decApplySum);

            new DecApplySumListService().saveTransLog(decApply, decApplySum);

        }

        /*
         * TODO:check 檢核累積交易金額是否超過當日限額
         */
        boolean overLimitAmount = isOverLimitAmount(decApply, decApplySum);

        return overLimitAmount;
    }

    /**
     * 當日限額之規則：
     * 依申報義務人身分不同檢核規則如下，需回傳true(表超過當日限額或為單筆大額申報)或false(表未超過當日限額或不為單筆大額申報：
     * "身分別"為公司行號(1)時，當日限額為100萬美金。 "身分別"為團體(2)時，當日限額為50萬美金。 "身分別"為我國國民(3)時
     * 已滿20歲，當日限額為50萬美金。 未滿20歲，當日限額為50萬台幣。 "身分別"為持居留證者(4)時，當日限額為50萬台幣或10萬美金。
     * 
     * @param decApply
     * @param decApplySum
     */
    public boolean isOverLimitAmount(DecApply decApply, DecApplySum decApplySum) {

        boolean overLimitAmount = false;

        if (decApply.getDeclarant().equals("1")) {
            if (decApplySum.getAmountUsd().compareTo(new BigDecimal(1000000)) == OVER_TOTAL_AMOUNT) {
                overLimitAmount = true;
            }
        } else if (decApply.getDeclarant().equals("2")) {
            if (decApplySum.getAmountUsd().compareTo(new BigDecimal(500000)) == OVER_TOTAL_AMOUNT) {
                overLimitAmount = true;
            }
        } else if (decApply.getDeclarant().equals("3")) {
            int compareBirth = DateUtils.compareDateYear(new Date(),
                    decApply.getDeclarantBirth(), 20);
            if (compareBirth == 1) {
                if (decApplySum.getAmountUsd()
                        .compareTo(new BigDecimal(500000)) == OVER_TOTAL_AMOUNT) {
                    overLimitAmount = true;
                }
            } else {
                if (decApplySum.getAmountTwd()
                        .compareTo(new BigDecimal(500000)) == OVER_TOTAL_AMOUNT) {
                    overLimitAmount = true;
                }
            }
        } else if (decApply.getDeclarant().equals("4")) {
            if (decApplySum.getAmountUsd().compareTo(new BigDecimal(100000)) == OVER_TOTAL_AMOUNT
                    || decApplySum.getAmountTwd().compareTo(
                            new BigDecimal(500000)) == OVER_TOTAL_AMOUNT) {
                overLimitAmount = true;
            }
        }
        return overLimitAmount;
    }

    public void save(final DecApplySum decApplySum) {
        execTransaction(new HibernateTemplate() {
            @Override
            public void doTransaction(Session session) {
                session.persist(decApplySum);
            }
        });
    }

    public void update(final DecApplySum decApplySum) {
        execTransaction(new HibernateTemplate() {
            @Override
            public void doTransaction(Session session) {
                session.merge(decApplySum);
            }
        });
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
