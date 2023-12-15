//Left sided panel which allows the user to:
//Add papers
//Browse papers
//Browse tags
//Add tags

package MainUI;

import javax.swing.*;
import javax.swing.text.html.HTML;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;

public class ControlPanel extends JPanel {
    private JButton addPaperBtn;


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

        displayTags();

    }


    private void displayTags() {

        //DUMMY TAG SYSTEM - ChatGPT

        // Create the root of the tree
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root Tag");

        // Create and add some tags and subtags
        DefaultMutableTreeNode tag1 = new DefaultMutableTreeNode("Tag 1");
        DefaultMutableTreeNode subTag1 = new DefaultMutableTreeNode("Sub Tag 1");
        tag1.add(subTag1);
        root.add(tag1);

        // More tags and subtags can be added here

        // Create the tree and put it in a scroll pane
        JTree tree = new JTree(root);
        JScrollPane treeView = new JScrollPane(tree);

        // Add the scroll pane to a window

        add(treeView);

    }



}
