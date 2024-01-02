package Managers;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import MainUI.*;


public class CreateLoginForm extends JFrame implements ActionListener
{

    JButton b1, skiplogin, createAcc;
    JPanel newPanel, newPanel2, newPanel3,  backPanel, loginPanel, newPanel22, errorPanel;
    JLabel userLabel, passLabel, passReq,loginLabel, userReq, errorLabel;
    final JTextField  textField1, textField2;
    String userName;
    String tableName;


    public CreateLoginForm()
    {
        backPanel = new JPanel(new GridLayout(5,1));
        backPanel.setSize(1000,1000);

        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));


        loginLabel = new JLabel("Login or Create New Account");
        passLabel = new JLabel("Password must contain at least one number, one symbol (!@#$%^&*) and be more than 6 characters");

        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginPanel.add(loginLabel);
        loginPanel.add(passLabel);

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

        passReq = new JLabel("");


        userReq = new JLabel("");


        newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newPanel22 = new JPanel();
        newPanel22.setLayout(new BoxLayout(newPanel22, BoxLayout.Y_AXIS));
        newPanel.setSize(200,200);
        newPanel3 = new JPanel();
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel2.add(passLabel);
        newPanel2.add(textField2);

        passReq.setAlignmentX(Component.CENTER_ALIGNMENT);
        userReq.setAlignmentX(Component.CENTER_ALIGNMENT);

        newPanel22.add(passReq);
        newPanel22.add(userReq);


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

    public boolean isValidUsername(String user){
        boolean isValidFormat = user.matches("^[A-Za-z0-9 ]+$");

        boolean isTaken = LoginManager.isUserTaken(user);

        return isValidFormat && !isTaken;   //username contains just letters
    }

    public boolean isValidPassword(String pass){
        return pass.matches("^(?=.*[0-9])(?=.*[!@#$%^&*]).{6,}$");   //password has to contain one number, one symbol, at least 6 characters
    }


    public void actionPerformed(ActionEvent ae)
    {
        String userName = textField1.getText();        //get user entered username from the textField1
        String passWord = textField2.getText();        //get user entered pasword from the textField2
        if(!isValidUsername(userName) && isValidPassword(passWord)){
            userReq.setText("Username taken, please enter an altenate username");
            passReq.setText("");
        }

        if(!isValidPassword(passWord) && isValidUsername(userName)){
            passReq.setText("Please enter a valid password");
            userReq.setText("");
        }

        if(!isValidUsername(userName) && !isValidPassword(passWord)){
            userReq.setText("Username taken, please enter an altenate username");
            passReq.setText("Please enter a valid password");
        }

        if(isValidUsername(userName) && isValidPassword(passWord)){
            this.userName = userName;
            dispose();


            tableName = "users";
            boolean tableCreationResult = DatabaseManager.createTable(tableName, "username", "password");
            if (tableCreationResult) {

                boolean addUser = DatabaseManager.insertRecord(tableName, "username, password", String.format("'%s', '%s'", userName, PasswordHashing.hashPassword(passWord)));
                if (addUser) {

                    System.out.println("Record inserted successfully!");
                } else {
                    System.out.println("Failed to insert record.");
                }
            }

            MainUI page = new MainUI(this.userName);
            String userName1 = page.getUserName();

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
        public void actionPerformed(ActionEvent e) {
            String userName = textField1.getText();        //get user entered username from the textField1
            String passWord = textField2.getText();


            if(!LoginManager.isUserTaken(userName)){
                userReq.setText("Username not found");
                passReq.setText("");
            }

            if(!LoginManager.checkPassword(userName, passWord) && LoginManager.isUserTaken(userName)){
                passReq.setText("Incorrect Password for user: "+userName);
                userReq.setText("");
            }

            if(LoginManager.isUserTaken(userName) && LoginManager.checkPassword(userName, passWord)){

                dispose();
                MainUI page = new MainUI(userName);
                String userName1 = page.getUserName();

            }
        }
    };

    ActionListener skiploginAL = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            dispose();
            MainUI page = new MainUI("Skipped login user");

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