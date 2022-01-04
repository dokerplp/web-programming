package dokerplp.model.proxy;

import dokerplp.model.dao.ResponseDao;
import dokerplp.model.entity.ResponseEntity;
import dokerplp.model.service.ResponseService;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class ResponseProxy implements ResponseService {

    @EJB
    private ResponseDao dao;

    @Override
    public void save(ResponseEntity bean) {
        dao.save(bean);
    }

    @Override
    public List<ResponseEntity> findAllById(Long id) {
        return dao.findAllById(id);
    }

    @Override
    public void deleteAllById(Long id) {
        dao.deleteAllById(id);
    }
}
