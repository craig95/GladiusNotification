package com.gladius.notification;

//import Users.Users;
import net.sf.json.*;
import org.apache.commons.io.IOUtils;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.io.InputStream;
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
     * Instance of the user module class that is needed to retrieve emails.
     */
    //private Users user;
    /**
     * The email from wich the Email to SMS API expects to recive emails.
     */
    private String SMS_From;
    /**
     * The SMTP server URL that will handle the emails for the Email to SMS Api.
     */
    private String SMS_SMTPHost;
    /**
     * The SMTP server port that will handle the emails for the Email to SMS Api.
     */
    private String SMS_SMTPPort;
    /**
     * Indicates if the SMTP server requires authentication that will handle the emails for the Email to SMS Api.
     */
    private boolean SMS_SMTPAuth;
    /**
     * Username for SMTP server that will handle the emails for the Email to SMS Api.
     */
    private String SMS_SMTPAuthUsername;
    /**
     * Password for SMTP server that will handle the emails for the Email to SMS Api.
     */
    private String SMS_SMTPAuthPassword;
    /**
     * The domain of the Email to SMS API.
     */
    private String Email_to_SMS_API_Domain;

    /**
     * The email from which the email notifications will be sent.
     */
    private String Email_From;
    /**
     * The SMTP server URL that will handle the email notification sending.
     */
    private String Email_SMTPHost;
    /**
     * The SMTP server port that will handle the email notification sending.
     */
    private String Email_SMTPPort;
    /**
     * Indicates if the SMTP server requires authentication that will handle the email notification sending.
     */
    private boolean Email_SMTPAuth;
    /**
     * Username for SMTP server that will handle the email notification sending.
     */
    private String Email_SMTPAuthUsername;
    /**
     * Password for SMTP server that will handle the email notification sending.
     */
    private String Email_SMTPAuthPassword;
    /**
     * Constructor
     *
     * Private constructor for the Notification class. The private constructor prevents instantiation from other
     * classes (Singleton Design Pattern). It will read in all the needed variables from the config file.
     */
    private NotificationInterface() {
        //user = new Users();
        InputStream input = NotificationInterface.class.getClassLoader().getResourceAsStream("configFile.txt");
        String jsonTxt = null;
        try {
            jsonTxt = IOUtils.toString(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);

        SMS_From = json.getString( "SMS_From" );
	    SMS_SMTPHost = json.getString( "SMS_SMTPHost" );
        SMS_SMTPPort =  json.getString( "SMS_SMTPPort" );
	    SMS_SMTPAuth = json.getBoolean( "SMS_SMTPAuth" );
        SMS_SMTPAuthUsername = json.getString( "SMS_SMTPAuthUsername" );
	    SMS_SMTPAuthPassword = json.getString( "SMS_SMTPAuthPassword" );
        Email_to_SMS_API_Domain = json.getString( "Email_to_SMS_API_Domain" );
	    
	    Email_From = json.getString( "Email_From" );
        Email_SMTPHost = json.getString( "Email_SMTPHost" );
	    Email_SMTPPort = json.getString( "Email_SMTPPort" );
        Email_SMTPAuth = json.getBoolean( "Email_SMTPAuth" );
	    Email_SMTPAuthUsername = json.getString( "Email_SMTPAuthUsername" );
        Email_SMTPAuthPassword = json.getString( "Email_SMTPAuthPassword" );
	
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
    public boolean sendNotification(long userID, String message, String noticeType) {
        ArrayList<Long> tempArray = new ArrayList<Long>();
        tempArray.add(userID);
		return sendNotification(tempArray, message, noticeType);
    }

    /**
     * Public interface to send a message to a array list of users.
     * @param userIDs array list of IDs of the users the message needs to be sent to.
     * @param message a message in string format that needs to be sent to the user.
     * @param noticeType the type of notification sms(to be determined), email or push notification.
     * @return will return true if the message was successfully sent and false if message failed to send.
     */
   public boolean sendNotification(ArrayList<Long> userIDs, String message, String noticeType) {
        /*Validate the paramaters*/
        String valid = validate(userIDs, message, noticeType);
        if (valid == "valid notification") { //validation succeeded
            if (noticeType == "email") {
                InternetAddress[] addresses = new InternetAddress[userIDs.size()];
                String tempString = "";
                try
                {
                    for(int i = 0; i < userIDs.size(); i++)
                    {
                        InternetAddress[] tempAddress = InternetAddress.parse(getEmail(userIDs.get(i)));
                        addresses[i] = tempAddress[0];
                    }
                } 
                catch (Exception e) {
                    return false;
                }
                EmailerThread emailerThread = new EmailerThread("NavUP Notification", message, addresses);
                emailerThread.run();
                return true;
            } 
            else if (noticeType == "sms") {
                InternetAddress[] addresses = new InternetAddress[userIDs.size()];
                try
                {
                  for(int i = 0; i < userIDs.size(); i++)
                  {
                      String tempNumber = getNumber(userIDs.get(i));
                      if (tempNumber == null) {
                          return false;
                      }
                      InternetAddress[] tempAddress = InternetAddress.parse( tempNumber + "@" + Email_to_SMS_API_Domain);
                      addresses[i] = tempAddress[0];
                  }
                }
                catch (Exception e) {
                    return false;
                }
                SMSerThread smserThread = new SMSerThread("NavUP Notification", message, addresses);
                smserThread.run();
                return true;
            }
            else if (noticeType == "push") {
                String json_string = "{'message':'" + message + "'}";
                PusherThread pusherThread = new PusherThread(userIDs, json_string);
                pusherThread.run();
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
     * @param userIDs array list of IDs of the users the message needs to be sent to.
     * @param message a message in string format that needs to be sent to the user.
     * @param noticeType the type of notification normal or urgent.
     * @return will return "valid" if all parameters are valid and an error message if the paramaters are invalid.
     */
    private String validate(ArrayList<Long> userIDs, String message, String noticeType) {
        if(message == null || message == "")
            return "invalid notification(No Message)";
        if (userIDs == null || userIDs.isEmpty())
            return "invalid  notification(No Users)";
        if (noticeType != null) {
            if (!(noticeType == "email" || noticeType == "sms" || noticeType == "push"))
                return "invalid notification(No Type)";
        } else {
            return "invalid notification(Unknown Error)";
        }
        return "valid notification";
    }

    /**
     * Private function to get the email of a user given their ID. The email will be retrieved from the users module.
     * @param userID the ID of the user whose email is needed.
     * @return returns a string containing a users email if found or null if the user's email could not be found.
     */
    private String getEmail(Long userID) {
        if (userID == 1) {
            return "u15029779@tuks.co.za";
        } else if (userID == 2) {
            return "u15059538@tuks.co.za";
        } else {
            return null;
        }

    }

    /**
     * Private function to get the phone number of a user given their ID. The phone number will be retrieved  from the
     * users module.
     * @param userID the ID of the user whose phone number is needed.
     * @return returns a string containing a users phone number if found or null if the user's phone number could not
     * be found.
     */
    private String getNumber(Long userID) {
        if (userID == 1) {
            return "0712526999";
        } else if (userID == 2) {
            return "0737147635";
        } else {
            return null;
        }
    }

    /**
     * Private class that handles email sending. It will create a new Mailer object and use it to send emails.
     */
    private class EmailerThread implements Runnable {
        /**
         * Email subject.
         */
        String subject;
        /**
         * The content of the message to be sent.
         */
        String content;
        /**
         * The email addresses stored in an array as InternetAddresses.
         */
        InternetAddress[] addresses;
        /**
         * The mailer object that will send the email/s.
         */
        Mailer mailer;
        /**
         * Constructor
         *
         * This is the constructor for the EmailerThread. It initializes all the class variables with the given
         * parameters.
         */
        EmailerThread(String _subject, String _content,  InternetAddress[] _addresses) {
            subject = _subject;
            content = _content;
            addresses = _addresses;
            mailer = new Mailer(Email_From, Email_SMTPHost, Email_SMTPPort, Email_SMTPAuth, Email_SMTPAuthUsername, Email_SMTPAuthPassword);
        }

        /**
         * The EmailerThread's run method. It will simply call the sendNotification function of the Mailer class.
         */
        public void run() {
            mailer.sendNotification(subject, content, addresses);
        }
    }

    /**
     * Private class that handles SMS sending. It will create a new Mailer object and use it to send emails to the Email
     * to SMS API.
     */
    private class SMSerThread implements Runnable {
        /**
         * SMS subject.
         */
        String subject;
        /**
         * The content of the message to be sent.
         */
        String message;
        /**
         * The email addresses stored in an array as InternetAddresses. These email addresses are in the format
         * "xxxxxxxxxx@<Email_to_SMS_API_Domain>", this format is required by most Email to SMS APIs.
         */
        InternetAddress[] addresses;
        /**
         * The mailer object that will send the email/s to the Email to SMS API.
         */
        Mailer smser;
        /**
         * Constructor
         *
         * This is the constructor for the SMSerThread. It initializes all the class variables with the given
         * parameters.
         */
        public SMSerThread(String _subject, String _message, InternetAddress[] _addresses) {
            subject = _subject;
            message = _message;
            addresses = _addresses;
            smser = new Mailer(SMS_From, SMS_SMTPHost, SMS_SMTPPort, SMS_SMTPAuth, SMS_SMTPAuthUsername, SMS_SMTPAuthPassword);
        }

        /**
         * The SMSerThread's run method. Because we have a custom Email to SMS API, we send the relevant data to an
         * adapter wich converts the data to the way our custom API expects it.
         */
        public void run() {
            //smser.sendNotification(subject, message, addresses);
            RaspSMSAdapter.raspSMSAdapter(smser, subject, message, addresses);
        }
    }

    /**
     * Private class that handles Push Notifications. If the server supports it.
     */
    private class PusherThread implements Runnable {
        ArrayList<Long> userIDs;
        String jsonString;
        public PusherThread(ArrayList<Long> _userIDs, String _jsonString) {
            userIDs = _userIDs;
            jsonString = _jsonString;
        }

        public void run() {
            for (Long userID : userIDs) {
                //WebServer.sendPushNotification(userID, jsonString);
                JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonString);
                MailLogger.logPushNotification(userID, json.getString("message"));
            }
        }
    }
}
