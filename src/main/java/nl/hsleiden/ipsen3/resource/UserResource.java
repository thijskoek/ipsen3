package nl.hsleiden.ipsen3.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.View;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.UserDAO;
import org.hibernate.Hibernate;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Daan
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserDAO dao;

    public UserResource(UserDAO userDAO) {
        dao = userDAO;
    }

    @GET
    @UnitOfWork
    @JsonView(View.Public.class)
    @RolesAllowed("beheerder")
    public List<User> retrieveAll(@Auth User user) {
        List<User> users = dao.findAll();
        for (User u: users) {
            Hibernate.initialize(u.getRoles());
        }
        return users;
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    @JsonView(View.Public.class)
    public User retrieve(@PathParam("id") int id) {
        return dao.findById(id);
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(User user) {
        if(dao.getByEmail(user.getEmail()) != null) {
            throw new BadRequestException("Email already in use");
        }
        dao.create(user);
    }

    @UnitOfWork
    @GET
    @Path("/me")
    @JsonView(View.Private.class)
    public User authenticate(@Auth User authenticator) {
        return authenticator;
    }
}
