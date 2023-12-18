//Left sided panel which allows the user to:
//Add papers
//Browse papers
//Browse tags
//Add tags

package MainUI;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftPanel extends JPanel {
    private JButton addTagBtn;
    private TagsDisplay tagsDisplay;



    // TEMPORARY UNTIL WE GET TAGS IMPLIMENTED
    //public ArrayList<>

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
                String tag = JOptionPane.showInputDialog("Enter the tag name");
                //todo - tag adding functionality
                //should add tag below selected tag, if no tag is selected add a new one to the list
                TreePath paths = tagsDisplay.getTree().getSelectionPath();
                System.out.println(paths);
            }
        });

        //set up tags display
        tagsDisplay = new TagsDisplay();
        add(tagsDisplay, BorderLayout.WEST);


    }




}
