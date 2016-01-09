package nl.hsleiden.ipsen3.service;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.UserDAO;

/**
 * @author Daan
 */
public class AuthenticationService implements Authenticator<BasicCredentials, User>, Authorizer<User> {
    private final UserDAO dao;

    public AuthenticationService(UserDAO userDAO) {
        this.dao = userDAO;
    }

    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        return null;
    }

    public boolean authorize(User principal, String role) {
        return false;
    }
}
