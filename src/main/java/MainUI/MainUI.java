package MainUI;

import Managers.BackupManager;
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

        BackupManager backupManager = new BackupManager(
                "ec2-54-246-1-94.eu-west-1.compute.amazonaws.com",
                "d6rkhhv2aujh36",
                "mixbutdugvnycu",
                "03f7fa8bfe5bfc30d6776369a8163f90164d68fcebaeecc32f073d7c4a334b94");

        TopMenuBar menuBar = new TopMenuBar(this);
        menuBar.setBackupManager(backupManager);
        //add menu bar
       // menuBar = new TopMenuBar();
        setJMenuBar(menuBar);

        // This next line closes the program when the frame is closed
        addWindowListener(new WindowAdapter() {	// Closes the program if close window clicked
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }


}
