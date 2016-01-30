package nl.hsleiden.ipsen3.services;


import nl.hsleiden.ipsen3.core.helper.Email;
import nl.hsleiden.ipsen3.services.interfaces.MailStrategy;
import nl.hsleiden.ipsen3.services.strategies.mail.JavaMailStrategy;

/**
 * Class used to send email via a MailStrategy of your choice.
 *
 * Standard MailStrategy is JavaMail.
 *
 * @author Daan Rosbergen
 */
public class MailService {

    private MailStrategy mailStrategy;

    /**
     * Creates a MailService with the standard JavaMailStrategy.
     *
     * @author Daan Rosbergen
     */
    public MailService() {
        this.mailStrategy = new JavaMailStrategy();
    }

    /**
     * Creates a MailService object to send mail via a MailStrategy of your choice.
     *
     * @author Daan Rosbergen
     * @param mailStrategy  MailStrategy    Strategy used to send mail.
     */
    public MailService(MailStrategy mailStrategy) {
        this.mailStrategy = mailStrategy;
    }

    /**
     * Set new MailStrategy.
     *
     * @author Daan Rosbergen
     * @param mailStrategy  MailStrategy    New MailStrategy.
     */
    public void setMailStrategy(MailStrategy mailStrategy) {
        this.mailStrategy = mailStrategy;
    }

    /**
     * Send mail via strategy.
     *
     * @author Daan Rosbergen
     * @param email
     */
    public void send(Email email) {
        this.mailStrategy.send(email);
    }

}
