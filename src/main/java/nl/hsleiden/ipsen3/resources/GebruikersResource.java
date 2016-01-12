package nl.hsleiden.ipsen3.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import nl.hsleiden.ipsen3.core.Gebruiker;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * Created by Roy on 11-1-2016.
 */
@Path("gebruiker")
@Produces(MediaType.APPLICATION_JSON)
public class GebruikersResource {

    private final String template;
    private final String defaultName;
    private ObjectMapper mapper = new ObjectMapper();

    public GebruikersResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/test")
    public String test() throws JsonProcessingException {
        System.out.println("oke");
        String jsonString = mapper.writeValueAsString("Hallo hallo de API hier.");
        return jsonString;
    }

    @GET
    @Timed
    @Path("/getGebruiker")
    public String get_gebruiker(@QueryParam("id") LongParam id) throws JsonProcessingException {
        System.out.println(id.toString());
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setVoornaam("Roy");
        gebruiker.setTussenvoegsel("");
        gebruiker.setNaam("Touw");
        String jsonString = mapper.writeValueAsString(gebruiker);
        return jsonString;
    }
}
