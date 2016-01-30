package nl.hsleiden.ipsen3.services.strategies.mail;



import nl.hsleiden.ipsen3.core.helper.Email;
import nl.hsleiden.ipsen3.services.interfaces.MailStrategy;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Standard Java mail strategy, uses a predefined smtp server to send a Email object. Uses the javax
 * mail library.
 *
 * @author Daan Rosbergen
 */
public class JavaMailStrategy implements MailStrategy {

    private Session session;

    /**
     * Sets smtp server and connects to it.
     *
     * @author Daan Rosbergen
     */
    public JavaMailStrategy() {
        final String username = "brandonvanwijk@gmail.com";
        final String password = "lrlmvkxsnvposaty";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Get the default Session object.
        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    /**
     * Send a email via this strategy.
     *
     * @author Daan Rosbergen
     * @param email
     */
    @Override public void send(Email email) {
        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(email.getFrom()));

            // Set To: header field of the header.
            for (String to: email.getRecipients()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }

            // Set Subject: header field
            message.setSubject(email.getSubject());

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setContent(email.getContent(), email.getContentType());

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            for (ArrayList<String> fileData: email.getAttachments()) {
                messageBodyPart = new MimeBodyPart();
                String filename = fileData.get(0);
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileData.get(1));
                multipart.addBodyPart(messageBodyPart);
            }

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
