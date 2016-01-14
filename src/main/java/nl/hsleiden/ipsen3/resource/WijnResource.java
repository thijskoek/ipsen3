package nl.hsleiden.ipsen3.resource;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.Wijn;
import nl.hsleiden.ipsen3.dao.WijnDAO;

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
    public long createWijn(Wijn wijn) {
        return dao.create(wijn);
    }
}
