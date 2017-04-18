package com.gladius.notification;

//import Users.Users;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;

/**
 * The NotificationInterface class provides the interface for the entire notification module. It provides two public
 * functions with which other modules can interface. The two functions have the same names but one sends a notification
 * to one user and the other to a list of users. To create
 *
 * @author  GladiOS Notification Module Team
 * @version 1.0
 * @since   22-03-2017
 */
public class NotificationInterface {
    /**
     * Instance of the user module class that is needed to retrive emails.
     */
    //private Users user;
    /**
     *
     */
    private String SMS_From;
    private String SMS_SMTPHost;
    private String SMS_SMTPPort;
    private boolean SMS_SMTPAuth;
    private String SMS_SMTPAuthUsername;
    private String SMS_SMTPAuthPassword;
    private String Email_to_SMS_API_Domain;

    private String Email_From;
    private String Email_SMTPHost;
    private String Email_SMTPPort;
    private boolean Email_SMTPAuth;
    private String Email_SMTPAuthUsername;
    private String Email_SMTPAuthPassword;
    /**
     * Constructor
     *
     * Private constructor for the Notification class. The private constructor prevents instantiation from other
     * classes (Singleton Design Pattern).
     */
    /*
     * TODO: Read all the config vars from a file.
     */
    private NotificationInterface() {
        SMS_From = "gladius.notification@gmail.com";
        SMS_SMTPHost = "smtp.gmail.com";
        SMS_SMTPPort = "587";
        SMS_SMTPAuth = true;
        SMS_SMTPAuthUsername = "gladius.notification@gmail.com";
        SMS_SMTPAuthPassword = "9FM-mZD-wtC-trd";
        Email_to_SMS_API_Domain = "nodomain.no";

        Email_From = "gladius.notification@gmail.com";
        Email_SMTPHost = "smtp.gmail.com";
        Email_SMTPPort = "587";
        Email_SMTPAuth = true;
        Email_SMTPAuthUsername = "gladius.notification@gmail.com";
        Email_SMTPAuthPassword = "9FM-mZD-wtC-trd";
        //user = new Users();
    }

    /**
     * NotificationHolder is loaded on the first execution of Singleton.getInstance() or the first access to
     * SingletonHolder.INSTANCE, not before. This is to ensure that there only exists one instance of Notification
     * (Singleton Design Pattern).
     */
    private static class NotificationHolder {
        private static final NotificationInterface INSTANCE = new NotificationInterface();
    }

    /**
     * Returns an instance of Notification. If no instance of notification exists a new one will be created and
     * returned. If an instance already exists it will return that instance. (Singleton Design Pattern).
     * @return will return an instance of NotificationInterface.
     */
    public static NotificationInterface getInstance() {
        return NotificationHolder.INSTANCE;
    }

    /**
     * Public interface to send a message to a single user.
     * @param userID the ID of the user the message needs to be sent to.
     * @param message a message in string format that needs to be sent to the user.
     * @param noticeType the type of notification sms(to be determined), email or push notification.
     * @return will return true if the message was successfully sent and false if message failed to send.
     */
    /*
     * TODO: This function should simply create an ArrayList of one user and then call the other senNotification function and return the result that that function returns.
     */
    public boolean sendNotification(long userID, String message, String noticeType) {
        ArrayList<Long> tempArray = new ArrayList<Long>();
        tempArray.add(userID);
        String valid = validate(tempArray, message, noticeType);
        if (valid == "valid") { //validation succeeded
            if (noticeType == "email") {
                InternetAddress[] addresses;
                try {
                    addresses = InternetAddress.parse(getEmail(userID));
                } catch (AddressException e) {
                    return false;
                }
                EmailerThread emailerThread = new EmailerThread("NavUP Notification", message, addresses);
                emailerThread.run();
                return true;
            } else if (noticeType == "sms") {
                InternetAddress[] addresses;
                try {
                    addresses = InternetAddress.parse("gladius.notification@gmail.com");
                } catch (AddressException e) {
                    return false;
                }
                SMSerThread smserThread = new SMSerThread("NavUP Notification", message, addresses);
                smserThread.run();
                return true;
            }
        } else { //validation failed
            return false;
        }
        return false;
    }

