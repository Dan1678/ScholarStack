package Managers;

import GroupContent.Comment;
import GroupContent.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;


public class DatabaseManager {

    private static Connection conn = null;

    static {
        //try {
            conn = DatabaseConnector.connectToDatabase();
            if (conn != null) {
                System.out.println("Connection to the database successful!");
            } else {
                System.out.println("Failed to make connection!");
            }
       // } catch (SQLException e) {
         //   e.printStackTrace();
       // }
    }

    public static boolean createTable(String tableName, String col1, String col2) {
        // Connect to the database
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

    }



    public static boolean insertRecord(String tableName, String columns, String values) {
        // Connect to the database

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

    }

    public static boolean insertComments(Integer parentID, String content, String userName, Timestamp time, Integer paperID) {
        // Connect to the database
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

    }

    public static void insertTags(int parentID, String content) {
        // Connect to the database
        if (conn != null) {
            System.out.println("Connection to the database successful!");
            // String colString = String.join(", ", columns);
            // String valString = String.join(", ", values);

            //String parentIDValue = (parentID == 0) ? "NULL" : parentID.toString();

            String insertQuery = String.format(
                    "INSERT INTO \"Tags\" (\"TagName\", \"ParentTagID\") VALUES ('%s', '%d')",
                    content.replaceAll("'", "''"), parentID);


            executeQuery(conn, insertQuery);

        } else {
            System.out.println("Connection to the database failed");
        }

    }



    //Deleting records
    public static boolean deleteRecord(String tableName, int id) {

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

    }

