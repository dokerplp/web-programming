package dokerplp.model.util;

import dokerplp.model.entity.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.ejb.Singleton;
import javax.ejb.Stateful;

@Stateful
public class UserHibernateSessionFactoryUtil {

    private SessionFactory sf;

    public SessionFactory getSessionFactory() {
        if (sf == null) {
            Configuration configuration = new Configuration().addAnnotatedClass(UserEntity.class).configure();
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sf = configuration.buildSessionFactory(sr);
        }
        return sf;
    }
}
