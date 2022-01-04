package dokerplp.model.dao;

import dokerplp.model.entity.UserEntity;
import dokerplp.model.service.UserService;
import dokerplp.model.util.UserHibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class UserDao implements UserService {
    
    @EJB
    private UserHibernateSessionFactoryUtil hsfu;

    @Override
    public void save(UserEntity bean) {
        Session session = hsfu.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(bean);
        tx1.commit();
        session.close();
    }
    @Override
    public UserEntity findByLogin(String login) {
        Session session = hsfu.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query<UserEntity> userQuery = session.createQuery("FROM UserEntity u WHERE u.login = ?1", UserEntity.class).setParameter(1, login);
        UserEntity user = userQuery.uniqueResult();
        tx1.commit();
        session.close();
        return user;
    }

    @Override
    public List<UserEntity> findAll(){
        Session session = hsfu.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<UserEntity> list = session.createQuery("FROM UserEntity ").getResultList();
        tx1.commit();
        session.close();
        return list;
    }

    @Override
    public void deleteAll() {
        Session session = hsfu.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("DELETE FROM UserEntity").executeUpdate();
        tx1.commit();
        session.close();
    }
}
