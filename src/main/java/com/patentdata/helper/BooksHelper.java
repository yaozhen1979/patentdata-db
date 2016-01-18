package com.patentdata.helper;

import org.hibernate.HibernateException;

import com.patentdata.model.Books;
import com.patentdata.util.HibernateCallback;
import com.patentdata.util.HibernateTemplate;

public class BooksHelper {
    
    public void saveBook(final Books book) {
        
        new HibernateTemplate().run(new HibernateCallback() {
            @Override
            public Object execute() throws HibernateException {
                // TODO Auto-generated method stub
                return null;
            }
        });
        
    }
    
}
