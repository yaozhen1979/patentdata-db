package com.uxb2b.anz02.service;

import java.util.Date;

import org.hibernate.Session;

import com.uxb2b.anz02.helper.SequenceHelper;
import com.uxb2b.anz02.model.DecApply;
import com.uxb2b.anz02.model.DecApplySum;
import com.uxb2b.anz02.model.DecApplySumList;

public class DecApplySumListService extends BaseService {

    public void save(final DecApplySumList decApplySumList) {
        execTransaction(new HibernateTemplate() {
            @Override
            public void doTransaction(Session session) {
                session.persist(decApplySumList);
            }
        });
    }

    public void update(final DecApplySumList decApplySumList) {
        execTransaction(new HibernateTemplate() {
            @Override
            public void doTransaction(Session session) {
                session.merge(decApplySumList);
            }
        });
    }

    public void saveTransLog(DecApply decApply, DecApplySum decApplySum) {
        // 新增【交易累計明細檔】
        DecApplySumList decApplySumList = new DecApplySumList();
        decApplySumList.setDecApplySumListId(SequenceHelper
                .getNextDecApplySumListSerialId());
        decApplySumList.setDecApplyId(decApply.getDecApplyId());
        decApplySumList.setDecApplySumId(decApplySum.getDecApplySumId());
        decApplySumList.setStatus(1);

        // TODO:待確定相關金額 ???
        decApplySumList.setAmountTwd(decApply.getAmountTwd());
        decApplySumList.setAmountUsd(decApply.getAmountUsd());

        decApplySumList.setCreateTime(new Date());
        
        this.save(decApplySumList);
    }

}
