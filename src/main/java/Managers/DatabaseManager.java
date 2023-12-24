package Managers;

import java.sql.*;

public class DatabaseManager {

    public static boolean createTable(String tableName, String col1, String col2) {
        // Connect to the database
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");

                // Create a table with the specified name
                String createTableQuery = String.format("CREATE TABLE IF NOT EXISTS %s (id SERIAL PRIMARY KEY, %s VARCHAR(255), %s VARCHAR(255))", tableName, col1, col2);
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



    public static boolean insertRecord(String tableName, String columns, String values) {
        // Connect to the database
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");
               // String colString = String.join(", ", columns);
               // String valString = String.join(", ", values);

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
    public static boolean deleteRecord(String tableName, int id) {
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");

                //Delete record from the table
                String delQuery = String.format("DELETE FROM %s WHERE id = " + id, tableName);
                executeQuery(conn, delQuery);
                return true;
            } else {
                System.out.println("Connection to database failed");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Read record from a table id
    //selectedoutput is desired column
    public static String readRecord(String tableName, String selectedOutput, int id) {
        String result = "";
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");


                try {
                    Statement s =conn.createStatement();
                    String sql = String.format("SELECT %s FROM %s WHERE id=" + id, selectedOutput, tableName);
                    ResultSet rset=s.executeQuery(sql);
                    while(rset.next()){
                        result = rset.getString(selectedOutput);
                    }
                    rset.close();
                    s.close();
                    conn.close();
                }
                catch (Exception e){
                }

            }
            else{
                System.out.println("Connection to database failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



    private static void executeQuery (Connection conn, String query){
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
