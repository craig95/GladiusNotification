import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class User {
   public static void main( String args[] )
     {
        String message = "blah";
        String email ="Drudan.ruan@gmail.com";
        int id = 6;
         MailLogger maillogger = new MailLogger();
        maillogger.logMailSent(id,email,message);
        System.out.println("executed");
        maillogger.logMailError(id,email,message);
        System.out.println("executed");
     }
}