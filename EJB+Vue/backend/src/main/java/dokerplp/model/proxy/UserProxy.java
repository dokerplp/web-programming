package dokerplp.model.proxy;

import dokerplp.model.dao.UserDao;
import dokerplp.model.entity.UserEntity;
import dokerplp.model.service.UserService;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import java.util.List;

@Stateless
@LocalBean
public class UserProxy implements UserService {

    @EJB
    private UserDao dao;

    @Override
    public void save(UserEntity bean) {
       dao.save(bean);
    }

    @Override
    public UserEntity findByLogin(String login) {
        return dao.findByLogin(login);
    }

    @Override
    public List<UserEntity> findAll(){
        return dao.findAll();
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
