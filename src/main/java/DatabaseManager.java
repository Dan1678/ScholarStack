import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

    public static boolean createTable(String tableName) {
        // Connect to the database
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");

                // Create a table with the specified name
                String createTableQuery = String.format("CREATE TABLE IF NOT EXISTS %s (id SERIAL PRIMARY KEY, username VARCHAR(255), password VARCHAR(255))", tableName);
                executeQuery(conn, createTableQuery);

                return true; // Table creation successful
            } else {
                System.out.println("Connection to the database failed");
                return false; // Table creation failed due to connection failure
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Table creation failed due to SQL exception
        }
    }

    // ... (previous methods)

    public static boolean insertRecord(String tableName, String columns, String values) {
        // Connect to the database
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");

                // Insert a record into the specified table
                String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, values);
                executeQuery(conn, insertQuery);

                return true; // Insertion successful
            } else {
                System.out.println("Connection to the database failed");
                return false; // Insertion failed due to connection failure
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Insertion failed due to SQL exception
        }
    }

    //Deleting records
    public static boolean deleteRecord(String tableName, int id){
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");

                //Delete record from the table
                String insertQuery = String.format("DELETE FROM %s WHERE id = "+id, tableName);
                executeQuery(conn, insertQuery);
                return true;
            } else {
                System.out.println("Connection to database failed");
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }






    private static void executeQuery(Connection conn, String query) {
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
