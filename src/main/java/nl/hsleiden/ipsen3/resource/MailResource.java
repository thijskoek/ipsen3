package nl.hsleiden.ipsen3.resource;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import nl.hsleiden.ipsen3.core.helper.Email;
import nl.hsleiden.ipsen3.core.Mail;
import nl.hsleiden.ipsen3.services.MailService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.StringWriter;

/**
 * Created by Brandon on 12-Jan-16.
 */

@Path("mail")
@Produces(MediaType.APPLICATION_JSON)
public class MailResource {

    private MustacheFactory mf = new DefaultMustacheFactory();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void sendMail(Mail mail) {
        MailService mailService = new MailService();

        Email email = new Email();
        email.setSubject(mail.getSubject());

        Mustache mustache = mf.compile("mailtemplates/contact-mail.mustache");
        StringWriter content = (StringWriter) mustache.execute(new StringWriter(), mail);

        email.setContent(content.toString(), "text/html");
        email.setTo(mail.getEmail());
        email.setFrom("no-reply@groep4.ipsen3.nl");

        mailService.send(email);
    }

}
