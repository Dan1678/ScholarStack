package MainUI;

import GroupContent.Comment;
import GroupContent.HierarchicalContent;
import GroupContent.Paper;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.ArrayList;

// Define a custom panel for comments
//class CommentPanel extends JPanel {
//    //panel which holds one comment
//    CommentPanel(String commentContent) {
//        setLayout(new BorderLayout());
//        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding
//
//        JLabel commentLabel = new JLabel("<html><body width='300'>" + commentContent + "</body></html>");
//        commentLabel.setVerticalAlignment(SwingConstants.TOP);
//        add(commentLabel, BorderLayout.WEST);
//
//        JButton replyBtn = new JButton("Reply");
//        add(replyBtn, BorderLayout.EAST);
//        //Do the thing we did for show comments with the interface
//    }
//}

public class CommentsDisplay extends JScrollPane {
    //scroll pane which holds multiple comment panels
    private JPanel commentsPanel; // Panel to hold comment components
    public static Paper paper;


    public CommentsDisplay() {
        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BorderLayout());


        setViewportView(commentsPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    public void displayComments(Paper paper) {
        CommentsDisplay.paper = paper;
        commentsPanel.removeAll();

        ArrayList<Comment> comments = paper.getComments();



        JTree tree = comments.get(0).getDisplayTree();
        commentsPanel.add(comments.get(0).getDisplayTree(), BorderLayout.CENTER);

        JLabel titleLabel = new JLabel(paper.getName());
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 15));
        commentsPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel infoLabel = new JLabel("Triple click on a comment to add a reply");
        infoLabel.setFont(infoLabel.getFont().deriveFont(Font.BOLD));
        commentsPanel.add(infoLabel, BorderLayout.SOUTH);

//        ArrayList<Comment> comments = paper.getComments();
//
//        if (comments.isEmpty()) {
//            JLabel noCommentsLabel = new JLabel("No comments available");
//            noCommentsLabel.setForeground(Color.GRAY);
//            noCommentsLabel.setHorizontalAlignment(SwingConstants.CENTER);
//            commentsPanel.add(noCommentsLabel);
//        } else {
//            for (Comment c : comments) {
//                CommentPanel commentPanel = new CommentPanel(c.getContent());
//                commentsPanel.add(commentPanel);
//            }
//        }

        revalidate();
        repaint();
    }

    public static Paper getPaper(){
        return paper;
    }
}

