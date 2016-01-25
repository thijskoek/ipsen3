package nl.hsleiden.ipsen3.resource;

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

/**
 * Created by Brandon on 16-Jan-16.
 *
 * @author Daan Rosbergen
 */
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final Logger logger = LoggerFactory.getLogger(OrderResource.class);

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
        Factuur factuur = factuurDAO.findById(id);
        for (OrderRegel orderRegel: order.getRegels()) {
            Factuurregel factuurregel = new Factuurregel();
            factuurregel.setAantal(orderRegel.getAantal());
            factuurregel.setWijn(orderRegel.getWijn());
            factuurregel.setFactuur(factuur);
            factuur.addFactuurregel(factuurregel);
        }
        factuurDAO.create(factuur);

        // TODO: Refactor this clusterfuck!
        MailService mailService = new MailService(new JavaMailStrategy());
        Email email = new Email();
        email.setSubject("Bedankt voor uw bestelling!");

        String content = String.format("Beste %s \n\n" +
                "Bedankt voor uw bestelling #%s. In deze mail vind u een factuur als " +
                "bijlage met een overzicht van uw bestelling. \n\n" +
                "Besteloverzicht: \n\n", factuur.getDebiteur().getFullName(), factuur.getFactuurnummer());

        for (Factuurregel regel : factuur.getFactuurregels()) {
            content += String.format("%s %s €%s \n", regel.getAantal(), regel.getWijn().getNaam(), regel.getWijn().getPrijs());
        }

        content += "\n\n Met vriendelijke groet, \n Groep 4, IPSEN3";

        email.setContent(content, "text/plain");
        email.setTo(factuur.getDebiteur().getEmail());
        email.setFrom("no-reply@groep4.ipsen3.nl");

        mailService.send(email);
    }
}
