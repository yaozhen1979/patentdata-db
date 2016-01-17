import static org.junit.Assert.*;

import org.hibernate.Session
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.patentdata.HibernateUtil;
import com.patentdata.model.Books


public class HibernateTester {

    SessionFactory sessionFactory

    @Before
    public void setUp() throws Exception {
        sessionFactory = HibernateUtil.getSessionFactory()
    }

    @After
    public void tearDown() throws Exception {
        HibernateUtil.shutdown()
    }

    @Test
    public void testHibernateQuery() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Books" ).list();
        for ( Books book : (List<Books>) result ) {
            println "Book = ${book.title}"
        }
        session.getTransaction().commit();
    }
}
