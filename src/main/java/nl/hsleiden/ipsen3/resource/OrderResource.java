package nl.hsleiden.ipsen3.resource;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.Factuur;
import nl.hsleiden.ipsen3.core.Order;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.FactuurDAO;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Brandon on 16-Jan-16.
 */

@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final FactuurDAO factuurDAO;

    public OrderResource(FactuurDAO factuurDAO) {
        this.factuurDAO = factuurDAO;
    }

    @POST
    @UnitOfWork
//    @RolesAllowed("klant")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@Auth User user, Order order) {
        System.out.println(order);
        factuurDAO.create(order);
    }
}
