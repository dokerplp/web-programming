package dokerplp.model.service;

import dokerplp.model.entity.UserEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserService {
    void save(UserEntity bean);

    UserEntity findByLogin(String login);

    List<UserEntity> findAll();

    void deleteAll();
}
