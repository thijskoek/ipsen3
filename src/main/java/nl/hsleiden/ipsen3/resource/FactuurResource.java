package nl.hsleiden.ipsen3.resource;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.Factuur;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.FactuurDAO;
import org.joda.time.DateTime;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/invoices")
@Produces(MediaType.APPLICATION_JSON)
public class FactuurResource {

    private final FactuurDAO dao;

    public FactuurResource(FactuurDAO dao) {
        this.dao = dao;
    }

    @GET
    @UnitOfWork
    @RolesAllowed({"beheerder", "m&s manager"})
    public List<Factuur> findAll(@Auth User user, @QueryParam("status") String status,
        @QueryParam("year") String year) {
        DateTime jodaYear = null;
        if (year != null) {
             jodaYear = new DateTime(year);
        }
        return dao.findAll(status, jodaYear);
    }

    @GET
    @UnitOfWork
    @Path("/revenue")
    @RolesAllowed({"beheerder", "m&s manager"})
    public Map<String, Double> getRevenue(@Auth User user, @QueryParam("year") String year) {
        DateTime jodaYear = null;
        if (year != null) {
            jodaYear = new DateTime(year);
        }
        return dao.getRevenue(jodaYear);
    }

}
