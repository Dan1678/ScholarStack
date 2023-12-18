package MainUI;

import GroupContent.Tag;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class TagsDisplay extends JPanel {

    private Tag tag;
    private JTree tree;

    public TagsDisplay() {
        tag = new Tag("Tags:");

        // get the current tree
        DefaultMutableTreeNode root = findTreeBelow(tag);

        // Create the tree and put it in a scroll pane
        JTree tree = new JTree(root);
        JScrollPane treeView = new JScrollPane(tree);

        // Add the scroll pane to a window
        add(treeView);

        displayTags();
    }

    private void displayTags() {

        DefaultMutableTreeNode root = findTreeBelow(tag);

    }

    private DefaultMutableTreeNode findTreeBelow(Tag parentTag) {
        //Gets the tree of all nodes underneath parent tag

        //base case
        if (parentTag.getSubTags() == null) {
            return null;
        }

        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentTag.getName());

        //recursive case
        for (Tag t : parentTag.getSubTags()) {
            DefaultMutableTreeNode treeBelow = findTreeBelow(t);
            parent.add(treeBelow);
        }
        return parent;
    }

    public void updateTags(Tag tag) {
        this.tag = tag;
        displayTags();
    }

    public JTree getTree() {
        return tree;
    }


}
