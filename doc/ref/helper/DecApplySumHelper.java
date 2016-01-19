package com.uxb2b.anz02.helper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxb2b.anz02.model.DecApply;
import com.uxb2b.anz02.model.DecApplySum;
import com.uxb2b.hibernate.session.QueryHelper;

public class DecApplySumHelper {

    private static Logger log = LoggerFactory
            .getLogger(DecApplySumHelper.class);

    /**
     * find by primary key
     */
    @SuppressWarnings("rawtypes")
    public static DecApplySum findByCondition(DecApply decApply) {
        //
        QueryHelper helper = new QueryHelper("DecApplySum d");
        //
        log.info("declarantIdNo:" + decApply.getDeclarantIdNo());
        log.info("reportDate:" + decApply.getReportDate());
        log.info("bizType:" + decApply.getBizType());
        //
        helper.eq("declarantIdNo", decApply.getDeclarantIdNo());
        helper.eq("reportDate", decApply.getReportDate());
        helper.eq("bizType", decApply.getBizType());

        List list = helper.query();
        if (list.size() != 0) {
            return (DecApplySum) list.get(0);
        } else {
            return null;
        }
    }

}
