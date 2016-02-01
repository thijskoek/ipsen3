package nl.hsleiden.ipsen3.resource;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.Actie;
import nl.hsleiden.ipsen3.core.Bestellijst;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.ActieDAO;
import nl.hsleiden.ipsen3.dao.BestellijstDAO;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Thijs Koek on 1/20/2016.
 */
@Path("/actie")
@Produces(MediaType.APPLICATION_JSON)
public class ActieResource {

    private final ActieDAO dao;

    public ActieResource(ActieDAO dao){
        this.dao = dao;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    @RolesAllowed({"beheerder", "m&s manager"})
    public long createActie(@Auth User user, Actie actie){
        return dao.create(actie);
    }

    @GET
    @Timed
    @UnitOfWork
    public Actie findActive(){
        return dao.findAll();
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Actie getById(@PathParam("id") Long id) {
        return dao.findById(id);
    }
}
