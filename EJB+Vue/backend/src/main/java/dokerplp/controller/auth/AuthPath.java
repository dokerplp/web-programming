package dokerplp.controller.auth;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Path("/auth")
@ApplicationScoped
public class AuthPath {
    @EJB
    private AuthCore auth;

    @POST
    @Path("/sign-in")
    public Response signIn(String json) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Response.ResponseBuilder rb = Response.ok();
        rb.header("Content-Type", "application/json;charset=UTF-8");

        AuthStatus st;
        if ((st = auth.init(json)) == AuthStatus.OK) {
            if ((st = auth.signIn()) == AuthStatus.OK) {
                rb.status(200);
                rb.entity(String.format("{\"status\": \"%b\"}", true));
                return rb.build();
            }
        }
        return auth.handleError(st, rb);
    }

    @POST
    @Path("/sign-up")
    public Response signUp(String json) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Response.ResponseBuilder rb = Response.ok();
        rb.header("Content-Type", "application/json;charset=UTF-8");

        AuthStatus st;
        if ((st = auth.init(json)) == AuthStatus.OK) {
            if ((st = auth.signUp()) == AuthStatus.OK) {
                rb.status(200);
                rb.entity(String.format("{\"status\": \"%b\"}", true));
                return rb.build();
            }
        }
        return auth.handleError(st, rb);
    }

}
