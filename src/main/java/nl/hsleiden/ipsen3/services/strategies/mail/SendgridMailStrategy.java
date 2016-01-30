package nl.hsleiden.ipsen3.services.strategies.mail;


import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import nl.hsleiden.ipsen3.core.helper.Email;
import nl.hsleiden.ipsen3.services.interfaces.MailStrategy;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Sendgrid MailStrategy. Used when emails should be send via SendGrid. The SendGrid api key is
 * stored in this class.
 *
 * @author Daan Rosbergen
 * Created by Daan on 30-Sep-15.
 */
public class SendgridMailStrategy implements MailStrategy {

    private final SendGrid sendgrid;

    /**
     * Creates a instance of the SendGrid api with a api key.
     *
     * @author Daan Rosbergen
     */
    public SendgridMailStrategy() {
        sendgrid = new SendGrid("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");
    }

    /**
     * Sends a email via SendGrid.
     *
     * @author Daan Rosbergen
     * @param email
     */
    @Override
    public void send(Email email) {
        SendGrid.Email sendgridEmail = new SendGrid.Email();

        for (String to: email.getRecipients()) {
            sendgridEmail.addTo(to);
        }
        sendgridEmail.setFrom(email.getFrom());
        sendgridEmail.setSubject(email.getSubject());
        sendgridEmail.setHtml(email.getContent());

        for (ArrayList<String> fileData: email.getAttachments()) {
            try {
                sendgridEmail.addAttachment(fileData.get(1), fileData.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            SendGrid.Response response = sendgrid.send(sendgridEmail);
            System.out.println(response.getMessage());
        } catch (SendGridException e) {
            e.printStackTrace();
        }

    }
}
