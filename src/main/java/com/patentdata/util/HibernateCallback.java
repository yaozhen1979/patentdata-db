package com.patentdata.util;

import org.hibernate.HibernateException;

public interface HibernateCallback {
    
    public void execute() throws HibernateException;
    
}
