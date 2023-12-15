import javax.swing.*;

public class Main {
    public static void main(String arg[]) {

        try {
            //create instance of the CreateLoginForm
            CreateLoginForm form = new CreateLoginForm();
            form.setSize(1000, 1000);  //set size of the frame
            form.setVisible(true);  //make form visible to the user
        } catch (Exception e) {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

//        String tableName = "papers";
//
//        // Create the specified table
//        boolean tableCreationResult = DatabaseManager.createTable(tableName);
//
//        if (tableCreationResult) {
//            System.out.println("Table '" + tableName + "' created successfully!");
//        } else {
//            System.out.println("Failed to create table '" + tableName + "'.");
//        }

//        // Create the specified table
//        if (tableCreationResult) {
//            System.out.println("Table '" + tableName + "' created successfully!");
//
//            // Insert a record into the "users" table
//            boolean insertionResult = DatabaseManager.insertRecord(tableName, "username, password", "'john_doe', 'password123'");
//
//            if (insertionResult) {
//                System.out.println("Record inserted successfully!");
//            } else {
//                System.out.println("Failed to insert record.");
//            }
//        } else {
//            System.out.println("Failed to create table '" + tableName + "'.");
//        }

    }
}
