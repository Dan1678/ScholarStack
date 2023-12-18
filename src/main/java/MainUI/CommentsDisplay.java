package MainUI;

import GroupContent.Comment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CommentsDisplay extends JPanel {


    private ArrayList<Comment> comments;

    public CommentsDisplay(ArrayList<Comment> comments) {

        //set up panel
        //setPreferredSize(new Dimension(500, 300));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        this.comments = comments;

    }

    public void displayComments() {
        for (Comment c : comments) {
            add(new JLabel(c.getContent()));
        }

        revalidate();
        repaint();
    }

}
