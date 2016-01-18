import static org.junit.Assert.*;

import org.hibernate.Session
import org.hibernate.SessionFactory;
import org.hibernate.Transaction
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.patentdata.util.HibernateUtil;
import com.patentdata.model.Books


public class HibernateTester {

    Session session

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.currentSession();
    }

    @After
    public void tearDown() throws Exception {
        session = HibernateUtil.closeSession();
    }

    @Test
    public void testHibernateQuery() {

        session.beginTransaction();
        List result = session.createQuery( "from Books b where b.title = 'Programming Robots with ROS'" ).list();
        for ( Books book : (List<Books>) result ) {
            println "Book = ${book.title}"
        }
        session.getTransaction().commit();
    }
}
