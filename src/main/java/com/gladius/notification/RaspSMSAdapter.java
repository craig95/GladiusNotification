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
     * @param mailer the mailer object that will send the email.
     * @param subject the subject that was given for this message.
     * @param content the message to be sent.
     * @param addresses the email addresses formatted in the way most email to SMS APIs require.
     */
    public static void raspSMSAdapter(Mailer mailer, String subject, String content, InternetAddress[] addresses) {
        try {
            addresses = InternetAddress.parse("gladius.notification@gmail.com");
        } catch (AddressException e) {
            return;
        }
        mailer.sendNotification("0712526999", "Hello World form the RaspSMSAdapter Class", addresses);
    }
}
