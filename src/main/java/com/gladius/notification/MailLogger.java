package com.gladius.notification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * The MailLogger class logs all emails and push notifications sent by the NotificationInterface and Mailer classes.
 * Please make sure to change the Username and Password fields to connect to the database.
 * Create database command: createdb -h localhost -p 5432 -U postgres dbname
 *
 * @author  GladiOS Notification Module Team
 * @version 1.0
 * @since   22-03-2017
 */
public class MailLogger{
	final static String username = "GladiOSNotification";
	final static String password = "123456789";
	/**
	 * This function logs a successful email sent to the database.
	 * @param femail The email address the email was sent to.
	 * @param fmessage The message that was emailed.
	 */
	public static void logMailSent(String femail,String fmessage) {
	   Connection c = null;
      Statement stmt = null;
      String email = femail;
      String message =fmessage;
      boolean sent = false;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/NotificationDB",
            username, password);
         c.setAutoCommit(false);

         stmt = c.createStatement();
         String sql = "INSERT INTO SUCCESS (EMAIL,MESSAGE) "
               + "VALUES ('"+email+"','"+message+"');";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }

//       c = null;
//       stmt = null;
//       try {
//       Class.forName("org.postgresql.Driver");
//         c = DriverManager
//            .getConnection("jdbc:postgresql://localhost:5432/NotificationDB",
//            username, password);
//         c.setAutoCommit(false);
//         System.out.println("Opened database successfully");
//
//         stmt = c.createStatement();
//         ResultSet rs = stmt.executeQuery( "SELECT * FROM SUCCESS;" );
//         while ( rs.next() ) {
//            int id = rs.getInt("id");
//            email = rs.getString("email");
//             message = rs.getString("message");
//
//            System.out.println( "ID = " + id );
//            System.out.println( "EMAIL = " + email );
//            System.out.println( "MESSAGE = " + message );
//            System.out.println();
//         }
//         rs.close();
//         stmt.close();
//         c.close();
//       } catch ( Exception e ) {
//         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//         System.exit(0);
//       }

	}

	/**
	 * This function logs an error that occured while sending an email.
	 * @param femail The email address that the email was attempted to be sent to.
	 * @param fmessage The error message.
	 */
	public  static void logMailError(String femail,String fmessage) {
		//Log the errors incase any occur
	  Connection c = null;
      Statement stmt = null;
      String email = femail;
      String message =fmessage;
      boolean sent = false;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/NotificationDB",
            username, password);
         c.setAutoCommit(false);

         stmt = c.createStatement();
         String sql = "INSERT INTO FAILED (EMAIL,MESSAGE) "
               + "VALUES ('"+email+"','"+message+"');";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }


//       c = null;
//       stmt = null;
//       try {
//       Class.forName("org.postgresql.Driver");
//         c = DriverManager
//            .getConnection("jdbc:postgresql://localhost:5432/NotificationDB",
//            "GaldiOSNotification", password);
//         c.setAutoCommit(false);
//         System.out.println("Opened database successfully");
//
//         stmt = c.createStatement();
//         ResultSet rs = stmt.executeQuery( "SELECT * FROM FAILED;" );
//         while ( rs.next() ) {
//            int id = rs.getInt("id");
//            email = rs.getString("email");
//             message = rs.getString("message");
//
//            System.out.println( "ID = " + id );
//            System.out.println( "email = " + email );
//            System.out.println( "message = " + message );
//            System.out.println();
//         }
//         rs.close();
//         stmt.close();
//         c.close();
//       } catch ( Exception e ) {
//         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//         System.exit(0);
//       }
	}

/**
 * This function logs a push notification.
 * @param fuserid The userID that the email needs to be sent to.
 * @param fmessage The push notification message content.
 */
  public void logPushNotification(int fuserid,String fmessage) {
	  Connection c = null;
      Statement stmt = null;
      int user_id = fuserid;
      String message =fmessage;
      boolean sent = false;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/NotificationDB",
            username, password);
         c.setAutoCommit(false);

         stmt = c.createStatement();
         String sql = "INSERT INTO PUSH (User_ID,MESSAGE) "
               + "VALUES ('"+fuserid+"','"+message+"');";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }


//       c = null;
//       stmt = null;
//       try {
//       Class.forName("org.postgresql.Driver");
//         c = DriverManager
//            .getConnection("jdbc:postgresql://localhost:5432/NotificationDB",
//            username, password);
//         c.setAutoCommit(false);
//         System.out.println("Opened database successfully");
//
//         stmt = c.createStatement();
//         ResultSet rs = stmt.executeQuery( "SELECT * FROM PUSH;" );
//         while ( rs.next() ) {
//            int id = rs.getInt("id");
//            fuserid= rs.getInt("user_id");
//             message = rs.getString("message");
//
//            System.out.println( "ID = " + id );
//            System.out.println( "User_ID = " + user_id );
//            System.out.println( "MESSAGE = " + message );
//            System.out.println();
//         }
//         rs.close();
//         stmt.close();
//         c.close();
//       } catch ( Exception e ) {
//         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//         System.exit(0);
//       }
  }
}


