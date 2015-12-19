package nl.hsleiden.ipsen3.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.Wijn;
import nl.hsleiden.ipsen3.dao.WijnDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Daan on 30-Nov-15.
 */
@Path("wijn")
@Produces(MediaType.APPLICATION_JSON)
public class WijnResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;
    private final WijnDAO dao;

    public WijnResource(String template, String defaultName, WijnDAO dao) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    public List findAll() { return dao.findAll(); }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public void create(Wijn wijn) { dao.create(wijn); }


    @GET
    @Path("{id}")
    @Timed
    @UnitOfWork
    public Wijn getById(@PathParam("id") Long id) {
        return dao.findById(id);
    }


}
