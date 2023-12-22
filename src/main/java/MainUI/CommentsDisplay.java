package MainUI;

import GroupContent.Comment;
import GroupContent.Paper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*public class CommentsDisplay extends JPanel {


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
*/
// Define a custom panel for comments
class CommentPanel extends JPanel {
    CommentPanel(String commentContent) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding

        JLabel commentLabel = new JLabel("<html><body width='300'>" + commentContent + "</body></html>");
        commentLabel.setVerticalAlignment(SwingConstants.TOP);
        add(commentLabel, BorderLayout.CENTER);
    }
}

public class CommentsDisplay extends JScrollPane {

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
            commentsPanel.add(new JLabel("No comments available")); // Display message for no comments
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
