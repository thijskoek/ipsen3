package nl.hsleiden.ipsen3.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by Roy on 13-1-2016.
 */
@Path("wachtwoord")
@Produces(MediaType.APPLICATION_JSON)
public class WachtwoordResource {

    private final String template;
    private final String defaultName;
    private ObjectMapper mapper = new ObjectMapper();

    public WachtwoordResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
    }

    @POST
    @Timed
    @Path("/generateLink")
    public String generateLink(@QueryParam("email") String email) throws JsonProcessingException {
        String link = "http://127.0.0.1:8080/?#/wachtwoordherstellen?key=123";
        link = mapper.writeValueAsString(link);
        return link;
    }

    @POST
    @Timed
    @Path("/herstellen")
    public void veranderWachtwoord(@QueryParam("wachtwoord") String wachtwoord) {
        System.out.println(wachtwoord);
    }


}
