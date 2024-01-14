package MainUI;

import GroupContent.HierarchicalContent;
import GroupContent.Tag;
import GroupContent.Paper;
import Managers.DatabaseManager;

import static GroupContent.Paper.allPapers;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.tree.TreePath;

public class TagsDisplay extends JPanel {

    private Tag tag;
    private JTree tree;
    private JScrollPane treeView;
    private LeftPanel parentPanel;


    public TagsDisplay() {
        JLabel infoLabel = new JLabel("This shows what tags belong to what papers");
        add(infoLabel);
        setPreferredSize(new Dimension(250, 0)); //panel has a set width

        tag = new Tag("Papers:");


        int largestId = DatabaseManager.getLargestId("papers4");


        Map<Integer, String> paperTitles = DatabaseManager.getAllPaperTitlesWithIds("papers4");

        for (int i = 0; i <= largestId; i++) {
            if (!paperTitles.containsKey(i)) {
                continue;
            }
            String paperTitle = paperTitles.get(i);
            Tag tempTag = new Tag(paperTitle);


            ArrayList<String> tagNamesList = DatabaseManager.getTagNamesByPaperId(i);

            for (String tagName : tagNamesList) {
                tempTag.addSubContent(new Tag(tagName));
            }
            tag.addSubContent(tempTag);
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
        tree.setPreferredSize(new Dimension(250, 0));
        // Add MouseListener to JTree
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 3) {
                    // on triple click
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();  //get selected node
                    if (selectedNode != null) { //ensure it isnt empty
                        tree.expandPath(new TreePath(selectedNode.getPath())); //ensure the node is expanded (so it is visible)

                        Object nodeInfo = selectedNode.getUserObject();
                        if (nodeInfo instanceof Tag) { //the tree elements should be of type Tag
                            Tag selectedTag = (Tag) nodeInfo; //cast to tag object
                            addSubTag(selectedTag);
                        }

                    }
                }
            }
        });


        treeView = new JScrollPane(tree);

        // Add the scroll pane to a window
        add(treeView);

        revalidate();
        repaint();

    }

    private DefaultMutableTreeNode findTreeBelow(Tag parentTag) {
        //Gets the tree of all nodes underneath parent tag

        //base case
        if (parentTag.getSubContent() == null) {
            return null;
        }

        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentTag);

        //recursive case
        for (HierarchicalContent t : parentTag.getSubContent()) {
            DefaultMutableTreeNode treeBelow = findTreeBelow((Tag)t);
            parent.add(treeBelow);
        }
        return parent;
    }

    private void addSubTag(Tag selectedTag) {
        String tagText = JOptionPane.showInputDialog("Enter the tag name");
        if (tagText != null) {

            selectedTag.addSubContent(new Tag(tagText));
        }
        displayTags();
    }

    public void updateTags(Tag tag) {
        this.tag = tag;
        displayTags();
    }

    public void addTag(Tag tag) {
        this.tag.addSubContent(tag);
        displayTags();
    }

    public JTree getTree() {
        return tree;
    }


}
