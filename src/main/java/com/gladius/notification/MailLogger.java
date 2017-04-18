package com.gladius.notification;
//HD Haasbroek
//JAVAC *.JAVA
//en om te run:
//java -cp postgresql-9.2-1002.jar;C:\Users\HD\Desktop\301 MailLogger
//maar mens run net die class wat die fucntions call tho, so
//maak dit java -cp postgresql-9.2-1002.jar;C:\Users\HD\Desktop\301 "callerClass"
//RUN EERS DIE PostgreSQLJDBC om die tables te create op die machine
//createdb -h localhost -p 5432 -U postgres dbname 
//in bin folder van postgressql
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
public class MailLogger{

*/	public void logMailSent(int fid,String femail,String fmessage) {/*
	   Connection c = null;
      Statement stmt = null;
      String email = femail;
      int id = fid;
      String message =fmessage;
      boolean sent = false;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/301db",
            "postgres", "Haasbroek11");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "INSERT INTO SUCCESS (ID,EMAIL,MESSAGE) "
               + "VALUES ('"+id+"','"+email+"','"+message+"');";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");


       c = null;
       stmt = null;
       try {
       Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/301db",
            "postgres", "Haasbroek11");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM SUCCESS;" );
         while ( rs.next() ) {
            id = rs.getInt("id");
            email = rs.getString("email");
             message = rs.getString("message");

            System.out.println( "ID = " + id );
            System.out.println( "EMAIL = " + email );
            System.out.println( "MESSAGE = " + message );
            System.out.println();
         }
         rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
*/
	}
	public  void logMailError(int fid,String femail,String fmessage) {/*
		//Log the errors incase any occur
	  Connection c = null;
      Statement stmt = null;
      String email = femail;
      int id = fid;
      String message =fmessage;
      boolean sent = false;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/301db",
            "postgres", "Haasbroek11");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "INSERT INTO FAILED (ID,EMAIL,MESSAGE) "
               + "VALUES ('"+id+"','"+email+"','"+message+"');";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");


       c = null;
       stmt = null;
       try {
       Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/301db",
            "postgres", "Haasbroek11");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM FAILED;" );
         while ( rs.next() ) {
            id = rs.getInt("id");
            email = rs.getString("email");
             message = rs.getString("message");

            System.out.println( "ID = " + id );
            System.out.println( "email = " + email );
            System.out.println( "message = " + message );
            System.out.println();
         }
         rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }*/
	}
  public void logPushNotification(int fid,int fuserid,String fmessage) {/*
         Connection c = null;
      Statement stmt = null;
      int id = fid;
      int user_id = fuserid;
      String message =fmessage;
      boolean sent = false;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/301db",
            "postgres", "Haasbroek11");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "INSERT INTO PUSH (ID,User_ID,MESSAGE) "
               + "VALUES ('"+id+"','"+fuserid+"','"+message+"');";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");


       c = null;
       stmt = null;
       try {
       Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/301db",
            "postgres", "Haasbroek11");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM PUSH;" );
         while ( rs.next() ) {
            id = rs.getInt("id");
            fuserid= rs.getInt("user_id");
             message = rs.getString("message");

            System.out.println( "ID = " + id );
            System.out.println( "User_ID = " + user_id );
            System.out.println( "MESSAGE = " + message );
            System.out.println();
         }
         rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       */
  }
}
*/

