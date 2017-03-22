package com.gladius.notification;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by craigvanheerden on 3/22/17.
 */
public class Mailer {
    private String from;
    private String host;
    private String port;
    private boolean auth;
    private String username;
    private String password;
    private Properties props;
    private Session session;

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

        if (auth) {
            session = Session.getInstance(props, new javax.mail.Authenticator() {protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(username, password);}});
        } else {
            session = Session.getInstance(props);
        }
    }

    public boolean sendNotification(String subject, String content, String email) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setContent(content, "text/html");
            // Send message
            Transport.send(message);
            //log sent message to database
            return true;
        } catch (MessagingException e) {
            //throw new RuntimeException(e);
            //log error to database
            return false;
        }
    }
}
