import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public static boolean connectToDatabase() {
        try {
            // Replace these values with your actual Heroku database connection details
            String host = "ec2-54-246-1-94.eu-west-1.compute.amazonaws.com";
            String port = "5432";
            String databaseName = "d6rkhhv2aujh36";
            String username = "mixbutdugvnycu";
            String password = "03f7fa8bfe5bfc30d6776369a8163f90164d68fcebaeecc32f073d7c4a334b94";

            // Construct the JDBC URL
            String dbUrl = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName + "?sslmode=require";

            // Connect to the database
            try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
                System.out.println("Connection successful!");

                // Introduce a delay of 45 seconds (45000 milliseconds)
                Thread.sleep(45000);

                return true; // Connection successful
            }
        } catch (SQLException | InterruptedException e) {
            // Print the exception details (you might want to log it in a real application)
            e.printStackTrace();
            System.out.println("Connection failed: " + e.getMessage());
            return false; // Connection failed
        }
    }
}
