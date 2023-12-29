package MainUI;

import Managers.CreateLoginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainUI extends JFrame {

    static GraphicsConfiguration gc;	// Contains this computer’s graphics configuration

    private LeftPanel leftPanel;
    private RightPanel rightPanel;
    private TopMenuBar menuBar;
    public String userName;

    public MainUI(String UserName){ //set up a new blank group
        this.userName = UserName;
        setupFrame();
    }
    public String getUserName(){
        return this.userName;
    }

    private void setupFrame() {
        setLayout(new BorderLayout());

        setSize(1500,750);
        setVisible(true); // making the frame visible
        //add panels
        leftPanel = new LeftPanel(this.userName);
        add(leftPanel, BorderLayout.WEST);

        rightPanel = new RightPanel(this.userName);
        add(rightPanel, BorderLayout.CENTER);


        //add menu bar
        menuBar = new TopMenuBar();
        setJMenuBar(menuBar);

        // This next line closes the program when the frame is closed
        addWindowListener(new WindowAdapter() {	// Closes the program if close window clicked
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }


}
