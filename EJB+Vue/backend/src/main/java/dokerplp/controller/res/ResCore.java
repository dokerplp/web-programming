package dokerplp.controller.res;

import dokerplp.model.entity.ResponseEntity;
import dokerplp.model.proxy.ResponseProxy;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateful
public class ResCore {

    private final Logger logger = LoggerFactory.getLogger(ResCore.class);

    @EJB
    private ResponseProxy response;

    @Getter
    private float x, y, r;

    public ResStatus init(String json){
        try {
            JSONObject object = new JSONObject(json);
            x = Float.parseFloat(object.getString("x"));
            y = Float.parseFloat(object.getString("y"));
            r = Float.parseFloat(object.getString("r"));

            return ResStatus.OK;
        } catch (JSONException e) {
            logger.error("JSON error while parsing \"{}\"", json, e);
        } catch (Exception e) {
            logger.error("Undefined error on request \"{}\"", json,  e);
        }
        return ResStatus.UNDEFINED_ERROR;
    }

    public ResStatus validate() {
        boolean y_valid = y >= -5f && y <= 5f;
        boolean r_valid = r == 1 || r == 2 || r == 3;
        if(y_valid && r_valid) return ResStatus.OK;
        return ResStatus.VALIDATION_FAILED;
    }

    public void save(long userId, long start) {
        ResponseEntity entity = new ResponseEntity(userId, x, y, r, (System.nanoTime() - start)/1000, AreaCheckUtil.isIn(x, y, r), AreaCheckUtil.left(x), AreaCheckUtil.top(y));
        response.save(entity);
    }

    public List<ResponseEntity> findAllById(Long id){
        return response.findAllById(id);
    }

    public void deleteAllById(Long id) {
        response.deleteAllById(id);
    }

    public Response handleError(ResStatus st, Response.ResponseBuilder rb) {
        rb.entity(String.format("{\"data\": \"%s\", \"status\": \"%b\"}", st.getDescription(), false));
        switch (st) {
            case VALIDATION_FAILED:
                rb.status(401);
                break;
            case REQUEST_ERROR:
                rb.status(500);
                break;
            case UNDEFINED_ERROR:
                rb.status(520);
                break;
        }
        return rb.build();
    }
}
