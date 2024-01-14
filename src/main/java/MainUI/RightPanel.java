//Panel on the right side, displaying papers and comments

package MainUI;

import GroupContent.Paper;
import Managers.DatabaseManager;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RightPanel extends JPanel implements ButtonClickListener{
    private PapersDisplay papersDisplay;
    private CommentsDisplay commentsDisplay;
    public static String UserName;

    public RightPanel(String UserName) {
        RightPanel.UserName = UserName;

        setLayout(new BorderLayout());

        commentsDisplay = new CommentsDisplay();
        papersDisplay = new PapersDisplay();

        add(commentsDisplay, BorderLayout.CENTER);
        add(papersDisplay, BorderLayout.NORTH);

        //new panel for adding papers button
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Panel for holding the "Add Reference" button
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        topPanel.add(buttonsPanel, BorderLayout.CENTER);


        //get the papers from the data base
        Paper paper = new Paper(null);
        for(int i = 0; i <= DatabaseManager.getLargestId("papers4"); i++) {

            String paperTitle = (DatabaseManager.readRecord2("papers4", "papertitle", "id", i));
            paper = new Paper(paperTitle);

            if (paperTitle == null) {
                continue;
            }


            System.out.println("Paper name: " + paperTitle);
            String username = String.valueOf(DatabaseManager.readRecord2("papers4", "username", "id", i));
            System.out.println("User uploaded: " + username);

            paper.setName(paperTitle);
            papersDisplay.addPaperUI(new PaperUI(paper), this);

        }



        JButton addReferenceButton = new JButton("Add Reference");
        Paper finalPaper = paper;
        addReferenceButton.addActionListener(new ActionListener() {
             ButtonClickListener listener = null;
            @Override
            //add the new reference to the database and check if it fits the Harvard style
            public void actionPerformed(ActionEvent e) {
                String reference = JOptionPane.showInputDialog("Enter the Harvard reference:");
                if (reference != null && !reference.isEmpty() && isValidHarvardReference(reference)) {
                    addNewPaper(reference);
                    String tableName = "papers4";
                    String columns = "username, papertitle";

                    String values = String.format("'%s', '%s'", UserName, reference);

                    boolean addPaper = DatabaseManager.insertRecord(tableName, columns, values);

                    papersDisplay.addReference(reference, listener);
                    commentsDisplay.displayComments(finalPaper);
//

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Harvard reference: \n -for a book. Example: Author Last Name, Author Initial(s). (Year).*Book Title*. Place of Publication: Publisher.\n -for an article. Example: Author Last Name, Author Initial(s). (Year). \"Article Title.\" Journal Name. Volume(Issue): Page Numbers.\n -for a website.Example: Author Last Name, Author Initial(s) or Organization. (Year). \\\"Webpage Title.\\\" Website Name. Available at: URL [Accessed Day Month Year].");
                }
            }
        });

        //new method for gnerating a bibliography from the papers which have a ticed box and put them in numerical order
        JButton getBibliographyButton = new JButton("Get Bibliography");
        getBibliographyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the selected papers from PapersDisplay
                ArrayList<Paper> selectedPapers = papersDisplay.getCheckedPapers();

                // Generate the bibliography using the selected papers
                if (!selectedPapers.isEmpty()) {

                    StringBuilder bibliography = new StringBuilder("Selected References:\n");
//
                     int counter = 1;

                    for (Paper paper : selectedPapers) {
                        bibliography.append(counter).append(". ").append(paper.getName()).append("\n");
                        counter++;
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
    //create pattern to check the references put in by the user
    private boolean isValidHarvardReference(String reference) {
        Pattern harvardBookPattern = Pattern.compile("^(?:[A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*(?:,? (?:&|and) [A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*)*\\.\\s*(?:\\(\\d{4}\\)\\.)?\\s*)?([^\"]+)(?:\\.\\s*\\d+(?:st|nd|rd|th)?\\s*ed\\.)?\\.\\s*([^:.,]+)(?:\\s*:\\s*([^.,]+))?(?:[.:](.*))?$");
        Pattern harvardArticlePattern = Pattern.compile("^(?:[A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*(?:,? (?:&|and) [A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*)*\\.\\s*(?:\\(\\d{4}\\)\\.)?\\s*)?\"([^\"]+)\"\\.\\s*([^.,]+)\\.\\s*(?:(\\d+)\\s*\\((\\d+)\\)\\s*)?:\\s*([^.,]+)(?:[.:](.*))?$");
        Pattern harvardWebsitePattern = Pattern.compile("^(?:[A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*(?:,? (?:&|and) [A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*)*\\.\\s*(?:\\(\\d{4}\\)\\.)?\\s*)?(?:\"([^\"]+)\"\\.\\s*)?([^.,]+)\\.\\s*Available at: (https?://\\S+) \\[Accessed (\\d{1,2}\\s(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s\\d{4})]");

        Matcher bookMatcher = harvardBookPattern.matcher(reference);
        Matcher articleMatcher = harvardArticlePattern.matcher(reference);
        Matcher websiteMatcher=harvardWebsitePattern.matcher(reference);

        // Check if the reference matches any of the patterns
        return bookMatcher.matches() || articleMatcher.matches() || websiteMatcher.matches();


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
            Paper paper = new Paper(paperName);
            paper.setName(paperName);
            papersDisplay.addPaperUI(new PaperUI(paper), this);
        }
    }

    public ArrayList<String> getPapersFromDB() {
        //get papers from the database and display them on the UI
        ArrayList<String> papersList = new ArrayList<>();
        return  papersList;
    }

    //get the username of the person that posted the reference
    public static String getLoggedInUser(){
        return UserName;
    }

}
