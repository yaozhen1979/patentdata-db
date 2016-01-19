package com.uxb2b.anz02.helper;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.uxb2b.anz02.model.SysSequence;
import com.uxb2b.hibernate.session.SessionHelper;

public class SequenceHelper {

    private static final String GLOBAL_ID = "GLOBAL_ID";
    //
    public static final String STS_LOG_SERIAL_ID = "STS_LOG_SERIAL_ID";
    public static final String DEC_APPLY_SERIAL_ID = "DEC_APPLY_SERIAL_ID";
    public static final String DEC_APPLY_SUM_SERIAL_ID = "DEC_APPLY_SUM_SERIAL_ID";
    public static final String DEC_APPLY_SUM_LIST_SERIAL_ID = "DEC_APPLY_SUM_LIST_SERIAL_ID";
    public static final String CBC_SERIAL_NO = "CBC_SERIAL_NO";
    
    public static int getNextGlobalId() {
        return findNextSeqValue(GLOBAL_ID, SysSequence.TYPE_NO_RESET);
    }
    
    public static int getNextStsLogSerialId() {
        return findNextSeqValue(STS_LOG_SERIAL_ID, SysSequence.TYPE_NO_RESET);
    }
    
    public static int getNextDecApplySerialId() {
        return findNextSeqValue(DEC_APPLY_SERIAL_ID, SysSequence.TYPE_NO_RESET);
    }
    
    public static int getNextDecApplySumSerialId() {
        return findNextSeqValue(DEC_APPLY_SUM_SERIAL_ID, SysSequence.TYPE_NO_RESET);
    }
    
    public static int getNextDecApplySumListSerialId() {
        return findNextSeqValue(DEC_APPLY_SUM_LIST_SERIAL_ID, SysSequence.TYPE_NO_RESET);
    }
    
    public static String getNextCbcSerialNo() {
        return String.valueOf(findNextSeqValue(CBC_SERIAL_NO,
                SysSequence.TYPE_DATE_RESET));
    }

    private static synchronized int findNextSeqValue(String sequenceName,
            String type) {
        Session session = SessionHelper.newSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = " from SysSequence c where c.seqName=?";
            Object obj = session.createQuery(sql).setString(0, sequenceName)
                    .setMaxResults(1).uniqueResult();
            SysSequence seq = (SysSequence) session.createQuery(sql)
                    .setString(0, sequenceName).setMaxResults(1).uniqueResult();
            if (seq == null) {
                seq = new SysSequence(sequenceName, 1);
                seq.setSeqType(type);
                seq.setLastTime(new Date());
                session.save(seq);
            } else {
                // 依 type 判斷
                Date theDate = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(theDate);
                int thisYear = cal.get(Calendar.YEAR);
                int thisMonth = cal.get(Calendar.MONTH);
                int thisDate = cal.get(Calendar.DATE);
                int lastYear = 0;
                int lastMonth = 0;
                int lastDate = 0;
                if (seq.getLastTime() != null) {
                    cal.setTime(seq.getLastTime());
                    lastYear = cal.get(Calendar.YEAR);
                    lastMonth = cal.get(Calendar.MONTH);
                    lastDate = cal.get(Calendar.DATE);
                }
                if (SysSequence.TYPE_YEAR_RESET.equals(type)) {
                    if (thisYear != lastYear) {
                        seq.setSeqValue(1);
                        seq.setLastTime(theDate);
                    } else {
                        seq.setSeqValue(seq.getSeqValue() + 1);
                    }
                } else if (SysSequence.TYPE_DATE_RESET.equals(type)) {
                    if (thisYear != lastYear || thisMonth != lastMonth
                            || thisDate != lastDate) {
                        seq.setSeqValue(1);
                        seq.setLastTime(theDate);
                    } else {
                        seq.setSeqValue(seq.getSeqValue() + 1);
                    }
                } else {
                    seq.setSeqValue(seq.getSeqValue() + 1);
                }
                session.update(seq);
            }
            tx.commit();
            return seq.getSeqValue();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
