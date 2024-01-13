package Managers;

import Managers.DatabaseConnector;

import java.io.IOException;
import java.sql.*;

public class LoginManager {
    static Log log;
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
            try {
                // Specify the log file name when creating the Log instance
                log = new Log("log.txt");
                log.logger.severe("SQL Exception:" + e.getMessage());
            } catch (IOException p) {
                log.logger.warning("Log unsuccessful:" + p.getMessage());
            }

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
            try {
                // Specify the log file name when creating the Log instance
                log = new Log("log.txt");
                log.logger.severe("SQL Exception:" + e.getMessage());
            } catch (IOException p) {
                log.logger.warning("Log unsuccessful:" + p.getMessage());
            }
        }
        return false;      //if user not found or passwords dont match
    }





}
