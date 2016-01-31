package nl.hsleiden.ipsen3.resource;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.Company;
import nl.hsleiden.ipsen3.dao.CompanyDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Brandon on 31-Jan-16.
 */
@Path("/bedrijf")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyResource {

    private final CompanyDAO dao;

    public CompanyResource(CompanyDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Company getById(@PathParam("id") Long id) { return dao.findById(id); }

    @POST
    @Timed
    @UnitOfWork
    public long createCompany(Company company) { return dao.create(company); }
}
