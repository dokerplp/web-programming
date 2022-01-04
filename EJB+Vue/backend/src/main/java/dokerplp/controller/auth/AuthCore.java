package dokerplp.controller.auth;

import dokerplp.model.entity.UserEntity;
import dokerplp.model.proxy.UserProxy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;


@Stateful
@NoArgsConstructor
public class AuthCore {

    private final Logger logger = LoggerFactory.getLogger(AuthCore.class);

    @EJB
    private UserProxy userProxy;

    @Getter
    private Long id;
    @Getter
    private String login;
    @Getter
    private char[] pass;

    public AuthStatus init(String json){
        try {
            JSONObject object = new JSONObject(json);
            this.login = object.getString("login");
            this.pass = object.getString("password").toCharArray();
            return AuthStatus.OK;
        } catch (JSONException e) {
            logger.error("JSON error while parsing \"{}\"", json, e);
        } catch (Exception e) {
            logger.error("Undefined error on request \"{}\"", json,  e);
        }
        return AuthStatus.REQUEST_ERROR;
    }

    private boolean isValid() {
        return login != null && pass != null;
    }

    private AuthStatus newUser() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (isValid()) {
            byte[] salt = PassHash.salt();
            byte[] hash = PassHash.hash(salt, pass);

            userProxy.save(new UserEntity(login, hash, salt));
            this.id = userProxy.findByLogin(login).getId();
            return AuthStatus.OK;
        }
        return AuthStatus.UNDEFINED_ERROR;
    }

    public AuthStatus signIn() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (isValid()) {
            UserEntity user = userProxy.findByLogin(login);
            if (user == null) return AuthStatus.NO_USER_FOUND;
            this.id = user.getId();
            byte[] hash = PassHash.hash(user.getSalt(), pass);

            if (!Arrays.equals(user.getPassword(), hash)) return AuthStatus.WRONG_PASSWORD;
            return AuthStatus.OK;
        }
        return AuthStatus.UNDEFINED_ERROR;
    }

    public AuthStatus signUp() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (isValid()) {
            UserEntity user = userProxy.findByLogin(login);
            if (user != null) return AuthStatus.USER_ALREADY_EXISTS;
            if (login.length() < 5) return AuthStatus.TOO_SHORT_LOGIN;
            if (pass.length < 5) return AuthStatus.TOO_SHORT_PASSWORD;
            return newUser();
        }
        return AuthStatus.UNDEFINED_ERROR;
    }

    public Response handleError(AuthStatus st, Response.ResponseBuilder rb) {
        rb.entity(String.format("{\"data\": \"%s\", \"status\": \"%b\"}", st.getDescription(), false));
        switch (st) {
            case USER_ALREADY_EXISTS:
            case TOO_SHORT_LOGIN:
            case TOO_SHORT_PASSWORD:
            case NO_USER_FOUND:
            case WRONG_PASSWORD:
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
