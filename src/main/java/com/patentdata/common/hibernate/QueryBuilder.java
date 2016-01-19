package com.patentdata.common.hibernate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;

import com.patentdata.util.HibernateUtil;

/**
 * 
 * @author tonykuo
 *
 */
@SuppressWarnings("rawtypes")
public class QueryBuilder {
    
    private static Pattern selectPattern = Pattern.compile("^\\s*SELECT\\s+(.+)\\s+FROM\\s+(.+)\\s*$", Pattern.CASE_INSENSITIVE);
    private static Pattern fromPattern = Pattern.compile("^\\s*FROM\\s+(.+)\\s*$", Pattern.CASE_INSENSITIVE);
    private static Pattern wherePattern = Pattern.compile("^\\s*(.+)\\s+WHERE\\s+(.+)\\s*$", Pattern.CASE_INSENSITIVE);
    private static Pattern orderPattern = Pattern.compile("^\\s*(.+)\\s+ORDER\\s+BY\\s+(.+)\\s*$", Pattern.CASE_INSENSITIVE);
    private static Pattern groupPattern = Pattern.compile("^\\s*(.+)\\s+GROUP\\s+BY\\s+(.+)\\s*$", Pattern.CASE_INSENSITIVE);
    
    private String table;

    private String select = null;

    private StringBuffer where = new StringBuffer();

    private StringBuffer order = new StringBuffer();
    
    private StringBuffer group = new StringBuffer();

    private List whereCondition = new LinkedList();

    private HashMap parameterMap = new HashMap();

    private Session session;
    
    private int pageSize = 10;
    private int page = -1;
    private int pageCount = 1;
    private int count;
    private boolean pageQuery;

    private List result;
    
    // default value = false
    private boolean isNativeSQL = false;
    
    private boolean hasOr;
    
    public QueryBuilder(CharSequence sql) {
        this(sql, new Object[0]);
    }
    
    public QueryBuilder(CharSequence sql, Object... params) {
        this(null, sql, params);
    }
    
    public QueryBuilder(Session session, CharSequence sql) {
        this(session, sql, new Object[0]);
    }

    public QueryBuilder(Session theSession, CharSequence sql, Object... params) {
        
        if (theSession == null) {
            session = HibernateUtil.currentSession();
        } else {
            session = theSession;
        }
        
        Matcher matcher = selectPattern.matcher(sql);
        
        if (matcher.matches()) {
            this.select = matcher.group(1);
            this.table = matcher.group(2);
        } else {
            matcher = fromPattern.matcher(sql);
            if (matcher.matches()) {
                this.table = matcher.group(1);
            } else {
                this.table = sql.toString();
            }
        }

        Matcher whereMatcher = wherePattern.matcher(this.table);
        if (whereMatcher.matches()) {
            this.table = whereMatcher.group(1);
            String whereStr = whereMatcher.group(2);

            Matcher groupMatcher = groupPattern.matcher(whereStr);
            if (groupMatcher.matches()) {
                whereStr = groupMatcher.group(1);
                String groupStr = groupMatcher.group(2);
                Matcher orderMatcher = orderPattern.matcher(groupStr);
                if (orderMatcher.matches()) {
                    this.group(orderMatcher.group(1));
                    this.order(orderMatcher.group(2));
                } else {
                    this.group(whereStr);
                }
            }
            
            Matcher orderMatcher = orderPattern.matcher(whereStr);
            if (orderMatcher.matches()) {
                this.and(orderMatcher.group(1));
                this.order(orderMatcher.group(2));
            } else {
                this.and(whereStr);
            }
            
        }

        addParams(params);
    }
    
    @SuppressWarnings("unchecked")
    public void addParams(Object... params) {
        for (int i = 0; i < params.length; i++) {
            if (params[i] != null && params[i].getClass().isArray()) {
                addParams((Object[]) params[i]);
            } else {
                this.whereCondition.add(params[i]);
            }
        }
    }

    public QueryBuilder setPage(int page1) {
        pageQuery = true;
        this.page = page1;
        return this;
    }

    public QueryBuilder setPageSize(int pageSize1) {
        this.pageSize = pageSize1;
        return this;
    }

    public QueryBuilder order(String propertyName) {
        order.append(",").append(propertyName);
        return this;
    }
    
    public QueryBuilder group(String propertyName) {
        group.append(",").append(propertyName);
        return this;
    }

    public QueryBuilder orderDesc(String propertyName) {
        order.append(",").append(propertyName).append(" desc");
        return this;
    }

    public String getSQL() {
        if (this.hasOr) {
            this.hasOr = false;
            this.where.append(")");
        }
        
        StringBuffer sql = new StringBuffer();
        if (select != null) {
            sql.append(" SELECT ").append(select).append(" ");
        }

        sql.append("FROM ").append(table);

        if (where.length() > 0) {
            sql.append(" WHERE ").append(where.substring(4));
        }
        if (group.length() > 0) {
            sql.append(" GROUP BY ").append(group.substring(1));
        }
        if (order.length() > 0) {
            sql.append(" ORDER BY ").append(order.substring(1));
        }
        return sql.toString();
    }

    public String getCountSQL() {
        if (this.hasOr) {
            this.hasOr = false;
            this.where.append(")");
        }
        
        StringBuffer sql = new StringBuffer();
        if (select == null || isNativeSQL()) {
            sql.append("SELECT count(*) FROM ").append(table);
        } else {
            if (select.indexOf(",") != -1) {
                sql.append("SELECT count(")
                        .append(select.substring(0, select.indexOf(",")))
                        .append(") FROM ").append(table);
            } else {
                sql.append("SELECT count(").append(select).append(") FROM ")
                        .append(table);
            }
        }
        if (where.length() > 0) {
            sql.append(" WHERE ").append(where.substring(4));
        }

        if (group.length() > 0) {
            sql.append(" GROUP BY ").append(group.substring(1));
        }
        return sql.toString();
    }

