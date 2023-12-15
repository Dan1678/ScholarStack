//Left sided panel which allows the user to:
//Add papers
//Browse papers
//Browse tags
//Add tags

package MainUI;

import GroupContent.Tag;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JButton addPaperBtn;

    private Tag tags;


    // TEMPORARY UNTIL WE GET TAGS IMPLIMENTED
    //public ArrayList<>

    public ControlPanel() {
        //set up panel
        setPreferredSize(new Dimension(400, 0)); //panel has a set width
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));


        addPaperBtn = new JButton("Add Paper");
        addPaperBtn.setFont(new Font("Arial", Font.BOLD, 24));
        addPaperBtn.setSize(new Dimension(200, 75));
        add(addPaperBtn);

        updateTags(new Tag("ROOT"));
        displayTags();

    }

    private void displayTags() {

        DefaultMutableTreeNode root = getTree(tags);


        // Create the tree and put it in a scroll pane
        JTree tree = new JTree(root);
        JScrollPane treeView = new JScrollPane(tree);

        // Add the scroll pane to a window
        add(treeView);

    }

    private DefaultMutableTreeNode getTree(Tag parentTag) {
        //Gets the tree of all nodes underneath parent tag

        //base case
        if (parentTag.getSubTags() == null) {
            return null;
        }

        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentTag.getName());

        //recursive case
        for (Tag t : parentTag.getSubTags()) {
            DefaultMutableTreeNode treeBelow = getTree(t);
            parent.add(treeBelow);
        }
        return parent;
    }

    public void updateTags(Tag tags) {
        this.tags = tags;
    }



}
