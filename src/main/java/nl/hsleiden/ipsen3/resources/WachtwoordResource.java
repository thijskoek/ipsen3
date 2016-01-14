package nl.hsleiden.ipsen3.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Roy on 13-1-2016.
 * De link moet gehashed worden met een uniek salt, zodat per opvraag een altijd unieke link wordt gegeven.
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
    public String generateLink(@QueryParam("email") String email) throws JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String link = "http://127.0.0.1:8080/?#/wachtwoordherstellen?key=";
        link += add_key(email);
        link = mapper.writeValueAsString(link);
        return link;
    }

    private String add_key(String email) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String key;
        byte[] bytesOfMessage = email.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        key = thedigest.toString();
        return key;
    }

    @POST
    @Timed
    @Path("/herstellen")
    public void veranderWachtwoord(@QueryParam("wachtwoord") String wachtwoord) {
        System.out.println(wachtwoord);
    }


}
