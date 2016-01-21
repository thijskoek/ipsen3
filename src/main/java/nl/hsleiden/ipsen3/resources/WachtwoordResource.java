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
import org.apache.commons.codec.binary.Hex;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Roy on 13-1-2016.
 * De link moet gehashed worden met een unique salt, zodat per opvraag een altijd unieke link wordt gegeven.
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
        sleutelDao.update(sleutel);
        return sleutel.getSleutel();
    }

    private String generate_key(String email) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String key;
        byte[] bytesOfMessage = email.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        key = Hex.encodeHexString(thedigest);
        return key;
    }

    @POST
    @Timed
    @UnitOfWork
    @Path("/controleersleutel")
    public boolean controleer_sleutel(@QueryParam("sleutel") String sleutel) {
        return sleutelDao.exists(sleutel);
    }

    @POST
    @Timed
    @UnitOfWork
    @Path("/herstellen")
    public void herstellen(@QueryParam("sleutel") String sleutel, @QueryParam("wachtwoord") String wachtwoord) {
        if(sleutelDao.exists(sleutel)) {
            Sleutel sleutelObject = sleutelDao.findByKey(sleutel);
            User user = userDao.findById(sleutelObject.getUser_id());
            user.setPassword(wachtwoord);
            user.hashPassword();
            userDao.update(user);
        }
    }

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
