package GroupContent;

import MainUI.WrappedTreeRndr;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class HierarchicalContent {

    private String content;
    private ArrayList<HierarchicalContent> subContent;
    private JTree tree;
    private Integer ID;

    public HierarchicalContent(String content, Integer ID) {
        this.content = content;
        this.ID = ID;
        subContent = new ArrayList<>();
    }

    public JTree getDisplayTree() {
        // returns the JTree representing the hierarchical content below the current tag

        DefaultMutableTreeNode root = findTreeBelow(this);

        tree = new JTree(root);
        //tree.setCellRenderer(new WrappedTreeRndr());

        // Add MouseListener to JTree
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 3) {
                    // on triple click

                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();  //get selected node
                    if (selectedNode != null) { //ensure it isn't empty
                        tree.expandPath(new TreePath(selectedNode.getPath())); //ensure the node is expanded (so it is visible)

                        Object nodeInfo = selectedNode.getUserObject();
                        if (nodeInfo instanceof HierarchicalContent) { //the tree elements should be of type HierarchicalContent
                            HierarchicalContent selectedContent = (HierarchicalContent) nodeInfo; //cast to HierarchicalContent object
                            String commentText = JOptionPane.showInputDialog("Enter the content");
                            if (commentText != null) {
                                selectedContent.addSubContent(new HierarchicalContent(commentText, selectedContent.getID()));
                            }

                        }
                    }
                }
            }
        });

        return tree;

    }

    private DefaultMutableTreeNode findTreeBelow(HierarchicalContent parentContent) {
        //Gets the tree of all nodes underneath parent tag

        //base case
        if (parentContent.getSubContent() == null) {
            return null;
        }

        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentContent);

        //recursive case
        for (HierarchicalContent c : parentContent.getSubContent()) {
            DefaultMutableTreeNode treeBelow = findTreeBelow(c);
            parent.add(treeBelow);
        }
        return parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addSubContent(HierarchicalContent content) {
        subContent.add(content);
    }

    public ArrayList<HierarchicalContent> getSubContent() {
        return subContent;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return content;
    }
}
