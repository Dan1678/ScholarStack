package MainUI;

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
import javax.swing.tree.TreePath;

public class TagsDisplay extends JPanel {

    private Tag tag;
    private JTree tree;
    private JScrollPane treeView;
    private LeftPanel parentPanel;


    public TagsDisplay() {
        JLabel infoLabel = new JLabel("Triple click to add tag");
        add(infoLabel);
        setPreferredSize(new Dimension(250, 0)); //panel has a set width

        tag = new Tag("Papers:");

        //change this not to be a loop

        for(int i = 0; i <= DatabaseManager.getLargestId("papers4"); i++) {

            String paperTitle = (DatabaseManager.readRecord2("papers4", "papertitle", "id", i));

            if (paperTitle == null) {
                continue;
            }

            tag.addSubTag(new Tag(paperTitle));

            /*for (Tag t : tag.getSubTags()) {
                ///get the paper id
                int PaperId = DatabaseManager.getPaperId("papers4", paperTitle);

                //get list of tags ids from tagspaperlink3 with same paperid
                ArrayList<Integer> tagsList = DatabaseManager.getTagsForPaper(PaperId);
                ArrayList<String> tagNameList = DatabaseManager.getTagNamesFromIDs(tagsList);

                for (i = 0; i < tagNameList.size(); i++){
                    t.addSubTag(new Tag(tagNameList.get(i)));
                }
                //add subtag to paper for each
                //t.addSubTag(new Tag("subsub 1"));
                //t.addSubTag(new Tag("subsub 2"));
            } */

        }

       // tag.addSubTag(new Tag("PAPER TITLE"));
       // tag.addSubTag(new Tag("PAPER TITLE 2"));

        /*for (Tag t : tag.getSubTags()) {
            ///get the paper id
            DatabaseManager.getPaperId("papers4", paperTitle);

            //get all tags associated with that paper
            //add subtag to paper for each
            t.addSubTag(new Tag("subsub 1"));
            t.addSubTag(new Tag("subsub 2"));
        } */

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
        if (parentTag.getSubTags() == null) {
            return null;
        }

        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentTag);

        //recursive case
        for (Tag t : parentTag.getSubTags()) {
            DefaultMutableTreeNode treeBelow = findTreeBelow(t);
            parent.add(treeBelow);
        }
        return parent;
    }

    private void addSubTag(Tag selectedTag) {
        String tagText = JOptionPane.showInputDialog("Enter the tag name");
        if (tagText != null) {

            selectedTag.addSubTag(new Tag(tagText));
        }
        displayTags();
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
