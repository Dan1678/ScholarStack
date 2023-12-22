import java.sql.*;

public class LoginManager {
    public static boolean isUserTaken(String userName) {
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if (conn != null) {
                try (Statement s = conn.createStatement()) {
                    String query = "SELECT COUNT(*) FROM users WHERE username ='" + userName + "'";
                    try (ResultSet rset = s.executeQuery(query)) {
                        if (rset.next()) {
                            return rset.getInt(1) > 0;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;     //if username does not exist
    }

    public static boolean checkPassword(String userName, String givenPassword){
        try (Connection conn = DatabaseConnector.connectToDatabase()) {
            if(conn != null){
                try (Statement s = conn.createStatement()) {
                    String query = "SELECT password FROM users WHERE username ='" + userName + "'";
                    try (ResultSet rset = s.executeQuery(query)){
                        if (rset.next()) {
                            String storedPassword = rset.getString("password");
                            String givenHashed = PasswordHashing.hashPassword(givenPassword);
                            return storedPassword.equals(givenHashed);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;      //if user not found or passwords dont match
    }





}
