import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public static Connection connectToDatabase() {
        try {

            String host = "ec2-54-246-1-94.eu-west-1.compute.amazonaws.com";
            String port = "5432";
            String databaseName = "d6rkhhv2aujh36";
            String username = "mixbutdugvnycu";
            String password = "03f7fa8bfe5bfc30d6776369a8163f90164d68fcebaeecc32f073d7c4a334b94";


            String dbUrl = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName + "?sslmode=require";

            // Connect to the database
            return DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            // Print the exception details
            e.printStackTrace();
            System.out.println("Connection failed: " + e.getMessage());
            return null; // Return null if the connection fails
        }
    }

    public static class LoginManager {
    }
}
