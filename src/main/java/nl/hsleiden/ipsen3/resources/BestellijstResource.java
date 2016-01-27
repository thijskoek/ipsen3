package nl.hsleiden.ipsen3.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.Bestellijst;
import nl.hsleiden.ipsen3.core.Wijn;
import nl.hsleiden.ipsen3.dao.BestellijstDAO;
import nl.hsleiden.ipsen3.dao.WijnDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Victor on 11-1-2016.
 */
@Path("/bestellijst")
@Produces(MediaType.APPLICATION_JSON)
public class BestellijstResource {

    private final BestellijstDAO dao;

    public BestellijstResource(BestellijstDAO dao) {
        this.dao = dao;

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public long createBestellijst(Bestellijst bestellijst){
        return dao.create(bestellijst);
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Bestellijst> findAll(){
        return dao.findAll();
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Bestellijst getById(@PathParam("id") Long id) {
        return dao.findById(id);
    }
}
