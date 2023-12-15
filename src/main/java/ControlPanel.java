import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {


    //Left sided panel which allows the user to:
        //Add papers
        //Browse papers
        //Browse tags
        //Add tags

    public ControlPanel() {
        setSize(300, 930);
        setLocation(20, 20);
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

}
