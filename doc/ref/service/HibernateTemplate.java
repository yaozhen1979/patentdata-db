package com.uxb2b.anz02.service;

import org.hibernate.Session;

public interface HibernateTemplate {

    void doTransaction(Session session);
    
}
