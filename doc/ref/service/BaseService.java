package com.uxb2b.anz02.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxb2b.hibernate.session.SessionUtil;

public class BaseService {

    protected Logger log;
    
    public BaseService() {
        if (log == null) {
            log = LoggerFactory.getLogger(this.getClass());
        }
    }
    
    public void execTransaction(HibernateTemplate tmp) {
        Session session = SessionUtil.getInstance().getSession();
        Transaction trans = session.beginTransaction();
        try {
            trans.begin();
            tmp.doTransaction(session);
            session.flush();
            trans.commit();
        } catch (RuntimeException e) {
            log.error("exception:" + e, e);
            trans.rollback();
            throw e;
        } finally {
            session.clear();
        }
    }
    
}
