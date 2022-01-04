package dokerplp.jsf.proxy;

import dokerplp.jsf.bean.ResponseBean;
import dokerplp.jsf.dao.ResponseDao;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public class ResponseService {
    private final ResponseDao responseDao = new ResponseDao();

    public List<ResponseBean> selectAll() {
        try {
            return responseDao.findAll();
        } catch (ServiceException e){
            return null;
        }
    }

    public void deleteAll() {
        try {
            responseDao.delete();
        } catch (ServiceException ignored){}
    }

    public void insert(ResponseBean bean){
        try {
            responseDao.save(bean);
        } catch (ServiceException ignored){}
    }
}
