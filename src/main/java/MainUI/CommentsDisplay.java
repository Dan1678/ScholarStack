package MainUI;

import GroupContent.Comment;
import GroupContent.Paper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Define a custom panel for comments
class CommentPanel extends JPanel {
    //panel which holds one comment
    CommentPanel(String commentContent) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding

        JLabel commentLabel = new JLabel("<html><body width='300'>" + commentContent + "</body></html>");
        commentLabel.setVerticalAlignment(SwingConstants.TOP);
        add(commentLabel, BorderLayout.WEST);

        JButton replyBtn = new JButton("Reply");
        add(replyBtn, BorderLayout.EAST);
        //Do the thing we did for show comments with the interface
    }
}

public class CommentsDisplay extends JScrollPane {
    //scroll pane which holds multiple comment panels
    private JPanel commentsPanel; // Panel to hold comment components

    public CommentsDisplay() {
        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));

        setViewportView(commentsPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void displayComments(Paper paper) {
        commentsPanel.removeAll();

        JLabel titleLabel = new JLabel(paper.getName());
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        commentsPanel.add(titleLabel);

        ArrayList<Comment> comments = paper.getComments();

        if (comments.isEmpty()) {
            JLabel noCommentsLabel = new JLabel("No comments available");
            noCommentsLabel.setForeground(Color.GRAY);
            noCommentsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            commentsPanel.add(noCommentsLabel);
        } else {
            for (Comment c : comments) {
                CommentPanel commentPanel = new CommentPanel(c.getContent());
                commentsPanel.add(commentPanel);
            }
        }

        revalidate();
        repaint();
    }
}
