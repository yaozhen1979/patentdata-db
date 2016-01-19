package com.patentdata.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.patentdata.util.HibernateCallback;
import com.patentdata.util.HibernateTemplate;
import com.patentdata.util.HibernateUtil;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
    
    private final Session session = HibernateUtil.currentSession();;
    
    @Override
    public void add(final T t) {
        new HibernateTemplate().run(new HibernateCallback() {
            @Override
            public void execute() throws HibernateException {
                session.persist(t);
            }
        });
    }
    
    @Override
    public void update(final T t) {
        new HibernateTemplate().run(new HibernateCallback() {
            @Override
            public void execute() throws HibernateException {
                session.update(t);
            }
        });
    }

    @Override
    public void remove(final T t) {
        new HibernateTemplate().run(new HibernateCallback() {
            @Override
            public void execute() throws HibernateException {
                session.delete(t);
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(Class<T> clazz, Object id) {
        T t = (T) session.load(clazz, (Serializable) id);
        return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> queryAll(Class<T> clazz) {
        return session.createCriteria(clazz).list();
    }
}
