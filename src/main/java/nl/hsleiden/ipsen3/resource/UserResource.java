package nl.hsleiden.ipsen3.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.View;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.UserDAO;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
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
    @JsonView(View.Public.class)
    @UnitOfWork
    public List<User> retrieveAll()
    {
        return dao.findAll();
    }

    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @UnitOfWork
    public User retrieve(@PathParam("id") int id)
    {
        return dao.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @UnitOfWork
    public void create(User user)
    {
        dao.create(user);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @UnitOfWork
    public void update(@PathParam("id") int id, @Auth User authenticator, User user)
    {
        dao.update(authenticator, id, user);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("beheerder")
    @UnitOfWork
    public void delete(@PathParam("id") int id)
    {
        dao.delete(id);
    }

    @GET
    @Path("/me")
    @JsonView(View.Private.class)
    @UnitOfWork
    public User authenticate(@Auth User authenticator)
    {
        return authenticator;
    }
}
