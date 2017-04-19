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
            .getConnection("jdbc:postgresql://localhost:5432/301db",
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
         //push notification
                sql = "CREATE TABLE PUSH " +
                      "(ID INT PRIMARY KEY     NOT NULL," +
                      " User_ID           int    NOT NULL, " +
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
