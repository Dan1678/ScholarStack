package MainUI;

import GroupContent.GroupContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainUI extends JFrame {

    static GraphicsConfiguration gc;	// Contains this computer’s graphics configuration

    private ControlPanel controlPanel;
    private PapersPanel papersPanel;

    private TopMenuBar menuBar;

    private GroupContent groupContent;

    public MainUI() { //set up a new blank group
        GroupContent gc = new GroupContent();
        this.groupContent = gc;
        setupFrame();
    }

    public MainUI(GroupContent groupContent) { //set up a group based on existing group
        this.groupContent = groupContent;
        setupFrame();
    }

    private void setupFrame() {
        setLayout(new BorderLayout());

        setSize(1500,750);
        setVisible(true); // making the frame visible
        //add panels
        controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.WEST);

        papersPanel = new PapersPanel();
        add(papersPanel, BorderLayout.CENTER);


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
