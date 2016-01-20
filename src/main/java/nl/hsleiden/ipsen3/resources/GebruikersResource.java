package nl.hsleiden.ipsen3.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import nl.hsleiden.ipsen3.core.Gebruiker;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.GebruikerDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


/**
 * Created by Roy on 11-1-2016.
 */
@Path("gebruiker")
@Produces(MediaType.APPLICATION_JSON)
public class GebruikersResource {

    private ObjectMapper mapper = new ObjectMapper();
    private GebruikerDAO dao;

    public GebruikersResource(GebruikerDAO dao) { this.dao = dao; }


    @GET
    @Timed
    @UnitOfWork
    @Path("/test")
    public String test() throws JsonProcessingException {
        System.out.println("oke");
        String jsonString = mapper.writeValueAsString("Hallo hallo de API hier.");
        return jsonString;
    }

    @POST
    @Timed
    @UnitOfWork
    @Path("/wijzig")
    public void wijzig(@Auth User user, @QueryParam("gebruiker") String gebruiker) throws IOException {
        JsonNode jsonNode = mapper.readTree(gebruiker);
        Gebruiker localGebruiker = mapper.treeToValue(jsonNode, Gebruiker.class);
        dao.update(localGebruiker, user.getEmail());
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/getGebruiker")
    public String get_gebruiker(@Auth User user) throws JsonProcessingException {
        Gebruiker gebruiker = dao.findByMail(user.getEmail());
        String jsonString = mapper.writeValueAsString(gebruiker);
        return jsonString;
    }
}
