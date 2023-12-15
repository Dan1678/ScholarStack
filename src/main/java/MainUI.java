import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainUI extends JFrame {

    static GraphicsConfiguration gc;	// Contains this computer’s graphics configuration


    public MainUI() {
        setSize(1000,1000);
       // setLayout(null); // using no layout managers
        setVisible(true); // making the frame visible



        // This next line closes the program when the frame is closed
        addWindowListener(new WindowAdapter() {	// Closes the program if close window clicked
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }


}
