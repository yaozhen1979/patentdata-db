package com.patentdata.dao;

import java.util.List;

import com.patentdata.common.hibernate.QueryBuilder;
import com.patentdata.model.Books;

public class BooksDAO extends GenericDAOImpl<Books> {
    
    @SuppressWarnings("unchecked")
    public List<Books> queryCondition(final String title) {
        // String hql = "select DISTINCT p from Patent p, TreeNodePatent tnp where p.id = tnp.patentId";
        // Session session = HibernateUtil.currentSession();
        QueryBuilder queryBuilder = new QueryBuilder("from Books b");
        queryBuilder.eq("b.title", title);
        
        return queryBuilder.query();
    }
    
}
