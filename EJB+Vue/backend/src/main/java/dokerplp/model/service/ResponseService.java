package dokerplp.model.service;

import dokerplp.model.entity.ResponseEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ResponseService {
    void save(ResponseEntity bean);

    List<ResponseEntity> findAllById(Long id);

    void deleteAllById(Long id);
}
