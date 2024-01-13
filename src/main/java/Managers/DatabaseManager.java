package Managers;

import GroupContent.Comment;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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

    public static boolean insertComments(Integer parentID, String content, String userName, Timestamp time, Integer paperID) {
        // Connect to the database
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");
                // String colString = String.join(", ", columns);
                // String valString = String.join(", ", values);

                String parentIDValue = (parentID == null) ? "NULL" : parentID.toString();

                String timeValue = (time == null) ? "NULL" : "'" + time.toString() + "'";


                String insertQuery = String.format(
                        "INSERT INTO comments (parent_id, content, username, timestamp, \"paperID\") VALUES (%s, '%s', '%s', %s, %d)",
                        parentIDValue, content.replaceAll("'", "''"), userName.replaceAll("'", "''"), timeValue, paperID
                );

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
    public static String readRecord(String tableName, String selectedOutput, String knownCol, String knownInput) {
        String result = null;
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");


                try {
                    Statement s =conn.createStatement();
                //    SELECT "username" FROM papers3 WHERE username = 'testpaperUpload';

                    String sql = "SELECT \"" + selectedOutput + "\" FROM " + tableName + " WHERE \"" + knownCol + "\" = '" + knownInput + "'";

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

    public static String readRecord3(String tableName, String selectedOutput, String knownCol, String knownInput, int id) {
        String result = null;
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");


                try {
                    Statement s =conn.createStatement();
                    //    SELECT "username" FROM papers3 WHERE username = 'testpaperUpload';

                    String sql = "SELECT \"" + selectedOutput + "\" FROM " + tableName +
                            " WHERE \"" + knownCol + "\" = '" + knownInput + "'" +
                            " AND id = " + id;

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


    public static String readRecord2(String tableName, String selectedOutput, String idType, int knownID) {
        String result = null;
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");


                try {
                    Statement s =conn.createStatement();
                    //    SELECT "username" FROM papers3 WHERE username = 'testpaperUpload';

                    String sql = "SELECT " + selectedOutput + " FROM " + tableName + " WHERE "+ idType +"=" + knownID;


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


    public static int getLargestId(String tableName) {
        int largestId = 0;

        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    String sql = "SELECT MAX(id) FROM " + tableName;
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        largestId = rs.getInt(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Connection to database failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return largestId;
    }

    public static Integer getPaperId(String tableName, String paperTitle) {
        Integer id = null;

        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    // Sanitize paperTitle to prevent SQL injection
                    // This is a basic example, you should implement a more robust method
                    String sanitizedTitle = paperTitle.replace("'", "''");

                    String sql = "SELECT id FROM " + tableName + " WHERE papertitle = '" + sanitizedTitle + "'";
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        id = rs.getInt("id");
                    }
                }
            } else {
                System.out.println("Connection to database failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public static Integer getCommentId(String tableName, String commentTitle) {
        Integer id = null;

        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    // Sanitize paperTitle to prevent SQL injection
                    // This is a basic example, you should implement a more robust method
                    String sanitizedTitle = commentTitle.replace("'", "''");

                    String sql = "SELECT id FROM " + tableName + " WHERE content = '" + sanitizedTitle + "'";
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        id = rs.getInt("id");
                    }
                }
            } else {
                System.out.println("Connection to database failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public static Integer getCommentParentID(String tableName, String commentTitle) {
        Integer id = null;

        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    // Sanitize paperTitle to prevent SQL injection
                    // This is a basic example, you should implement a more robust method
                    String sanitizedTitle = commentTitle.replace("'", "''");

                    String sql = "SELECT parent_id FROM " + tableName + " WHERE content = '" + sanitizedTitle + "'";
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        id = rs.getInt("id");
                    }
                }
            } else {
                System.out.println("Connection to database failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public static void getAllPaperNamesFromDB(String tableName, int noRows) {
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");

                try (Statement stmt = conn.createStatement()) {
                    // Fixed the SQL query string
                    String sql = "SELECT * FROM " + tableName + " LIMIT " + noRows;
                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        ResultSetMetaData rsmd = rs.getMetaData();
                        //int columnsNumber = rsmd.getColumnCount();
                        System.out.println("tesing get all papers from db"+rs.getString(3));

                        System.out.println();
                    }
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Connection to database failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //not sure if need
    public static void getAllUSerNamesFromDB(String tableName, int noRows) {
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                System.out.println("Connection to the database successful!");

                try (Statement stmt = conn.createStatement()) {
                    // Fixed the SQL query string
                    String sql = "SELECT * FROM " + tableName + " LIMIT " + noRows;
                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        ResultSetMetaData rsmd = rs.getMetaData();
                        //int columnsNumber = rsmd.getColumnCount();
                        System.out.println("tesing get all papers from db"+rs.getString(2));

                        System.out.println();
                    }
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Connection to database failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Comment> getCommentsForPaper(int paperId) {
        ArrayList<Comment> comments = new ArrayList<>();

        String sql = "SELECT id, content, username FROM comments WHERE \"paperID\" = " + paperId;



        try (Connection conn = DatabaseConnector.connectToDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rset = stmt.executeQuery(sql)) {

            while (rset.next()) {
                Integer id = rset.getInt("id");
                String content = rset.getString("content");
                String username = rset.getString("username");


                Comment comment = new Comment(content, id, username);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static ArrayList<Comment> getSubComments(int parentID) {
        ArrayList<Comment> Subcomments = new ArrayList<>();

        String sql = "SELECT id, content, username FROM comments WHERE parent_ID =" + parentID;



        try (Connection conn = DatabaseConnector.connectToDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rset = stmt.executeQuery(sql)) {

            while (rset.next()) {
                Integer id = rset.getInt("id");
                String content = rset.getString("content");
                String username = rset.getString("username");


                Comment comment = new Comment(content, id, username);
                Subcomments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Subcomments;
    }



    private static void executeQuery (Connection conn, String query){
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
