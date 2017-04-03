package com.gladius.notification;

import com.sun.nio.sctp.NotificationHandler;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.imageio.IIOException;
import java.io.IOException;

/**
 * Unit test for simple App.
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

    /**
     * Rigourous Test :-)
     */
    public void testBasicSendEmail() {
        NotificationInterface notification = NotificationInterface.getInstance();
            if (notification.sendNotification(1, "Hello World Notification", "email"))  {
                assertTrue("Successfully sent email notification.", true);
            } else {
                fail("Failed to send email notification.");
            }
    }

//    public void testEmptyEmail() {
//        NotificationInterface notification = NotificationInterface.getInstance();
//        if (notification.sendNotification(1, "", "email"))  {
//            fail("Empty message validation failed.");
//        } else {
//            assertTrue("Empty message succeeded.", true);
//        }
//    }
//
//    public void testNonExistingID() {
//        NotificationInterface notification = NotificationInterface.getInstance();
//        if (notification.sendNotification(-1, "Test", "email"))  {
//            fail("Non existing user validation failed.");
//        } else {
//            assertTrue("Non existing user validation succeeded.", true);
//        }
//    }

    public void testNonExistingNotificationType() {
        NotificationInterface notification = NotificationInterface.getInstance();
        if (notification.sendNotification(1, "Test", "blah"))  {
            fail("Non existing notification type validation failed.");
        } else {
            assertTrue("Non existing notification type validation succeeded.", true);
        }
    }
}
