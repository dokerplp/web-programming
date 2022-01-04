package dokerplp.model.util;

import dokerplp.model.entity.ResponseEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.ejb.Singleton;
import javax.ejb.Stateful;

@Stateful
public class ResponseSessionFactoryUtil {
    private SessionFactory sf;

    public SessionFactory getSessionFactory() {
        if (sf == null) {
            Configuration configuration = new Configuration().addAnnotatedClass(ResponseEntity.class).configure();
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sf = configuration.buildSessionFactory(sr);
        }
        return sf;
    }
}
