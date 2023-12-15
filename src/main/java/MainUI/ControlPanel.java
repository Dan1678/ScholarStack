//Left sided panel which allows the user to:
//Add papers
//Browse papers
//Browse tags
//Add tags

package MainUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ControlPanel extends JPanel {
    private JButton addPaperBtn;


    // TEMPORARY UNTIL WE GET TAGS IMPLIMENTED
    //public ArrayList<>

    public ControlPanel() {
        //set up panel
        setPreferredSize(new Dimension(500, 0)); //panel has a set width
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));


        addPaperBtn = new JButton("Add Paper");
        addPaperBtn.setFont(new Font("Arial", Font.BOLD, 24));
        addPaperBtn.setPreferredSize(new Dimension(200, 75));
        add(addPaperBtn, BorderLayout.NORTH);
    }

}
