package com.gladius.notification;
import Users.Users;

import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationInterface {
    /**
     * The default Mailer class that will handle sending of emails for non batch jobs.
     */
    private Mailer defaultMailer;
    /**
     * Instance of the user module class that is needed to retrive emails.
     */
    private Users user;

    /**
     * Constructor
     *
     * Private constructor for the Notification class. The private constructor prevents instantiation from other
     * classes (Singleton Design Pattern).
     */
    private NotificationInterface() {
        defaultMailer = new Mailer("gladius.notification@gmail.com", "smtp.gmail.com", "587", true, "gladius.notification@gmail.com", "9FM-mZD-wtC-trd");
        user = new Users();
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
     *
     * @return will return an instance of NotificationInterface.
     */
    public static NotificationInterface getInstance() {
        return NotificationHolder.INSTANCE;
    }

    /**
     * Public interface to send a message to a single user.
     *
     * @param userID        the ID of the user the message needs to be sent to.
     * @param message       a message in string format that needs to be sent to the user.
     * @param noticeType    the type of notification sms(to be determined), email or push notification.
     *
     * @return will return true if the message was successfully sent and false if message failed to send.
     */
    public boolean sendNotification(long userID, String message, String noticeType) {
        ArrayList<Long> tempArray = new ArrayList<Long>();
        tempArray.add(userID);
        String valid = validate(tempArray, message, noticeType);
        if (valid == "valid") { //validation succeeded
            if (noticeType == "email") {
                defaultMailer.sendNotification("NavUP Notification", message, getEmail(userID));
                return true;
            }
        } else { //validation failed
            return false;
        }
        return false;
    }

    /**
     * Public interface to send a message to a array list of users.
     *
     * @param userIDs       array list of IDs of the users the message needs to be sent to.
     * @param message       a message in string format that needs to be sent to the user.
     * @param noticeType    the type of notification sms(to be determined), email or push notification.
     *
     * @return will return true if the message was successfully sent and false if message failed to send.
     */
   public boolean sendNotification(ArrayList<Long> userIDs, String message, String noticeType) {
        /*Validate the paramaters*/
        ArrayList<Long> tempArray = new ArrayList<Long>();
        tempArray.add(userID);
        String valid = validate(userIDs, message, noticeType);
        if (valid == "valid notification") { //validation succeeded
            if (noticeType == "email") {
                InternetAddress[] addresses;
                try {
                    addresses.addRecipients(Message.RecipientType.CC, userIDs);
                } 
                catch (AddressException e) {
                    return false;
                }
                EmailerThread emailerThread = new EmailerThread("NavUP Notification", message, addresses);
                emailerThread.run();
                return true;
            } 
            else if (noticeType == "sms") {
                InternetAddress[] addresses;
                try {
                    addresses = InternetAddress.parse("gladius.notification@gmail.com");
                } 
                catch (AddressException e) {
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
     * Private function to validate the data sent to the sendNotification functions. This function checks whether there
     * are IDs in the array list, and whether the message has content and if the notice type is one of the three
     * possible types.
     *
     * @param userIDs       array list of IDs of the users the message needs to be sent to.
     * @param message       a message in string format that needs to be sent to the user.
     * @param noticeType    the type of notification normal or urgent.
     *
     * @return will return "valid" if all parameters are valid and an error message if the paramaters are invalid.
     */
    private String validate(ArrayList<Long> userIDs, String message, String noticeType) {
        return "valid";
//        if (userIDs.isEmpty()) {
//            return "no users";
//        }
//        for (long userID : userIDs) {
//
//        }
//        return null;
    }

    /**
     * Private function to get the email of a user given their ID. The email will be retried from the users module.
     *
     * @param userID       the ID of the user whose email is needed.
     *
     * @return returns a string containing a users email if found or null if the user's email could not be found.
     */
    private String getEmail(Long userID) {
        try {
            return user.getEmail(userID.toString());
        } catch (SQLException e) { //Catching exceptions the users module is supposed to catch.
            return null;
        }

    }

}
