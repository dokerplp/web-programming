package dokerplp.jsf.util;

import dokerplp.jsf.bean.ResponseBean;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().addAnnotatedClass(ResponseBean.class).configure();
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(sr);
        }
        return sessionFactory;
    }
}
