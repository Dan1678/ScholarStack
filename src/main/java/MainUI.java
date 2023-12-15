import javax.swing.*;
import java.awt.*;
import java.awt.MenuBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainUI extends JFrame {

    static GraphicsConfiguration gc;	// Contains this computer’s graphics configuration

    private PapersPanel papersPanel;
    private ControlPanel controlPanel;
    private TopMenuBar menuBar;

    public MainUI() {
        setSize(1500,1000);
        setLayout(null); // using no layout managers
        setVisible(true); // making the frame visible

        //add panels
        papersPanel = new PapersPanel();
        add(papersPanel);

        controlPanel = new ControlPanel();
        add(controlPanel);

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
