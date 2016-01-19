package com.uxb2b.anz02.helper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.uxb2b.anz02.model.DecApply;
import com.uxb2b.hibernate.session.QueryHelper;
import com.uxb2b.hibernate.session.SessionUtil;

public class DecApplyHelper {

    private static Logger log = LoggerFactory.getLogger(DecApplyHelper.class);

    /**
     * find by primary key
     */
    public static DecApply findByPrimaryKey(Integer id) {
        SessionUtil session = SessionUtil.getInstance();
        DecApply decApply = (DecApply) session.getSession().get(DecApply.class,
                id);
        return decApply;
    }
    
    @SuppressWarnings("rawtypes")
    public static DecApply findByApplyNo(String applyNo) {
        
        QueryHelper helper = new QueryHelper("DecApply d");
        //
        log.info("applyNo:" + applyNo);
        helper.eq("applyNo", applyNo);

        List list = helper.query();
        if (list.size() != 0) {
            return (DecApply) list.get(0);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DecApply decApply = DecApplyHelper.findByPrimaryKey(1);
        log.info("DecApply INFO:" + decApply);
    }

}
