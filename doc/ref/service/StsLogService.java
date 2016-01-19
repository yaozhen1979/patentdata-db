package com.uxb2b.anz02.service;

import org.hibernate.Session;

import com.uxb2b.anz02.model.StsLog;

public class StsLogService extends BaseService {

    public void save(final StsLog stsLog) {
        execTransaction(new HibernateTemplate() {
            @Override
            public void doTransaction(Session session) {
                session.persist(stsLog);
            }
        });
    }

    public void update(final StsLog stsLog) {
        execTransaction(new HibernateTemplate() {
            @Override
            public void doTransaction(Session session) {
                session.merge(stsLog);
            }
        });
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
