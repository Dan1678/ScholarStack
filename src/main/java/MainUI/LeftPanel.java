//Left sided panel which allows the user to:
//Add papers
//Browse papers
//Browse tags
//Add tags

package MainUI;

import GroupContent.Tag;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;


public class LeftPanel extends JPanel {
    private TagsDisplay tagsDisplay;
    private Tag selectedTag;


    public LeftPanel() {
        //set up panel
        setPreferredSize(new Dimension(300, 0)); //panel has a set width
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout (new BorderLayout());


        //set up tags display
        tagsDisplay = new TagsDisplay();
        add(tagsDisplay, BorderLayout.CENTER);


    }




}
