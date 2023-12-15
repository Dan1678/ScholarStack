package MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainUI extends JFrame {

    static GraphicsConfiguration gc;	// Contains this computer’s graphics configuration

    private ControlPanel controlPanel;
    private PapersPanel papersPanel;

    private TopMenuBar menuBar;

    public MainUI() {

       // setLayout(new BorderLayout());
        pack();
        setSize(1500,1000);
        setVisible(true); // making the frame visible
        //add panels
        controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.WEST);

        papersPanel = new PapersPanel();
        add(papersPanel, BorderLayout.CENTER);

        //add menu bar
        //menuBar = new TopMenuBar();
        //setJMenuBar(menuBar);

        // This next line closes the program when the frame is closed
        addWindowListener(new WindowAdapter() {	// Closes the program if close window clicked
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }


}
