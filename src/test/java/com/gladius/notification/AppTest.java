package com.gladius.notification;

import com.sun.nio.sctp.NotificationHandler;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for GladiOS Notification Module.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testBasicSend_Email() {
        NotificationInterface notification = NotificationInterface.getInstance();
            if (notification.sendNotification(1, "<h1>Hello World Notification</h1><br><p>This is a hello world test email.</p>", "email"))  {
                assertTrue("Successfully sent email notification.", true);
            } else {
                fail("Failed to send email notification.");
            }
    }

    public void testBasicSend_SMS() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification(1, "Hello World SMS!", "sms"))  {
            assertTrue("Successfully sent sms notification.", true);
        } else {
            fail("Failed to send email notification.");
        }
    }

    public void testEmptyMessage_Email() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification(1, "", "email"))  {
            fail("Empty email message validation failed.");
        } else {
            assertTrue("Empty email message check succeeded.", true);
        }
    }

    public void testEmptyMessage_SMS() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification(1, "", "sms"))  {
            fail("Empty SMS message validation failed.");
        } else {
            assertTrue("Empty SMS message validation succeeded.", true);
        }
    }

    public void testNonExistingID_Email() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification(-1, "Test Non existing email address.", "email"))  {
            fail("Non existing user validation failed (email).");
        } else {
            assertTrue("Non existing user validation succeeded (email).", true);
        }
    }

    public void testNonExistingID_SMS() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification(-1, "Test non existing phone number", "sms"))  {
            fail("Non existing user validation failed (SMS).");
        } else {
            assertTrue("Non existing user validation succeeded (SMS).", true);
        }
    }

    public void testNonExistingNotificationType() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification(1, "Message Heading", "blah"))  {
            fail("Non existing notification type validation failed.");
        } else {
            assertTrue("Non existing notification type validation succeeded.", true);
        }
    }
}
