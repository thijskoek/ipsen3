package nl.hsleiden.ipsen3.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.Gebruiker;
import nl.hsleiden.ipsen3.core.Sleutel;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.GebruikerDAO;
import nl.hsleiden.ipsen3.dao.SleutelDAO;
import nl.hsleiden.ipsen3.dao.UserDAO;

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
    
    private ObjectMapper mapper = new ObjectMapper();
    private UserDAO userDao;
    private SleutelDAO sleutelDao;

    public WachtwoordResource(UserDAO userDao, SleutelDAO sleutelDao) {
        this.userDao = userDao;
        this.sleutelDao = sleutelDao;
    }

    @POST
    @Timed
    @UnitOfWork
    @Path("/generateLink")
    public String generateLink(@QueryParam("email") String email) throws JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String link = "http://127.0.0.1:8080/?#/wachtwoordherstellen?key=";
        link += create_sleutel(email);
        link = mapper.writeValueAsString(link);
        return link;
    }

    private String create_sleutel(String email) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = userDao.getByEmail(email);
        Sleutel sleutel = new Sleutel();
        sleutel.setUser_id(user.getId());
        sleutel.setSleutel(generate_key(email));
        sleutelDao.create(sleutel);
        return sleutel.getSleutel();
    }

    private String generate_key(String email) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String key;
        byte[] bytesOfMessage = email.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        key = thedigest.toString();
        return key;
    }

   /* private String add_key(String email) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String key;
        byte[] bytesOfMessage = email.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        key = thedigest.toString();
        //Add email to key
        key += "&email=" + email;
        sleutelDao.create()
        return key;
    }*/

  /*  @POST
    @Timed
    @UnitOfWork
    @Path("/herstellen")
    //Pas email along, fetch gebruiker by email et voila@
    public void veranderWachtwoord(@QueryParam("wachtwoord") String wachtwoord,
                                   @QueryParam("email") String email) {
        Gebruiker gebruiker = dao.findByMail(email);
        //gebruiker.setWachtwoord(wachtwoord);
        dao.create(gebruiker);
    }*/


}
