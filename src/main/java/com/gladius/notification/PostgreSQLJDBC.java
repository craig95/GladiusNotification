package com.gladius.notification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Creates the needed tables for the MailLogger. The database name nees to be NotificationDB. Please make sure to change
 * the Username and Password fields to connect to the database.
 *
 * @author  GladiOS Notification Module Team
 * @version 1.0
 * @since   22-03-2017
 */
public class PostgreSQLJDBC {
  final static String username = "GladiOSNotification";
  final static String password = "123456789";
   public static void main( String args[] )
     {
       Connection c = null;
       Statement stmt = null;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/NotificationDB",
            username, "123456789");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         //sent mail
         String sql = "DROP TABLE IF EXISTS SUCCESS;";
         stmt.executeUpdate(sql);
         stmt.close();
         stmt = c.createStatement();
          sql = "CREATE TABLE SUCCESS " +
                      "(ID SERIAL   NOT NULL," +
                      " EMAIL           TEXT    NOT NULL, " +
                      " MESSAGE          TEXT    NOT NULL)";
         stmt.executeUpdate(sql);
         stmt.close();
         stmt = c.createStatement();
         //failed mail
         sql = "DROP TABLE IF EXISTS FAILED;";
         stmt.executeUpdate(sql);
         stmt.close();
         stmt = c.createStatement();
       sql = "CREATE TABLE FAILED " +
                      "(ID SERIAL     NOT NULL," +
                      " EMAIL           TEXT    NOT NULL, " +
                      " MESSAGE          TEXT     NOT NULL)";
         stmt.executeUpdate(sql);
         //push notification
         sql = "DROP TABLE IF EXISTS PUSH;";
         stmt.executeUpdate(sql);
         stmt.close();
         stmt = c.createStatement();
                sql = "CREATE TABLE PUSH " +
                      "(ID SERIAL    NOT NULL," +
                      " User_ID           int    NOT NULL, " +
                      " MESSAGE          TEXT     NOT NULL)";
         stmt.executeUpdate(sql);
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Table created successfully");
     }
}
