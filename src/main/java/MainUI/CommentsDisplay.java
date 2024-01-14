package MainUI;

import GroupContent.Comment;
import GroupContent.Paper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Define a custom panel for comments

public class CommentsDisplay extends JScrollPane {
    //scroll pane which holds multiple comment panels
    private JPanel commentsPanel; // Panel to hold comment components
    public static Paper paper;


    public CommentsDisplay() {
        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BorderLayout());

        //add scroll bars
        setViewportView(commentsPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    public void displayComments(Paper paper) {
        String paperName = paper.getName();
        CommentsDisplay.paper = paper;
        commentsPanel.removeAll(); //remove old JTree

        //add the new tree
        ArrayList<Comment> comments = paper.getComments();
        commentsPanel.add(comments.get(0).getDisplayTree(), BorderLayout.CENTER);

        //add the title to the tree
        JLabel titleLabel = new JLabel(paper.getName());
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 15));
        commentsPanel.add(titleLabel, BorderLayout.NORTH);

        //add the info to the bottom
        JLabel infoLabel = new JLabel("Triple click on a comment to add a reply");
        infoLabel.setFont(infoLabel.getFont().deriveFont(Font.BOLD));
        commentsPanel.add(infoLabel, BorderLayout.SOUTH);


        revalidate();
        repaint();
    }

    public static Paper getPaper(){
        return paper;
    }
}

