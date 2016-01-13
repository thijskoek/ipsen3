package nl.hsleiden.ipsen3.resource;

import nl.hsleiden.ipsen3.core.Email;
import nl.hsleiden.ipsen3.core.Mail;
import nl.hsleiden.ipsen3.services.MailService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Brandon on 12-Jan-16.
 */

@Path("mail")
@Produces(MediaType.APPLICATION_JSON)
public class MailResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public MailResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void sendMail(Mail mail) {
        MailService mailService = new MailService();

        Email email = new Email();
        email.setFrom("IPSEN3");
        email.setTo(mail.getEmail());
        email.setSubject(mail.getSubject());
        email.setText(mail.getMessage());

        mailService.send(email);
    }

}
