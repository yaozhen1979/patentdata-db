package com.uxb2b.anz02.service;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;

import com.uxb2b.anz02.model.DecApply;

public class DecApplyService extends BaseService {

    public void save(final DecApply decApply) {
        execTransaction(new HibernateTemplate() {
            @Override
            public void doTransaction(Session session) {
                session.persist(decApply);
            }
        });
    }

    public void update(final DecApply decApply) {
        execTransaction(new HibernateTemplate() {
            @Override
            public void doTransaction(Session session) {
                session.merge(decApply);
            }
        });
    }

    /**
     * TODO:TEST:HOW TO ???
     * 
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DecApply decApply = new DecApply();
        decApply.setAmountTwd(new BigDecimal("0"));
        decApply.setAmountUsd(new BigDecimal("0"));
        decApply.setApplyNo("12345");
        decApply.setApproveDocFlag("0");
        decApply.setBizType("O");
        // decApply.setCancelDecApply(null);
        decApply.setCbcDecId(new Integer(12345));
        // decApply.setCbcDecLists(null);
        decApply.setCbcSerialNo("1234567");
        decApply.setCheckDeclarantId("1");
        decApply.setCompetentAuthority("設立登記主管機關");
        decApply.setCreaterId("tony");
        decApply.setCreaterName("tony");
        decApply.setCreateTime(new Date());
        decApply.setCsrDoc(null);
        decApply.setCsrFileFlag(0);
        decApply.setDataSource(0);
        decApply.setDecApplyId(1);
        // decApply.setDecApplySumLists(null);
        decApply.setDeclarant("test");
        decApply.setDeclarantAddr("address");
        decApply.setDeclarantBirth(new Date());
        decApply.setDeclarantCountry("TW");
        decApply.setDeclarantIdDate(new Date());
        decApply.setDeclarantIdNo("12345");
        decApply.setDeclarantIdValidDate(new Date());
        decApply.setDeclarantionDocFlag(0);
        decApply.setDeclarantType("1");
        decApply.setDeclareCurrency("TWD");
        decApply.setExchangeRate(new BigDecimal(1));
        // decApply.setManualApplies(null);
        decApply.setManualReportingFlag(0);
        decApply.setMaxAmountFlag(0);
        decApply.setReceiverCountry("TW");
        decApply.setRegistrationNo("12345");
        decApply.setRemittanceAmt(new BigDecimal(0));
        decApply.setReportDate(new Date());
        decApply.setSendCbcDs("M");
        decApply.setStatus(0);
        decApply.setSubTypeFlag(0);
        decApply.setTranSubType("1");
        decApply.setTranType("700");
        
        // save
        new DecApplyService().save(decApply);
    }

}
