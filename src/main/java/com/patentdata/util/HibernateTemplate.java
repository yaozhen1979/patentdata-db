package com.patentdata.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateTemplate {
    
    public void run(HibernateCallback callback) {
        
        Session session = null;
        Transaction tx = null;
        
        try {
            session = HibernateUtil.currentSession();
            tx = session.beginTransaction();
            // Object result = callback.execute();
            callback.execute();
            tx.commit();
            session.flush();
        } catch (HibernateException e) {
            try {
                tx.rollback();
            } catch (Throwable T) {
            }
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.closeSession();
        }
        
    }
    
}
