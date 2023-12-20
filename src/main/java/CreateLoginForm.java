//import required classes and packages  
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import MainUI.*;

//create CreateLoginForm class to create login form  
//class extends JFrame to create a window where our component add  
//class implements ActionListener to perform an action on button click  
public class CreateLoginForm extends JFrame implements ActionListener
{
    //initialize button, panel, label, and text field  
    JButton b1, skiplogin;
    JPanel newPanel, newPanel2, newPanel3;
    JPanel backPanel, loginPanel, newPanel22, errorPanel;
    JLabel userLabel, passLabel, passReq, errorLabel;
    final JTextField  textField1, textField2;
    JLabel loginLabel;
    String userName;
    String tableName;

    //calling constructor  
    CreateLoginForm()
    {
        JPanel backPanel = new JPanel(new GridLayout(5,1));
        backPanel.setSize(1000,1000);

        loginPanel = new JPanel();

        loginLabel = new JLabel();
        loginLabel.setText("Login");

        loginPanel.add(loginLabel);
        backPanel.add(loginPanel);


        add(backPanel);


        //create label for username   
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1  

        //create text field to get username from the user  
        textField1 = new JTextField(15);    //set length of the text  

        //create label for password  
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2  

        //create text field to get password from the user  
        textField2 = new JPasswordField(15);    //set length for the password  

        //create submit button  
        b1 = new JButton("SUBMIT"); //set label to button

        skiplogin = new JButton("Skip login");

        JLabel passReq = new JLabel("Password must contain at least one number and one symbol");

        //create panel to put form elements  
        newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newPanel22 = new JPanel();
        newPanel.setSize(200,200);
        newPanel3 = new JPanel();
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel2.add(passLabel);    //set password label to panel
        newPanel2.add(textField2);   //set text field to panel
                  //set button to panel
        newPanel22.add(passReq);
        newPanel3.add(b1);
        newPanel3.add(skiplogin);
        backPanel.add(newPanel);
        backPanel.add(newPanel2);
        backPanel.add(newPanel22);
        backPanel.add(newPanel3);
       // backPanel.add(newPanel);


        //set border to panel   
        add(backPanel, BorderLayout.CENTER);


        //perform action on button click   
        b1.addActionListener(this);     //add action listener to button

        skiplogin.addActionListener(skiploginAL);
        setTitle("LOGIN FORM");         //set title to the login form  
    }

    private boolean isValidUsername(String user){
        return user.matches("^[A-Za-z ]+$");   //username contains just letters
    }

    private boolean isValidPassword(String pass){
        return pass.matches("^(?=.*[0-9])(?=.*[!@#$%^&*]).{6,}$");   //password has to contain one number, one symbol, at least 6 characters
    }


    //define abstract method actionPerformed() which will be called on button click   
    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
    {
        String userName = textField1.getText();        //get user entered username from the textField1
        String passWord = textField2.getText();        //get user entered pasword from the textField2


        if(isValidUsername(userName) && isValidPassword(passWord)){
            dispose();

            tableName = "users";

            boolean addUser = DatabaseManager.insertRecord(tableName, "username, password", String.format("'%s', '%s'", userName,passWord));
            if (addUser) {
                System.out.println("Record inserted successfully!");
            } else {
                System.out.println("Failed to insert record.");
            }

            MainUI page = new MainUI();

            //page.setVisible(true);
            //JLabel wel_label = new JLabel("Welcome: "+userName);
            //page.add(wel_label);

            //page.getContentPane().add(wel_label);
        }
        else{
            //show error message
            System.out.println("Please enter valid username and password");
        }


    }
    ActionListener skiploginAL = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            dispose();
            MainUI page = new MainUI();

//            page.setVisible(true);
//            JLabel wel_label = new JLabel("Welcome: "+userName);
//            page.add(wel_label);
//
//            page.getContentPane().add(wel_label);
        }
    };
    public String getUserName(){
        return this.userName;
        }


/*
        //check whether the credentials are authentic or not  
        if (userName.equals("test1@gmail.com") && passWord.equals("test")) {  //if authentic, navigate user to a new page

            //create instance of the NewPage  
            MainUI page = new MainUI();

            //make page visible to the user  
            page.setVisible(true);

            //create a welcome label and set it to the new page  
            JLabel wel_label = new JLabel("Welcome: "+userName);
            page.getContentPane().add(wel_label);
        }
        else{
            //show error message  
            System.out.println("Please enter valid username and password");
        }
    }*/
}  