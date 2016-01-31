package nl.hsleiden.ipsen3.resource;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.core.Wijn;
import nl.hsleiden.ipsen3.dao.WijnDAO;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Daan on 30-Nov-15.
 */
@Path("/wijnen")
@Produces(MediaType.APPLICATION_JSON)
public class WijnResource {

    private final WijnDAO dao;

    public WijnResource(WijnDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Wijn getById(@PathParam("id") Long id) {
        return dao.findById(id);
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Wijn> findAll() {
        return dao.findAll();
    }

    @POST
    @Timed
    @UnitOfWork
    @RolesAllowed({"beheerder", "m&s manager"})
    public long createWijn(@Auth User user, Wijn wijn) {
        return dao.create(wijn);
    }

    @PUT
    @Path("/{id}")
    @Timed
    @UnitOfWork
    @RolesAllowed({"beheerder", "m&s manager"})
    public long updateWijn(@Auth User user, @PathParam("id") Long id, Wijn wijn) {
        if (dao.findById(id) == null) {
            throw new NotFoundException("Deze wijn bestaat nog niet.");
        }
        return dao.update(wijn);
    }
}
