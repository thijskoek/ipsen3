package nl.hsleiden.ipsen3.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.Company;
import nl.hsleiden.ipsen3.core.Gebruiker;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.CompanyDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by Brandon on 31-Jan-16.
 */
@Path("/bedrijf")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyResource {

    private ObjectMapper mapper = new ObjectMapper();
    private final CompanyDAO dao;

    public CompanyResource(CompanyDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/getBedrijf")
    public String get_gebruiker() throws JsonProcessingException {
        Company company = dao.findById((long) 2);
        String jsonString = mapper.writeValueAsString(company);
        return jsonString;
    }

    //public Company getById(@PathParam("id") Long id) { return dao.findById(id); }

    @POST
    @Timed
    @UnitOfWork
    @Path("/createBedrijf")
    public long createCompany(Company company) { return dao.create(company); }

    @POST
    @Timed
    @UnitOfWork
    @Path("/wijzig")
    public void wijzig(@QueryParam("company") String company) throws IOException {
        JsonNode jsonNode = mapper.readTree(company);
        Company localGebruiker = mapper.treeToValue(jsonNode, Company.class);
        dao.update(localGebruiker);
    }
}
