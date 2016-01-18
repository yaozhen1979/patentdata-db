package com.patentdata.util;

import org.hibernate.HibernateException;

public interface HibernateCallback {
    
    Object execute() throws HibernateException;
    
}
