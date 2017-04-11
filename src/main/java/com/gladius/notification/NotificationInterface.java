/**
 * The NotificationInterface class provides the interface for the entire notification module. It provides two public
 * functions with which other modules can interface. The two functions have the same names but one sends a notification
 * to one user and the other to a list of users. To create
 *
 * @author  GladiOS Notification Module Team
 * @version 1.0
 * @since   22-03-2017
 */
package com.gladius.notification;

import Users.Users;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationInterface {
    /**
     * The default Mailer class that will handle sending of emails.
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
     *
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
                defaultMailer.sendNotification("NavUP Notification", message, addresses);
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
     *
     * TODO: First it should validate the parameters sent to it with the validation function, if the validation function returns true it should continue.
     * TODO: Second check the type of message and gather all the information needed to send that type of notification.
     * TODO:    If type is email it should create an array of InternetAddresses(http://stackoverflow.com/questions/13854037/send-mail-to-multiple-recipients-in-java#answer-13854096) from the emails of the users using the getEmail function.
     * TODO:    If type is sms it should create an ArrayList of phone numbers which it needs to get from the getNumber function.
     * TODO:    If type push... we still need to confirm with integration on how that would work.
     * TODO: Third it should send the data collected for the specific notification type to the relevant handler for that notification type.
     * TODO:    If type is email the sendNotification function of Mailer should be called and be given the subject, the message and the array of InternetAddresses
     * TODO:    If type is sms the sendNotification function of the SMSer should be called and be given the message and ArrayList of numbers.
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
     *
     * TODO: This function should be able to validate all fields recived by the sendNotification functions.
     * TODO: userIDs validation, the list should not be empty and values in list should not be empty/null.
     * TODO: notificationType validation, should be a valid notification type ("email", "sms" or "push")
     * TODO: message validation, the message should not be empty.
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
     * Private function to get the email of a user given their ID. The email will be retrieved from the users module.
     * @param userID the ID of the user whose email is needed.
     * @return returns a string containing a users email if found or null if the user's email could not be found.
     *
     * TODO: This function should return a email given a userID, this function should query the users module for this information.
     * TODO: Wait for users module to write a proper interface.
     * TODO: Remove the exception handling, users module should take care of these exceptions.
     */
    private String getEmail(Long userID) {
        try {
            return user.getEmail(userID.toString());
        } catch (SQLException e) { //Catching exceptions the users module is supposed to catch.
            return null;
        }

    }

    /**
     * Private function to get the phone number of a user given their ID. The phone number will be retrieved  from the
     * users module.
     * @param userID the ID of the user whose phone number is needed.
     * @return returns a string containing a users phone number if found or null if the user's phone number could not
     * be found.
     *
     * TODO: This function should return a phone number given a userID, this function should query the users module for this information.
     * TODO: Wait for users module to write a proper interface.
     */
    private String getNumber(Long userID) {
        return null;
    }

}