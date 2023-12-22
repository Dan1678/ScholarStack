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


    /*    String testPass = "123456";
        String testPass2 = "test5%%";
        String hashed = PasswordHashing.hashPassword(testPass);
        String hashed1 = PasswordHashing.hashPassword(testPass);
        String hashed2 = PasswordHashing.hashPassword(testPass2);

        System.out.println("input password: "+testPass);
        System.out.println("Hashed password: "+ hashed);
        System.out.println("Hashed password: "+ hashed1);
        System.out.println("input password2: "+testPass2);
        System.out.println("Hashed password: "+ hashed2);
     */


      /*  String tableName = "users";

        int rowDeleted = 6;
        boolean wasDeleted = DatabaseManager.deleteRecord(tableName, rowDeleted);



       /* if(wasDeleted){
            System.out.println("Record for ID:" + rowDeleted+ "was deleted");
        }
        else{
            System.out.println("Record for ID:"+rowDeleted+"was not deleted");
        }

        int idToRead = 1;
        String selOut = "username";
        String outputCol = DatabaseManager.readRecord(tableName, selOut, idToRead);

        System.out.println("RequiredOutput: "+outputCol);

        boolean tableCreationResult = DatabaseManager.createTable(tableName);
        boolean insertionResult = DatabaseManager.insertRecord(tableName, "username, password", "'john_does', 'password1234'");

            */

        String tableName = "tags";

        // Create the specified table
        boolean tableCreationResult = DatabaseManager.createTable(tableName);

        if (tableCreationResult) {
            System.out.println("Table '" + tableName + "' created successfully!");
        } else {
            System.out.println("Failed to create table '" + tableName + "'.");
        }

        // Create the specified table
        if (tableCreationResult) {
            System.out.println("Table '" + tableName + "' created successfully!");

            // Insert a record into the "users" table
            boolean insertionResult = DatabaseManager.insertRecord(tableName, "username, password", "'john_doe', 'password123'");

            if (insertionResult) {
                System.out.println("Record inserted successfully!");
            } else {
                System.out.println("Failed to insert record.");
            }
        } else {
            System.out.println("Failed to create table '" + tableName + "'.");
        }

    }
}
