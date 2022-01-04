package dokerplp.model.dao;

import dokerplp.model.entity.ResponseEntity;
import dokerplp.model.service.ResponseService;
import dokerplp.model.util.ResponseSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class ResponseDao implements ResponseService {

    @EJB
    private ResponseSessionFactoryUtil hsfu;

    @Override
    public void save(ResponseEntity bean) {
        Session session = hsfu.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(bean);
        tx1.commit();
        session.close();
    }

    @Override
    public List<ResponseEntity> findAllById(Long id) {
        Session session = hsfu.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query<ResponseEntity> resQuery = session.createQuery("FROM ResponseEntity r WHERE r.userId = ?1", ResponseEntity.class).setParameter(1, id);
        List<ResponseEntity> res = resQuery.getResultList();
        tx1.commit();
        session.close();
        return res;
    }

    @Override
    public void deleteAllById(Long id) {
        Session session = hsfu.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("DELETE FROM ResponseEntity r WHERE r.userId = ?1").setParameter(1, id).executeUpdate();
        tx1.commit();
        session.close();
    }
}
