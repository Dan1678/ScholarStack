package MainUI;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {


    //Left sided panel which allows the user to:
        //Add papers
        //Browse papers
        //Browse tags
        //Add tags

    private JButton addPaperBtn;

    public ControlPanel() {
        setSize(300, 930);
        setPreferredSize(new Dimension(500, 0));
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));


        addPaperBtn = new JButton("Add Paper");
        addPaperBtn.setFont(new Font("Arial", Font.BOLD, 24));
        addPaperBtn.setPreferredSize(new Dimension(200, 75));
        add(addPaperBtn, BorderLayout.NORTH);
    }

}
