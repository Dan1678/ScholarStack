package MainUI;

import GroupContent.Tag;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class TagsDisplay extends JPanel {

    private Tag tag;
    private JTree tree;
    private JScrollPane treeView;
    private LeftPanel parentPanel;

    public TagsDisplay() {
        setPreferredSize(new Dimension(250, 0)); //panel has a set width


        tag = new Tag("Tags:");

        tag.addSubTag(new Tag("sub 1"));
        tag.addSubTag(new Tag("sub 2"));

        for (Tag t : tag.getSubTags()) {
            t.addSubTag(new Tag("subsub 1"));
            t.addSubTag(new Tag("subsub 2"));
        }

        displayTags();
    }

    public void setParentPanel(LeftPanel parentPanel) {
        this.parentPanel = parentPanel;
    }
    private void displayTags() {


        //Remove the old one
        if (treeView != null) {
            remove(treeView);
        }


        DefaultMutableTreeNode root = findTreeBelow(tag);

        tree = new JTree(root);
        treeView = new JScrollPane(tree);

        // Add the scroll pane to a window
        add(treeView);

        revalidate();
        repaint();

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

    public void addTag(Tag tag) {
        this.tag.addSubTag(tag);
        displayTags();
    }

    public JTree getTree() {
        return tree;
    }


}
