package com.patentdata.dao;

import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.patentdata.model.Books;
import com.patentdata.util.HibernateUtil;

public class BooksDAOTester {

    Session session;

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.currentSession();
    }

    @After
    public void tearDown() throws Exception {
        HibernateUtil.closeSession();
    }
    
    @Ignore
    @Test
    public void testAdd() {
        Books book = new Books();
        book.setIsbn("1234567890123");
        book.setTitle("tony test");
        book.setSubTitle("tony sub title");
        new BooksDAO().add(book);
    }
    
    @Ignore
    @Test
    public void testUpdate() {
        Books book = new Books();
        book.setIsbn("1234567890123");
        book.setTitle("tony test update");
        book.setSubTitle("tony sub title update");
        new BooksDAO().update(book);
    }
    
    @Ignore
    @Test
    public void testRemove() {
        Books book = new Books();
        book.setIsbn("1234567890123");
        new BooksDAO().remove(book);
    }
    
    @Test
    public void testFindById() {
        Books book = new BooksDAO().findById(Books.class, "9781449323899");
        System.out.println(book.getTitle());
    }
    
    @Test
    public void testQueryAll() {
        System.out.println(new BooksDAO().queryAll(Books.class).get(0).getIsbn());
    }
    
    @Test
    public void testQueryCondition() {
        // "Programming Robots with ROS"
        String title = "Programming Robots with ROS";
        System.out.println(new BooksDAO().queryCondition(title).get(0).getTitle());
    }
    
    @Ignore
    @Test
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void testHibernateQuery() {

        session.beginTransaction();
        List result = session.createQuery( "from Books b where b.title = 'Programming Robots with ROS'" ).list();
        for ( Books book : (List<Books>) result ) {
            System.out.println("Book = " + book.getTitle());
        }
        session.getTransaction().commit();
    }

}
