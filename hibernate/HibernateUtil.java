import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 
 * @deprecated
 * @author tonykuo
 *
 */
public class HibernateUtil {

    public static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * 
     * @return
     * @throws HibernateException
     */
    private static SessionFactory buildSessionFactory()
            throws HibernateException {
        Configuration configuration = new Configuration();
        // configuration.configure(new
        // File("src/main/resources/hibernate.cfg.xml"));
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
