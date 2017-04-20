package com.gladius.notification;

import com.sun.nio.sctp.NotificationHandler;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

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
            if (notification.sendNotification("user1", "<h1>Hello World Notification</h1><br><p>This is a hello world test email.</p>", "email"))  {
                assertTrue("Successfully sent email notification.", true);
            } else {
                fail("Failed to send email notification.");
            }
    }

    public void testBasicSend_SMS() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification("user1", "Hello World SMS!", "sms"))  {
            assertTrue("Successfully sent sms notification.", true);
        } else {
            fail("Failed to send SMS notification.");
        }
    }

    public void testBasicSend_Push() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification("user1", "Push Notification Test", "push"))  {
            assertTrue("Successfully sent Push notification.", true);
        } else {
            fail("Failed to send Push notification.");
        }
    }

    public void testEmptyMessage_Email() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification("user1", "", "email"))  {
            fail("Empty email message validation failed.");
        } else {
            assertTrue("Empty email message check succeeded.", true);
        }
    }

    public void testEmptyMessage_SMS() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification("user1", "", "sms"))  {
            fail("Empty SMS message validation failed.");
        } else {
            assertTrue("Empty SMS message validation succeeded.", true);
        }
    }

    public void testEmptyMessage_Push() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification("user1", "", "push"))  {
            fail("Empty Push message validation failed.");
        } else {
            assertTrue("Empty Push message validation succeeded.", true);
        }
    }

    public void testNonExistingID_Email() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification("non_user", "Test Non existing user (email).", "email"))  {
            fail("Non existing user validation failed (email).");
        } else {
            assertTrue("Non existing user validation succeeded (email).", true);
        }
    }

    public void testNonExistingID_SMS() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification("non_user", "Test non existing user (SMS).", "sms"))  {
            fail("Non existing user validation failed (SMS).");
        } else {
            assertTrue("Non existing user validation succeeded (SMS).", true);
        }
    }

    public void testNonExistingNotificationType() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification("user", "Message Heading", "blah"))  {
            fail("Non existing notification type validation failed.");
        } else {
            assertTrue("Non existing notification type validation succeeded.", true);
        }
    }

    public void testBatchEmail() {
        NotificationInterface notification = NotificationInterface.getInstance();
        ArrayList<String> usernames = new ArrayList<String>();
        usernames.add("user1");
        usernames.add("user2");
        if (notification.sendNotification(usernames, "NavUP Batch Email Notification Test", "email"))  {
            assertTrue("Batch email notifications succeeded.", true);
        } else {
            fail("Batch email notifications failed");
        }
    }

    public void testBatchSMS() {
        NotificationInterface notification = NotificationInterface.getInstance();
        ArrayList<String> usernames = new ArrayList<String>();
        usernames.add("user1");
        usernames.add("user2");
        if (notification.sendNotification(usernames, "NavUP Batch SMS Notification Test", "sms")) {
            assertTrue("Batch sms notifications succeeded.", true);
        } else {
            fail("Batch sms notifications failed");
        }
    }

    public void testBatchPush() {
        NotificationInterface notification = NotificationInterface.getInstance();
        ArrayList<String> usernames = new ArrayList<String>();
        usernames.add("user1");
        usernames.add("user2");
        if (notification.sendNotification(usernames, "NavUP Batch Push Notification Test", "push")) {
            assertTrue("Batch push notifications succeeded.", true);
        } else {
            fail("Batch Push notifications failed");
        }
    }
}
