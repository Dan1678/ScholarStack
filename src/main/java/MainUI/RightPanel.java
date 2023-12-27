//Panel on the right side, displaying papers and comments

package MainUI;

import GroupContent.Paper;
import Managers.DatabaseManager;
import Managers.PasswordHashing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RightPanel extends JPanel implements ButtonClickListener{

    //private CommentsDisplay commentsDisplay;
    private PapersDisplay papersDisplay;

    private CommentsDisplay commentsDisplay;

    public RightPanel() {

        // todo may be able to get rid of this as it only contains the papers display

        setLayout(new BorderLayout());

        commentsDisplay = new CommentsDisplay();
        papersDisplay = new PapersDisplay();

        add(commentsDisplay, BorderLayout.CENTER);
        add(papersDisplay, BorderLayout.NORTH);



//        //add some temp papers
//        Paper paper = new Paper();
//        paper.setName("PAPER TITLE ");
//
//        papersDisplay.addPaperUI(new PaperUI(paper), this);
//
//        paper = new Paper();
//        paper.setName("PAPER TITLE 2");
//        papersDisplay.addPaperUI(new PaperUI(paper), this);
//
//        papersDisplay.addPaperUI(new PaperUI(paper), this);
//        papersDisplay.addPaperUI(new PaperUI(paper), this);
//        papersDisplay.addPaperUI(new PaperUI(paper), this);
//        papersDisplay.addPaperUI(new PaperUI(paper), this);
//        papersDisplay.addPaperUI(new PaperUI(paper), this);
//        papersDisplay.addPaperUI(new PaperUI(paper), this);
//        papersDisplay.addPaperUI(new PaperUI(paper), this);
//        papersDisplay.addPaperUI(new PaperUI(paper), this);
//        papersDisplay.addPaperUI(new PaperUI(paper), this);
//        papersDisplay.addPaperUI(new PaperUI(paper), this);


        JButton addReferenceButton = new JButton("Add Reference");
        addReferenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reference = JOptionPane.showInputDialog("Enter the Harvard reference:");
                if (reference != null && !reference.isEmpty()&& isValidHarvardReference(reference)) {
                    addNewPaper(reference);
                }else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Harvard reference. Make sure to use , in between and . at the end");
                }
            }
        });

        add(addReferenceButton);

    }

    private boolean isValidHarvardReference(String reference) {
        Pattern harvardPattern = Pattern.compile("^([A-Za-z]+), ([A-Za-z]+), \\d{4}\\.$");
        Matcher matcher = harvardPattern.matcher(reference);
        return matcher.matches();
    }

    @Override
         public void onButtonClicked(Paper paper) {


        commentsDisplay.displayComments(paper);
    }

    public void addNewPaper(String name) {
        //add a new paper to the database
        System.out.println(name);
        ArrayList<String> papersList = getPapersFromDB();
        redrawPapers(papersList);
    }

    public void redrawPapers(ArrayList<String> papersList) {

        for (String paperName : papersList) {
            Paper paper = new Paper();
            paper.setName(paperName);

            papersDisplay.addPaperUI(new PaperUI(paper), this);
        }
    }

    public ArrayList<String> getPapersFromDB() {
        //get papers from the database and display them on the UI
        //maybe wont be a string
        ArrayList<String> papersList = new ArrayList<>();


        //code

        return  papersList;
    }

}
