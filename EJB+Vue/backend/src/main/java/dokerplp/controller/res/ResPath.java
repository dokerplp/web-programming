package dokerplp.controller.res;

import dokerplp.controller.auth.AuthCore;
import dokerplp.controller.auth.AuthStatus;
import dokerplp.model.entity.ResponseEntity;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Path("/result")
@ApplicationScoped
public class ResPath {
    @EJB
    private AuthCore auth;

    @EJB
    private ResCore res;

    @POST
    public Response result(String json) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Response.ResponseBuilder rb = Response.ok();
        rb.header("Content-Type", "application/json;charset=UTF-8");

        long start = System.nanoTime();

        AuthStatus st;
        if ((st = auth.init(json)) == AuthStatus.OK) {
            if ((st = auth.signIn()) == AuthStatus.OK) {
                ResStatus rst;
                if ((rst = res.init(json)) == ResStatus.OK) {
                    if ((rst = res.validate()) == ResStatus.OK) {
                        res.save(auth.getId(), start);
                        StringBuilder sb = new StringBuilder();
                        sb.append("[");
                        for (ResponseEntity entity : res.findAllById(auth.getId())) {
                            sb.append(entity.toString());
                            sb.append(",");
                        }
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append("]");

                        rb.status(200);
                        rb.entity(String.format("{\"data\": %s, \"status\": \"%b\"}", sb, true));
                        return rb.build();
                    }
                }
                return res.handleError(rst, rb);
            }
        }
        return auth.handleError(st, rb);
    }

    @POST
    @Path("/clear")
    public Response clear(String json) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Response.ResponseBuilder rb = Response.ok();
        rb.header("Content-Type", "application/json;charset=UTF-8");
        AuthStatus st;
        if ((st = auth.init(json)) == AuthStatus.OK) {
            if ((st = auth.signIn()) == AuthStatus.OK) {
                res.deleteAllById(auth.getId());
                rb.status(200);
                rb.entity(String.format("{\"status\": \"%b\"}", true));
                return rb.build();
            }
        }
        return auth.handleError(st, rb);
    }

    @POST
    @Path("/find")
    public Response find(String json) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Response.ResponseBuilder rb = Response.ok();
        rb.header("Content-Type", "application/json;charset=UTF-8");
        AuthStatus st;
        if ((st = auth.init(json)) == AuthStatus.OK) {
            if ((st = auth.signIn()) == AuthStatus.OK) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                for (ResponseEntity entity : res.findAllById(auth.getId())) {
                    sb.append(entity.toString());
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("]");

                rb.status(200);
                rb.entity(String.format("{\"data\": %s, \"status\": \"%b\"}", sb, true));
                return rb.build();
            }
        }
        return auth.handleError(st, rb);
    }

}
