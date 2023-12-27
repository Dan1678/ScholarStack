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

        //new panel for adding papers button
        JPanel buttonsPanel = new JPanel();
        // buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel topPanel = new JPanel(); // Panel for holding the "Add Reference" button
        topPanel.setLayout(new BorderLayout());

        topPanel.add(buttonsPanel, BorderLayout.CENTER);

        // add some temp papers
        Paper paper = new Paper();
        paper.setName("PAPER TITLE ");

        papersDisplay.addPaperUI(new PaperUI(paper), this);

        paper = new Paper();
        paper.setName("PAPER TITLE 2");
        papersDisplay.addPaperUI(new PaperUI(paper), this);
        paper.setName("PAPER TITLE 3");
        papersDisplay.addPaperUI(new PaperUI(paper), this);


        JButton addReferenceButton = new JButton("Add Reference");
        Paper finalPaper = paper;
        addReferenceButton.addActionListener(new ActionListener() {
            ButtonClickListener listener = null;
            @Override
            public void actionPerformed(ActionEvent e) {
                String reference = JOptionPane.showInputDialog("Enter the Harvard reference:");
                if (reference != null && !reference.isEmpty()&& isValidHarvardReference(reference)) {
                    addNewPaper(reference);
                    String tableName = "papers3";
                    String columns = "username, \"paper title\"";
                    String values = String.format("'testUsername', '%s'", String.format(reference));

                    boolean addPaper = DatabaseManager.insertRecord(tableName, columns, values);

                    papersDisplay.addReference(reference, listener);
                    commentsDisplay.displayComments(finalPaper);
                }else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Harvard reference. Make sure to use , in between and . at the end");
                }
            }
        });

        JButton getBibliographyButton = new JButton("Get Bibliography");
        getBibliographyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the selected papers from PapersDisplay
                ArrayList<Paper> selectedPapers = papersDisplay.getCheckedPapers();

                // Generate the bibliography using the selected papers
                if (!selectedPapers.isEmpty()) {

                    StringBuilder bibliography = new StringBuilder("Selected References:\n");
                    for (Paper paper : selectedPapers) {
                        bibliography.append("- ").append(paper.getName()).append("\n");
                    }

                    // Display the bibliography
                    JOptionPane.showMessageDialog(null, bibliography.toString(), "Bibliography", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // If no papers were selected
                    JOptionPane.showMessageDialog(null, "No papers selected!", "Bibliography", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonsPanel.add(getBibliographyButton);
        buttonsPanel.add(addReferenceButton);
        topPanel.add(papersDisplay, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

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
