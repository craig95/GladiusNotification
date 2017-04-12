package com.gladius.notification;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * The RaspSMSAdapter is a adapter that adapts the expected input most email to SMS APIs expect and adapts it to be
 * compatible with our RaspSMS(Raspberry Pi SMS) email to SMS API. Our API expects the phone number to be in the
 * subject and the email be sent to gladius.notification@gmail.com.
 *
 * @author  GladiOS Notification Module Team
 * @version 1.0
 * @since   11-04-2017
 */
public class RaspSMSAdapter {
    /**
     * This is the static function that will adapt the input as required by the RaspSMS API and then pass the
     * adapted input to the mailer object it received.
     * @param smser the mailer object that will send the email to the RaspSMS API.
     * @param subject the subject that was given for this message.
     * @param content the message to be sent.
     * @param addresses the email addresses formatted in the way most email to SMS APIs require.
     */
    public static void raspSMSAdapter(Mailer smser, String subject, String content, InternetAddress[] addresses) {
        InternetAddress[] gladiusEmail;
        try {
            gladiusEmail = InternetAddress.parse("gladius.notification@gmail.com");
        } catch (AddressException e) {
            return;
        }
        for (InternetAddress address: addresses) {
            String phoneNumber = address.toString();
            phoneNumber = phoneNumber.substring(0, phoneNumber.indexOf('@'));
            smser.sendNotification(phoneNumber, subject+ ". " + content, gladiusEmail);
        }
    }
}
