
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import MainUI.*;


public class CreateLoginForm extends JFrame implements ActionListener
{

    JButton b1, skiplogin, createAcc;
    JPanel newPanel, newPanel2, newPanel3;
    JPanel backPanel, loginPanel, newPanel22, errorPanel;
    JLabel userLabel, passLabel, passReq, errorLabel;
    final JTextField  textField1, textField2;
    JLabel loginLabel;
    String userName;
    String tableName;


    CreateLoginForm()
    {
        backPanel = new JPanel(new GridLayout(5,1));
        backPanel.setSize(1000,1000);

        loginPanel = new JPanel();


        loginLabel = new JLabel();
        loginLabel.setText("Login or Create Account");

        loginPanel.add(loginLabel);
        backPanel.add(loginPanel);
        add(backPanel);


        userLabel = new JLabel();
        userLabel.setText("Username");


        textField1 = new JTextField(15);


        passLabel = new JLabel();
        passLabel.setText("Password");

        textField2 = new JPasswordField(15);

        b1 = new JButton("Login");

        skiplogin = new JButton("Skip login");

        createAcc = new JButton("Create Account");

        JLabel passReq = new JLabel("Password must contain at least one number and one symbol");


        newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newPanel22 = new JPanel();
        newPanel.setSize(200,200);
        newPanel3 = new JPanel();
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel2.add(passLabel);
        newPanel2.add(textField2);
        newPanel22.add(passReq);
        newPanel3.add(b1);
        newPanel3.add(createAcc);
        newPanel3.add(skiplogin);
        backPanel.add(newPanel);
        backPanel.add(newPanel2);
        backPanel.add(newPanel22);
        backPanel.add(newPanel3);
       // backPanel.add(newPanel);

        add(backPanel, BorderLayout.CENTER);

        createAcc.addActionListener(this);

        skiplogin.addActionListener(skiploginAL);
        b1.addActionListener(currAcc);
        setTitle("LOGIN FORM");
    }

    private boolean isValidUsername(String user){
        boolean isValidFormat = user.matches("^[A-Za-z ]+$");

        boolean isTaken = LoginManager.isUserTaken(user);

        return isValidFormat && !isTaken;   //username contains just letters
    }

    private boolean isValidPassword(String pass){
        return pass.matches("^(?=.*[0-9])(?=.*[!@#$%^&*]).{6,}$");   //password has to contain one number, one symbol, at least 6 characters
    }


    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
    {
        String userName = textField1.getText();        //get user entered username from the textField1
        String passWord = textField2.getText();        //get user entered pasword from the textField2

        if(isValidUsername(userName) && isValidPassword(passWord)){
            dispose();

            tableName = "users";
            boolean tableCreationResult = DatabaseManager.createTable(tableName);
            if (tableCreationResult) {

                boolean addUser = DatabaseManager.insertRecord(tableName, "username, password", String.format("'%s', '%s'", userName, PasswordHashing.hashPassword(passWord)));
                if (addUser) {
                    System.out.println("Record inserted successfully!");
                } else {
                    System.out.println("Failed to insert record.");
                }
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

    ActionListener currAcc = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userName = textField1.getText();        //get user entered username from the textField1
            String passWord = textField2.getText();

            if(LoginManager.isUserTaken(userName) && LoginManager.checkPassword(userName, passWord)){
                dispose();
                MainUI page = new MainUI();
            }
        }
    };

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