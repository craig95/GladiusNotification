package com.gladius.notification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import org.postgresql.Driver;
public class MailLogger{

	public static void logMailSent(int fid,String femail,String fmessage) {
//	    Connection c = null;
//      Statement stmt = null;
//      String email = femail;
//      int id = fid;
//      String message =fmessage;
//      boolean sent = false;
//      try {
//         Class.forName("org.postgresql.Driver");
//         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbmail", "postgres", "Haasbroek11");
//         c.setAutoCommit(false);
//         System.out.println("Opened database successfully");
//
//         stmt = c.createStatement();
//         String sql = "INSERT INTO SENT (ID,EMAIL,MESSAGE) "
//               + "VALUES ('"+id+"','"+email+"','"+message+"');";
//         stmt.executeUpdate(sql);
//         stmt.close();
//         c.commit();
//         c.close();
//      } catch (Exception e) {
//         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//         System.exit(0);
//      }
//      System.out.println("Records created successfully");
//
//
//       c = null;
//       stmt = null;
//       try {
//       Class.forName("org.postgresql.Driver");
//         c = DriverManager
//            .getConnection("jdbc:postgresql://localhost:5432/dbmail",
//            "postgres", "Haasbroek11");
//         c.setAutoCommit(false);
//         System.out.println("Opened database successfully");
//
//         stmt = c.createStatement();
//         ResultSet rs = stmt.executeQuery( "SELECT * FROM SENT;" );
//         while ( rs.next() ) {
//            id = rs.getInt("id");
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

	public static void logMailError(int fid,String femail,String fmessage) {
		//Log the errors incase any occur
//	  Connection c = null;
//      Statement stmt = null;
//      String email = femail;
//      int id = fid;
//      String message =fmessage;
//      boolean sent = false;
//      try {
//         Class.forName("org.postgresql.Driver");
//         c = DriverManager
//            .getConnection("jdbc:postgresql://localhost:5432/dbmail",
//            "postgres", "Haasbroek11");
//         c.setAutoCommit(false);
//         System.out.println("Opened database successfully");
//
//         stmt = c.createStatement();
//         String sql = "INSERT INTO FAILED (ID,EMAIL,MESSAGE) "
//               + "VALUES ('"+id+"','"+email+"','"+message+"');";
//         stmt.executeUpdate(sql);
//         stmt.close();
//         c.commit();
//         c.close();
//      } catch (Exception e) {
//         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//         System.exit(0);
//      }
//      System.out.println("Records created successfully");
//
//
//       c = null;
//       stmt = null;
//       try {
//       Class.forName("org.postgresql.Driver");
//         c = DriverManager
//            .getConnection("jdbc:postgresql://localhost:5432/dbmail",
//            "postgres", "Haasbroek11");
//         c.setAutoCommit(false);
//         System.out.println("Opened database successfully");
//
//         stmt = c.createStatement();
//         ResultSet rs = stmt.executeQuery( "SELECT * FROM FAILED;" );
//         while ( rs.next() ) {
//            id = rs.getInt("id");
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
}