    @SuppressWarnings("unchecked")
    public QueryBuilder setParameter(String name, Object value) {
        parameterMap.put(name, value);
        return this;
    }

    private List list() {
        
        Query query = createQuery(getSQL());

        Iterator i = whereCondition.iterator();
        int index = 0;

        while (i.hasNext()) {
            query.setParameter(index++, i.next());
        }

        if (parameterMap.size() > 0) {
            i = parameterMap.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry parameter = (Map.Entry) i.next();
                query.setParameter((String) parameter.getKey(),
                        parameter.getValue());
            }
        }

        if (pageQuery) {
            query.setMaxResults(pageSize);
            if (page > 0) {
                query.setFirstResult((page - 1) * pageSize);
            }
        }
        result = query.list();
        return result;
    }
    
    private Query createQuery(String sql) {
        if (isNativeSQL()) {
            return session.createSQLQuery(sql);
        } else {
            return session.createQuery(sql);
        }
    }

    public int queryCount() {
        Query query = createQuery(getCountSQL());

        Iterator i = whereCondition.iterator();
        int index = 0;
        while (i.hasNext()) {
            query.setParameter(index++, i.next());
        }
        if (parameterMap.size() > 0) {
            i = parameterMap.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry parameter = (Map.Entry) i.next();
                query.setParameter((String) parameter.getKey(),
                        parameter.getValue());
            }
        }

        Number cnt = (Number) query.uniqueResult();
        if (cnt == null) {
            return 0;
        } else {
            return cnt.intValue();
        }
    }

    public List query() {
        if (this.hasOr) {
            this.hasOr = false;
            this.where.append(")");
        }

        if (this.pageQuery) {
            this.count = queryCount();
            if (count <= pageSize) {
                pageCount = 1;
            } else {
                pageCount = (count / pageSize)
                        + ((count % pageSize > 0) ? 1 : 0);
            }
            if (count == 0) {
                return new LinkedList();
            }
            // 若指定的頁數大於實際頁數，設為最後一頁。
            if (page > pageCount) {
                page = pageCount;
            }
        }
        
        return list();
    }
    
    public List queryPage() {
        if (this.hasOr) {
            this.hasOr = false;
            this.where.append(")");
        }
        return list();
    }

    public QueryBuilder notIn(String propertyName, Collection values) {
        return notIn(propertyName, values.toArray());
    }

    public QueryBuilder notIn(String propertyName, Object... values) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            buff.append(",?");
        }
        return this
                .and(propertyName + " not in (" + buff.toString().substring(1)
                        + ")", values);
    }

    public QueryBuilder notIn(String propertyName, String... values) {
        return notIn(propertyName, (Object[]) values);
    }
    
    public QueryBuilder eq(String propertyName, Object value) {
        return this.and(propertyName, "=", value);
    }

    public QueryBuilder allEq(Map propertyNameValues) {
        Iterator i = propertyNameValues.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry e = (Map.Entry) i.next();
            this.eq((String) e.getKey(), e.getValue());
        }
        return this;
    }

    public QueryBuilder between(String propertyName, Object lo, Object hi) {
        return this.and(propertyName + " between ? and ?", lo, hi);
    }

    public QueryBuilder in(String propertyName, Collection values) {
        return in(propertyName, values.toArray());
    }

    public QueryBuilder in(String propertyName, Object... values) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            buff.append(",?");
        }
        return this.and(propertyName + " in (" + buff.toString().substring(1)
                + ")", values);
    }

    public QueryBuilder in(String propertyName, String... values) {
        return in(propertyName, (Object[]) values);
    }

    public QueryBuilder like(String propertyName, String value) {
        return this.and(new StringBuffer(propertyName).append(" like '")
                .append(value).append("'").toString());
    }

    public QueryBuilder likeAny(String propertyName, String value) {
        return this.like(propertyName, "%" + value + "%");
    }

    public QueryBuilder and(String propertyName, String condition, Object value) {
        return this.and(propertyName + " " + condition + " ?", value);
    }

    public QueryBuilder and(String sql, Object... value) {
        if (this.hasOr) {
            where.append(") ");
            hasOr = false;
        }
        where.append(" and (").append(sql).append(")");
        addParams(value);
        return this;
    }

    public QueryBuilder or(String propertyName, String condition, Object value) {
        return this.or(propertyName + " " + condition + " ?", value);
    }

    public QueryBuilder or(String sql, Object... value) {
        if (!hasOr) {
            where.append(" and (");
            hasOr = true;
        } else {
            where.append(" or ");
        }
        where.append("(").append(sql).append(")");
        addParams(value);
        return this;
    }
    
    public QueryBuilder singleOr(String propertyName, String condition, Object value) {
        return this.singleOr(propertyName + " " + condition + " ?", value);
    }
    
    public QueryBuilder singleOr(String sql, Object... value) {
        where.append(" or (").append(sql).append(")");
        addParams(value);
        return this;
    }
    
    public int getCount() {
        return count;
    }

    public int getPage() {
        return page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List getList() {
        return result;
    }

    public boolean isNativeSQL() {
        return isNativeSQL;
    }

    public void setNativeSQL(boolean isNativeSQL) {
        this.isNativeSQL = isNativeSQL;
    }
    
}
