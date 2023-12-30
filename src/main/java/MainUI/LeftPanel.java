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
    private TagsDisplay tagsDisplay;
    private Tag selectedTag;





    public LeftPanel(String UserName) {
        //set up panel
        setPreferredSize(new Dimension(300, 0)); //panel has a set width
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BorderLayout());


        //set up tags display
        tagsDisplay = new TagsDisplay();
        add(tagsDisplay, BorderLayout.CENTER);

        //add welcomeUser message
        WelcomeUser welcomeUser = new WelcomeUser();
        JPanel welcomePanel = welcomeUser.createWelcomePanel(UserName);
        add(welcomePanel, BorderLayout.NORTH);

//        JPanel welcomePanel = new JPanel();
//        JLabel welLabel = new JLabel();
//        welLabel.setText("Welcome "+UserName+"!");
//        welcomePanel.add(welLabel);
//
//        add(welLabel, BorderLayout.NORTH);



    }







}




