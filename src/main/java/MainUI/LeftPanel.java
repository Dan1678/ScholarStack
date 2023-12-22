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
    private JButton addTagBtn;
    private TagsDisplay tagsDisplay;
    private Tag selectedTag;


    public LeftPanel() {
        //set up panel
        setPreferredSize(new Dimension(300, 0)); //panel has a set width
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout (new BorderLayout());


        addTagBtn = new JButton("Add Tag");
        addTagBtn.setFont(new Font("Arial", Font.BOLD, 24));
        addTagBtn.setSize(new Dimension(200, 75));
        add(addTagBtn, BorderLayout.NORTH );

        addTagBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tagText = JOptionPane.showInputDialog("Enter the tag name");
                if (tagText != null) {
                    if (selectedTag != null) {
                        selectedTag.addSubTag(new Tag(tagText));
                    } else {
                        tagsDisplay.addTag(new Tag(tagText));
                    }
                }
                else {
                    //handling when the dialog is canceled (close window button as well)
                    System.out.println("Tag addition canceled.");
                }
            }
            /*public void actionPerformed(ActionEvent e) {
                String tagText = JOptionPane.showInputDialog("Enter the tag name");
                //todo - tag adding functionality
                //should add tag below selected tag, if no tag is selected add a new one to the list
                tagsDisplay.addTag(new Tag(tagText));
            }*/
        });


        //set up tags display
        tagsDisplay = new TagsDisplay();
        add(tagsDisplay, BorderLayout.WEST);


    }




}
