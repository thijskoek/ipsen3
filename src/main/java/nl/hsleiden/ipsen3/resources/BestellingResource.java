package nl.hsleiden.ipsen3.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.Factuur;
import nl.hsleiden.ipsen3.core.Product;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.BestellingDAO;
import nl.hsleiden.ipsen3.dao.FactuurDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.lang.Math.toIntExact;

/**
 * Created by Roy on 27-1-2016.
 */
@Path("bestelling")
@Produces(MediaType.APPLICATION_JSON)
public class BestellingResource {

    private ObjectMapper mapper = new ObjectMapper();
    private FactuurDAO dao;

    public BestellingResource(FactuurDAO factuurDAO) {
        this.dao = factuurDAO;
    }

    @POST
    @Timed
    @UnitOfWork
    @Path("/test")
    public String test(@Auth User user) throws JsonProcessingException {
        List<Factuur> facturen = dao.findByDebiteur(toIntExact(user.getId()));
        String jsonString = mapper.writeValueAsString(facturen);
        return jsonString;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/haalfactuur")
    public String haal_factuur(@QueryParam("id") long factuur_id) throws JsonProcessingException {
        Factuur factuur = dao.findById(factuur_id);
        String jsonString = mapper.writeValueAsString(factuur);
        return jsonString;
    }
}
