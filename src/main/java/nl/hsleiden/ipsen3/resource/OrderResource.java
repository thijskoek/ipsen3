package nl.hsleiden.ipsen3.resource;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.ipsen3.core.*;
import nl.hsleiden.ipsen3.dao.FactuurDAO;
import nl.hsleiden.ipsen3.services.MailService;
import nl.hsleiden.ipsen3.services.strategies.mail.JavaMailStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;

/**
 * Created by Brandon on 16-Jan-16.
 *
 * @author Daan Rosbergen
 */
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final Logger logger = LoggerFactory.getLogger(OrderResource.class);
    private MustacheFactory mf = new DefaultMustacheFactory();
    private MailService mailService = new MailService(new JavaMailStrategy());

    private final FactuurDAO factuurDAO;

    public OrderResource(FactuurDAO factuurDAO) {
        this.factuurDAO = factuurDAO;
    }

    @POST
    @UnitOfWork
    @RolesAllowed("klant")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@Auth User user, Order order) {
        long id = factuurDAO.create(order);
        final Factuur factuur = factuurDAO.findById(id);
        for (OrderRegel orderRegel: order.getRegels()) {
            Factuurregel factuurregel = new Factuurregel();
            factuurregel.setAantal(orderRegel.getAantal());
            factuurregel.setWijn(orderRegel.getWijn());
            factuurregel.setFactuur(factuur);
            factuur.addFactuurregel(factuurregel);
        }
        factuurDAO.create(factuur);
        new Thread(new Runnable() {
            public void run() {
                sendOrderEmail(factuur);
            }
        }).start();
    }

    private void sendOrderEmail(Factuur factuur) {
        // TODO: Refactor this clusterfuck!
        Email email = new Email();
        email.setSubject("Bedankt voor uw bestelling!");

        Mustache mustache = mf.compile("mailtemplates/bevestiging-bestelling.mustache");
        StringWriter content = (StringWriter) mustache.execute(new StringWriter(), factuur);

        email.setContent(content.toString(), "text/html");
        email.setTo(factuur.getDebiteur().getEmail());
        email.setFrom("no-reply@groep4.ipsen3.nl");

        mailService.send(email);
    }
}
