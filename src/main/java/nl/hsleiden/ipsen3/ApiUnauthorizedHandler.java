package nl.hsleiden.ipsen3;

import io.dropwizard.auth.UnauthorizedHandler;

import javax.ws.rs.core.Response;

/**
 * @author Daan
 */
public class ApiUnauthorizedHandler implements UnauthorizedHandler {
    public Response buildResponse(String prefix, String realm) {
        return null;
    }
}
