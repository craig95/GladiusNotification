package com.gladius.notification;

import com.sun.mail.smtp.SMTPTransport;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The Mailer class provides the email functionality to the NotificationInterface class. It has one public function
 * which handles the sending of emails. The Mailer class makes use of an existing SMTP server and JavaMail to send
 * emails.
 *
 * @author  GladiOS Notification Module Team
 * @version 1.0
 * @since   22-03-2017
 */
public class Mailer {
    /**
     * The email address that will appear in the from field of the message.
     */
    private String from;
    /**
     * The address of the SMTP server that JavaMail will send the email request to.
     */
    private String host;
    /**
     * The mail port of the SMTP server.
     */
    private String port;
    /**
     * Indicates whether the server needs authentication.
     */
    private boolean auth;
    /**
     * The username for authentication if the SMTP server requires authentication.
     */
    private String username;
    /**
     * The password for authentication if the SMTP server requires authentication.
     */
    private String password;
    /**
     * The properties that will be used to create a session with the specified SMTP server.
     */
    private Properties props;
    /**
     * The session that will be established with the specified SMTP server.
     */
    private Session session;

    /**
     * Constructor
     *
     * @param _from the email address that will appear as the sender in all emails sent.
     * @param _host the SMTP server address that will handle the emails
     * @param _port the port of the SMTP server that will handle the emails
     * @param _auth boolean to indicate whether the server needs authentication
     * @param _username the username needed to authenticate with the server
     * @param _password the password needed to authenticate with the server
     */
    public Mailer(String _from, String _host, String _port, boolean _auth, String _username, String _password) {
        from = _from;
        host = _host;
        port = _port;
        auth = _auth;
        username = _username;
        password = _password;

        props = new Properties();

        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);


        //Create session
        if (auth) {
            session = Session.getInstance(props, new javax.mail.Authenticator() {protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(username, password);}});
        } else {
            session = Session.getInstance(props);
        }

    }

    /**
     * Sends a email with the given HTML content with the given subject to the email specified.
     *
     * @param subject the subject of the email to be sent.
     * @param content the HTML contenet of the email to be sent.
     * @param email the users email address to which the email needs to be send.
     * @return will return whether the email was successfully sent.
     */
    public boolean sendNotification(String subject, String content, InternetAddress[] email) {
        try {
            //Create Message
            MimeMessage message = new MimeMessage(session);
            message.addFrom(InternetAddress.parse(from));
            message.setRecipients(Message.RecipientType.TO, email);
            message.setSubject(subject);
            message.setContent(content, "text/html");

            //Send Message
            SMTPTransport smtpTransport = (SMTPTransport)session.getTransport("smtp");
            smtpTransport.connect(host, new Integer(port), username, password);
            smtpTransport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            smtpTransport.close();
            //Log Success
            MailLogger.logMailSent(0, email.toString(), content);
            return true;
        } catch (MessagingException e) {
            //Log Failure
            MailLogger.logMailError(0, email.toString(), e.toString());
            return false;
        }
    }
}
