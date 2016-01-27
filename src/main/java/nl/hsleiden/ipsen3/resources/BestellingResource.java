package nl.hsleiden.ipsen3.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.Bestelling;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.BestellingDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy on 27-1-2016.
 */
@Path("bestelling")
@Produces(MediaType.APPLICATION_JSON)
public class BestellingResource {

    private ObjectMapper mapper = new ObjectMapper();
    private BestellingDAO dao;

    public BestellingResource(BestellingDAO bestellingDAO) {
        this.dao = bestellingDAO;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/test")
    public void test(@Auth User user) {
        List<Bestelling> bestellingen = dao.findByUser(user.getId());

    }
}