    //Read record from a table id
    //selectedoutput is desired column
    public static String readRecord(String tableName, String selectedOutput, String knownCol, String knownInput) {
        String result = null;

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

                }
                catch (Exception e){
                }

            }
            else{
                System.out.println("Connection to database failed");
            }

        return result;
    }

    public static String readRecord3(String tableName, String selectedOutput, String knownCol, String knownInput, int id) {
        String result = null;

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
                    //conn.close();
                }
                catch (Exception e){
                }

            }
            else{
                System.out.println("Connection to database failed");
            }


        return result;
    }


    public static String readRecord2(String tableName, String selectedOutput, String idType, int knownID) {
        String result = null;

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
                    //conn.close();
                }
                catch (Exception e){
                }

            }
            else{
                System.out.println("Connection to database failed");
            }


        return result;
    }


    public static int getLargestId(String tableName) {
        int largestId = 0;


            if (conn != null) {
                //System.out.println("TESTXX");
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


        return largestId;
    }

    public static ArrayList<String> getTagNamesByPaperId(int paperId) {
        ArrayList<String> tagNames = new ArrayList<>();
        if (conn != null) {
            // Directly inserting the paperId into the SQL query. Be cautious with this approach.


            String sql = "SELECT \"Tags\".\"TagName\", \"papers4\".\"papertitle\" " + "FROM \"Tags\" " +
                    "JOIN tagpaperlink3 ON \"Tags\".ID = tagpaperlink3.TagID " +
                    "JOIN \"papers4\" ON \"papers4\".\"id\" = tagpaperlink3.PaperID " +
                    "WHERE \"papers4\".\"id\" = " + paperId;




            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    tagNames.add(rs.getString("TagName"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed");
        }

        return tagNames;
    }

    public static Map<Integer, String> getAllPaperTitlesWithIds(String tableName) {
        Map<Integer, String> titlesMap = new HashMap<>();

        if (conn != null) {
            String sql = "SELECT id, papertitle FROM " + tableName;

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("papertitle");
                    titlesMap.put(id, title);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed");
        }

        return titlesMap;
    }





   //!!make all getIDs into same func
    public static Integer getPaperId(String tableName, String paperTitle) {
        Integer id = null;


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
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Connection to database failed");
            }


        return id;
    }

    public static Integer getCommentId(String tableName, String commentTitle) {
        Integer id = null;


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
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Connection to database failed");
            }


        return id;
    }

    public static Integer getId(String tableName, String content, String colName) {
        Integer id = null;


            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    // Sanitize paperTitle to prevent SQL injection
                    // This is a basic example, you should implement a more robust method
                    String sanitizedTitle = content.replace("'", "''");

                    String sql = "SELECT id FROM " + tableName + " WHERE " + colName + " = '" + sanitizedTitle + "'";
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        id = rs.getInt("id");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Connection to database failed");
            }


        return id;
    }

    public static Integer getCommentParentID(String tableName, String commentTitle) {
        Integer id = null;


            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    // Sanitize paperTitle to prevent SQL injection
                    // This is a basic example, you should implement a more robust method
                    String sanitizedTitle = commentTitle.replace("'", "''");

                    String sql = "SELECT parent_id FROM " + tableName + " WHERE content = '" + sanitizedTitle + "'";
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        id = rs.getInt("parent_id");
                    }
                }
                catch (SQLException e) {
            e.printStackTrace();
        }
            } else {
                System.out.println("Connection to database failed");
            }

        return id;
    }

    public static Integer getTagParentID(String tableName, String commentTitle) {
        Integer id = null;


        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                // Sanitize paperTitle to prevent SQL injection
                // This is a basic example, you should implement a more robust method
                String sanitizedTitle = commentTitle.replace("'", "''");

                String sql = "SELECT \"ParentTagID\" FROM " + tableName + " WHERE \"TagName\" = '" + sanitizedTitle + "'";
                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next()) {
                    id = rs.getInt("ParentTagID");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed");
        }

        return id;
    }



    public static ArrayList<Tag> getTags() {
        ArrayList<Tag> tags = new ArrayList<>();
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT \"TagName\" FROM \"Tags\"";
                ResultSet rset = stmt.executeQuery(sql);

                while (rset.next()) {

                    String content = rset.getString("TagName");


                    Tag tag = new Tag(content, null, null);
                    tags.add(tag);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed");
        }
        return tags;
    }



    //not sure if need
    public static void getAllUSerNamesFromDB(String tableName, int noRows) {

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

    }
    public static ArrayList<Comment> getCommentsForPaper(int paperId) {
        ArrayList<Comment> comments = new ArrayList<>();
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT id, content, username FROM comments WHERE \"paperID\" = " + paperId;
                ResultSet rset = stmt.executeQuery(sql);

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
        } else {
            System.out.println("Connection to database failed");
        }
        return comments;
    }


    public static ArrayList<Integer> getTagsForPaper(int paperId) {
        ArrayList<Integer> tagIDs = new ArrayList<>();

        if (conn != null) {
            try (Statement stmt = conn.createStatement();
                 ResultSet rset = stmt.executeQuery("SELECT tagid FROM tagpaperlink3 WHERE paperid = " + paperId)) {

                while (rset.next()) {
                    int id = rset.getInt("tagid");
                    tagIDs.add(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

         else {
            System.out.println("Connection to database failed");
        }
        return tagIDs;
    }

    public static ArrayList<String> getTagNamesFromIDs(ArrayList<Integer> tagIDs) {
        ArrayList<String> tagNames = new ArrayList<>();
        if (tagIDs.isEmpty()) {
            return tagNames; // Return the empty tagNames list
        }

        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT \"TagName\" FROM \"Tags\" WHERE id IN (" +
                        tagIDs.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(",")) + ")";
                try (ResultSet rset = stmt.executeQuery(sql)) {
                    while (rset.next()) {
                        String tagName = rset.getString("TagName");
                        tagNames.add(tagName);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed");
        }

        return tagNames;
    }


    public static ArrayList<Comment> getSubComments(int parentID) {
        ArrayList<Comment> Subcomments = new ArrayList<>();

        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT id, content, username FROM comments WHERE parent_ID = " + parentID;
                ResultSet rset = stmt.executeQuery(sql);

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
        } else {
            System.out.println("Connection to database failed");
        }
        return Subcomments;
    }

    public static ArrayList<Tag> getSubTags(int parentID) {
        ArrayList<Tag> Subtags = new ArrayList<>();

        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT \"TagName\" FROM \"Tags\" WHERE \"ParentTagID\" = " + parentID;

                ResultSet rset = stmt.executeQuery(sql);

                while (rset.next()) {

                    String content = rset.getString("TagName");


                    Tag tag = new Tag(content, null, null);
                    Subtags.add(tag);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed");
        }
        return Subtags;
    }




    private static void executeQuery (Connection conn, String query){
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
