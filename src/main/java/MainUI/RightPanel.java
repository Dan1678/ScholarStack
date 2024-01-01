//Panel on the right side, displaying papers and comments

package MainUI;

import GroupContent.Paper;
import Managers.CreateLoginForm;
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

    public RightPanel(String UserName) {


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
        paper.setName("Smith, J., Johnson, A. (2010). *The Art of Collaboration*. London: ABC Publishing.");

        papersDisplay.addPaperUI(new PaperUI(paper), this);

        paper = new Paper();
        paper.setName("Smith, J. (2022). \"The Art of Referencing.\" Reference Guides Online. Available at: https://www.example.com/reference-guide [Accessed 30 December 2023].\n");
        papersDisplay.addPaperUI(new PaperUI(paper), this);
        paper = new Paper();
        paper.setName("Smith, J., Johnson, A. (2010). \"The Art of Writing Articles.\" Journal of Academic Writing. 5(2): 123-135.\n");
        papersDisplay.addPaperUI(new PaperUI(paper), this);


        JButton addReferenceButton = new JButton("Add Reference");
        Paper finalPaper = paper;
        addReferenceButton.addActionListener(new ActionListener() {
            ButtonClickListener listener = null;
            @Override
            public void actionPerformed(ActionEvent e) {
                String reference = JOptionPane.showInputDialog("Enter the Harvard reference:");
                if (reference != null && !reference.isEmpty() && isValidHarvardReference(reference)) {
                    addNewPaper(reference);
                    String tableName = "papers3";
                    String columns = "username, \"paper title\"";

                    String values = String.format("'%s', '%s'", UserName, reference);

                    boolean addPaper = DatabaseManager.insertRecord(tableName, columns, values);

                    papersDisplay.addReference(reference, listener);
                    commentsDisplay.displayComments(finalPaper);
                }else if (reference != null && !reference.isEmpty()) {
                    System.out.println("Reference addition canceled.");

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Harvard reference: \n -for a book. Example: Author Last Name, Author Initial(s). (Year).*Book Title*. Place of Publication: Publisher.\n -for an article. Example: Author Last Name, Author Initial(s). (Year). \"Article Title.\" Journal Name. Volume(Issue): Page Numbers.\n -for a website.Example: Author Last Name, Author Initial(s) or Organization. (Year). \\\"Webpage Title.\\\" Website Name. Available at: URL [Accessed Day Month Year].");
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
//                    for (Paper paper : selectedPapers) {
//                        bibliography.append("- ").append(paper.getName()).append("\n");
//                    }
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

    private boolean isValidHarvardReference(String reference) {
        Pattern harvardPattern = Pattern.compile("^([A-Za-z]+( [A-Za-z]+)*), ([A-Za-z]+( [A-Za-z]+)*)\\. \\d{4}\\. [A-Za-z0-9\\s]+\\.$");
        Pattern harvardBookPattern = Pattern.compile("^(?:[A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*(?:,? (?:&|and) [A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*)*\\.\\s*(?:\\([\\d]{4}\\)\\.)?\\s*)?([^\"]+)(?:\\.\\s*(?:\\d+)(?:st|nd|rd|th)?\\s*ed\\.)?\\.\\s*([^:\\.,]+)(?:\\s*:\\s*([^\\.,]+))?(?:[\\.:](.*))?$");
        Pattern harvardArticlePattern = Pattern.compile("^(?:[A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*(?:,? (?:&|and) [A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*)*\\.\\s*(?:\\([\\d]{4}\\)\\.)?\\s*)?\"([^\"]+)\"\\.\\s*([^\\.,]+)\\.\\s*(?:([\\d]+)\\s*\\(([\\d]+)\\)\\s*)?:\\s*([^\\.,]+)(?:[\\.:](.*))?$");
        Pattern harvardWebsitePattern = Pattern.compile("^(?:[A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*(?:,? (?:&|and) [A-Z][a-zA-Z]+(?:, [A-Z][a-zA-Z]+)*)*\\.\\s*(?:\\([\\d]{4}\\)\\.)?\\s*)?(?:\"([^\"]+)\"\\.\\s*)?([^\\.,]+)\\.\\s*Available at: (https?://\\S+) \\[Accessed (\\d{1,2}\\s(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s\\d{4})\\]");

        Matcher harvardMatcher = harvardPattern.matcher(reference);
        Matcher bookMatcher = harvardBookPattern.matcher(reference);
        Matcher articleMatcher = harvardArticlePattern.matcher(reference);
        Matcher websiteMatcher=harvardWebsitePattern.matcher(reference);

        // Check if the reference matches any of the patterns
        return harvardMatcher.matches() || bookMatcher.matches() || articleMatcher.matches() || websiteMatcher.matches();


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
