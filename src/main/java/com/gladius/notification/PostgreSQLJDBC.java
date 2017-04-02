import java.sql.*;
//java -cp postgresql-9.2-1002.jar;C:\Users\HD\Desktop\301 PostgreSQLJDBC
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
//CREATE THE TABLES

public class PostgreSQLJDBC {
   public static void main( String args[] )
     {
       Connection c = null;
       Statement stmt = null;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/dbmail",
            "postgres", "Haasbroek11");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         //sent mail
       String sql = "CREATE TABLE SUCCESS " +
                      "(ID INT PRIMARY KEY     NOT NULL," +
                      " EMAIL           TEXT    NOT NULL, " +
                      " MESSAGE          CHAR(50)     NOT NULL)";
         stmt.executeUpdate(sql);
         stmt.close();
         stmt = c.createStatement();
         //failed mail
       sql = "CREATE TABLE FAILED " +
                      "(ID INT PRIMARY KEY     NOT NULL," +
                      " EMAIL           TEXT    NOT NULL, " +
                      " MESSAGE          CHAR(50)     NOT NULL)";
         stmt.executeUpdate(sql);
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Table created successfully");
     }
}
/*import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class PostgreSQLJDBC {
   public static void main( String args[] )
     {
       Connection c = null;
       Statement stmt = null;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/testdb",
            "postgres", "Haasbroek11");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "CREATE TABLE COMPANY " +
                      "(ID INT PRIMARY KEY     NOT NULL," +
                      " NAME           TEXT    NOT NULL, " +
                      " EMAIL            CHAR(50)     NOT NULL, ";
                      
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Table created successfully");
     }
}
//createdb -h localhost -p 5432 -U postgres testdb in bin folder
/*        Connection c = null;
       Statement stmt = null;
       try {
       Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/testdb",
            "postgres", "Haasbroek11");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
         while ( rs.next() ) {
            int id = rs.getInt("id");
            name = rs.getString("name");
             email = rs.getString("email");

            System.out.println( "ID = " + id );
            System.out.println( "NAME = " + name );
            System.out.println( "EMAIL = " + email );
            System.out.println();
         }
         rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }*/