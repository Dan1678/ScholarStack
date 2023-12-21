package MainUI;

import GroupContent.Comment;
import GroupContent.Paper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CommentsDisplay extends JPanel {


    private ArrayList<Comment> comments;


    public CommentsDisplay() {

        //set up panel
        setPreferredSize(new Dimension(0, 300));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        comments = new ArrayList<Comment>();


    }

    public void displayComments(Paper paper) {
        removeAll();

        add(new JLabel(paper.getName()));
        comments = paper.getComments();
        for (Comment c : comments) {
            add(new JLabel(c.getContent()));
        }

        revalidate();
        repaint();
    }

}
