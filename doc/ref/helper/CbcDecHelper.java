package com.uxb2b.anz02.helper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxb2b.anz02.model.CbcDec;
import com.uxb2b.hibernate.session.QueryHelper;
import com.uxb2b.hibernate.session.SessionUtil;

public class CbcDecHelper {

    protected static Logger log = LoggerFactory.getLogger(CbcDecHelper.class);

    public static CbcDec findByPrimaryKey(Integer id) {
        SessionUtil session = SessionUtil.getInstance();
        CbcDec cdcDec = (CbcDec) session.getSession().get(CbcDec.class, id);
        return cdcDec;
    }
    
    public static List<CbcDec> findByStatus(int status) {
        
        QueryHelper helper = new QueryHelper("CbcDec");
        helper.eq("status", status);

        List list = helper.query();
        return list;
    }
}
