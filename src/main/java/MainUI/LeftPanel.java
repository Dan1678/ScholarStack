//Left sided panel which allows the user to:
//Add papers
//Browse papers
//Browse tags
//Add tags

package MainUI;

import GroupContent.Tag;

import javax.swing.*;
import java.awt.*;


public class LeftPanel extends JPanel {
    private TagsDisplay tagsOnPapersDisplay;
    private TagsListDisplay tagsDisplay;
    private Tag selectedTag;

    public LeftPanel(String UserName) {
        //set up panel
        setPreferredSize(new Dimension(300, 0)); //panel has a set width
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BorderLayout());

        //add welcomeUser message
        WelcomeUser welcomeUser = new WelcomeUser();
        JPanel welcomePanel = welcomeUser.createWelcomePanel(UserName);
        add(welcomePanel, BorderLayout.NORTH);

        JPanel tagsDisplayPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2,1);
        tagsDisplayPanel.setLayout(gridLayout);


        //set up tags displays
        tagsOnPapersDisplay = new TagsDisplay();
        tagsDisplayPanel.add(tagsOnPapersDisplay, gridLayout);

        tagsDisplay = new TagsListDisplay();
        tagsDisplayPanel.add(tagsDisplay, gridLayout);

        add(tagsDisplayPanel, BorderLayout.CENTER);


    }


}




