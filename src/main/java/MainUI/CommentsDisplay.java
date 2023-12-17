package MainUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CommentsDisplay extends JPanel {


    private ArrayList<PaperUI> comments;

    public CommentsDisplay() {

        //set up panel
        setVisible(true);
        setPreferredSize(new Dimension(500, 300));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        comments = new ArrayList<>();

    }

}
