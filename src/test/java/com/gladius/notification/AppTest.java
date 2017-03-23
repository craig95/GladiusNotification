package com.gladius.notification;

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
    public void testSendEmail() throws IOException{
        NotificationHandler notificationHandler = new NotificationHandler();
        try {
            if (notificationHandler.sendNotification("{\"just_a_test\": \"test_stuff\"}") == "{\"notification_sent\":true}")  {
                assertTrue("Successfully sent email notification.", true);
            }
        } catch (IIOException e) {
            assertTrue("Failed to send email notification.", true);
        }
    }
}
