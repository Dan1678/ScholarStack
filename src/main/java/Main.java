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

        if (DatabaseConnector.connectToDatabase()) {
            System.out.println("Connected to the database successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