    /**
     * Public interface to send a message to a array list of users.
     * @param userIDs array list of IDs of the users the message needs to be sent to.
     * @param message a message in string format that needs to be sent to the user.
     * @param noticeType the type of notification sms(to be determined), email or push notification.
     * @return will return true if the message was successfully sent and false if message failed to send.
     */
    /*
     * TODO: First it should validate the parameters sent to it with the validation function, if the validation function returns true it should continue.
     * TODO: Second check the type of message and gather all the information needed to send that type of notification.
     * TODO:    If type is email it should create an array of InternetAddresses(http://stackoverflow.com/questions/13854037/send-mail-to-multiple-recipients-in-java#answer-13854096) from the emails of the users using the getEmail function.
     * TODO:    If type is sms it should create an ArrayList of phone numbers which it needs to get from the getNumber function.
     * TODO:    If type push... we still need to confirm with integration on how that would work.
     * TODO: Third it should send the data collected for the specific notification type to the relevant handler for that notification type.
     * TODO:    If type is email a new EmailerThread should be created and run.
     * TODO:    If type is sms a new SMSerThread should be created and run. The format of addresses should be xxxxxxxxxx@<Email_to_SMS_API_Domain> where xxxxxxxxxx is the users phone number and <Email_to_SMS_API_Domain> is the variable with the same name.
     * TODO:    If type is push... we still need to confirm with integration on how that would work.
     * TODO: Fourth return true or false depending if all of this was successful or not.
     */
    public boolean sendNotification(ArrayList<Long> userIDs, String message, String noticeType) {

        return false;
    }

    /**
     * Private function to validate the data sent to the sendNotification functions. This function checks whether there
     * are IDs in the array list, and whether the message has content and if the notice type is one of the three
     * possible types.
     * @param userIDs array list of IDs of the users the message needs to be sent to.
     * @param message a message in string format that needs to be sent to the user.
     * @param noticeType the type of notification normal or urgent.
     * @return will return "valid" if all parameters are valid and an error message if the paramaters are invalid.
     */
    /*
     * TODO: This function should be able to validate all fields recived by the sendNotification functions.
     * TODO: userIDs validation, the list should not be empty and values in list should not be empty/null.
     * TODO: notificationType validation, should be a valid notification type ("email", "sms" or "push")
     * TODO: message validation, the message should not be empty.
     */
    private String validate(ArrayList<Long> userIDs, String message, String noticeType) {
        if(message == "")
            return "invalid";
        if (userIDs.isEmpty())
            return "invalid";
        if(!(noticeType == "email" || noticeType == "sms" || noticeType == "push"))
            return "invalid";
        return "valid";
    }

    /**
     * Private function to get the email of a user given their ID. The email will be retrieved from the users module.
     * @param userID the ID of the user whose email is needed.
     * @return returns a string containing a users email if found or null if the user's email could not be found.
     */
    /*
     * TODO: This function should return a email given a userID, this function should query the users module for this information.
     * TODO: Wait for users module to write a proper interface.
     * TODO: Remove the exception handling, users module should take care of these exceptions.
     */
    private String getEmail(Long userID) {
        //try {
            return "u15029779@tuks.co.za";//user.getEmail(userID.toString());
        //} catch (SQLException e) { //Catching exceptions the users module is supposed to catch.
        //    return null;
        //}

    }

    /**
     * Private function to get the phone number of a user given their ID. The phone number will be retrieved  from the
     * users module.
     * @param userID the ID of the user whose phone number is needed.
     * @return returns a string containing a users phone number if found or null if the user's phone number could not
     * be found.
     */
    /*
     * TODO: This function should return a phone number given a userID, this function should query the users module for this information.
     * TODO: Wait for users module to write a proper interface.
     */
    private String getNumber(Long userID) {
        return null;
    }

    private class EmailerThread implements Runnable {
        String subject;
        String content;
        InternetAddress[] addresses;
        Mailer mailer;
        EmailerThread(String _subject, String _content,  InternetAddress[] _addresses) {
            subject = _subject;
            content = _content;
            addresses = _addresses;
            mailer = new Mailer(Email_From, Email_SMTPHost, Email_SMTPPort, Email_SMTPAuth, Email_SMTPAuthUsername, Email_SMTPAuthPassword);
        }

        public void run() {
            mailer.sendNotification(subject, content, addresses);
        }
    }

    private class SMSerThread implements Runnable {
        String subject;
        String message;
        InternetAddress[] addresses;
        Mailer smser;
        public SMSerThread(String _subject, String _message, InternetAddress[] _addresses) {
            subject = _subject;
            message = _message;
            addresses = _addresses;
            smser = new Mailer(SMS_From, SMS_SMTPHost, SMS_SMTPPort, SMS_SMTPAuth, SMS_SMTPAuthUsername, SMS_SMTPAuthPassword);
        }

        public void run() {
            //smser.sendNotification(subject, message, addresses);
            RaspSMSAdapter.raspSMSAdapter(smser, subject, message, addresses);
        }
    }
}