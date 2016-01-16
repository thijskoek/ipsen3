package nl.hsleiden.ipsen3.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.View;
import nl.hsleiden.ipsen3.core.User;

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

    private final OrderDAO dao;

    public OrderResource(OrderDao userDAO) {
        dao = orderDAO;
    }


    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(User user) {
        dao.create(user);
    }
}
