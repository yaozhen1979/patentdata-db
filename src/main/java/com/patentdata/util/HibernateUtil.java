package com.patentdata.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 
 * 
 * @author tonykuo
 *
 */
public class HibernateUtil {
    
    public static final SessionFactory sessionFactory;
    
    public static final ThreadLocal<Session> session = new ThreadLocal<Session>();
    
    static {
        
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            
            // setting properties programmatically
            // configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "none");
            
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException ex) {
            throw new RuntimeException("Configuratio error = " + ex.getMessage(), ex);
        }
        
    }
    
    public static Session currentSession() throws HibernateException {
        Session s = (Session) session.get();
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }
    
    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        session.set(null);
        if (s != null) {
            s.close();
        }
    }
    
}
