package MainUI;
import Managers.DatabaseConnector;
import Managers.DatabaseManager;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PapersPanel extends JPanel {

        private Papers_list papersList;
        private JTextArea displayArea;
         private JPanel commentPanel;


    public PapersPanel() {

        commentPanel = new JPanel();
        commentPanel.setLayout(new BorderLayout());
        commentPanel.setBorder(BorderFactory.createTitledBorder("Comments"));

        setLayout(new BorderLayout());
            papersList = new Papers_list();
            //Set up panel
            setVisible(true);
            setBorder(BorderFactory.createLineBorder(Color.black));




            displayArea = new JTextArea(250, 505);
            displayArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(displayArea);
            scrollPane.setPreferredSize(new Dimension(500, 300));//make it large otherwise it won t show
            add(scrollPane,BorderLayout.CENTER);
            JButton addReferenceButton = new JButton("Add Reference");
            addReferenceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String reference = JOptionPane.showInputDialog("Enter the Harvard reference:");
                    if (!reference.isEmpty()&& isValidHarvardReference(reference)) {

                        papersList.addPaper(reference);

                        displayPapers();
                    }else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid Harvard reference. Make sure to use , in between and . at the end");
                    }
                }
            });

            JButton generateBibliographyButton = new JButton("Generate Bibliography");
            generateBibliographyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    generateBibliography();
                }
            });
            JButton addCommentButton = new JButton("Add Comment");
            addCommentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedReference = getSelectedReference();
                if (selectedReference != null) {
                    String comment = JOptionPane.showInputDialog("Enter a comment for this reference:");
                    if (comment != null && !comment.isEmpty()) {
                        // Add the comment to the comment panel
                        addComment(selectedReference, comment);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a reference to add a comment.");
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // FlowLayout for center alignment
            buttonPanel.add(addReferenceButton);
            buttonPanel.add(generateBibliographyButton);
            buttonPanel.add(addCommentButton);
            add(buttonPanel, BorderLayout.SOUTH);
            add(commentPanel, BorderLayout.EAST);

            displayPapers();
           // JPanel buttonPanel = new JPanel();
            //buttonPanel.add(addReferenceButton);

           // setLayout(new BorderLayout());
           // add(addReferenceButton,BorderLayout.SOUTH);
            //add(scrollPane,BorderLayout.CENTER);


        }
    private void addComment(String selectedReference, String comment) {
        // Implement logic to add the comment to the comment panel
        /*JLabel commentLabel = new JLabel(selectedReference + ": " + comment);
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));//organise comments one below the other
        commentPanel.add(commentLabel);
        commentPanel.revalidate();
        commentPanel.repaint();*/
        JLabel commentLabel = new JLabel("<html>" + selectedReference + ": " + comment + "</html>");
        commentLabel.setPreferredSize(new Dimension(800, 50)); // Fixed size for each comment

        // Set line wrap and HTML rendering for the label
        commentLabel.setVerticalAlignment(SwingConstants.TOP);
        commentLabel.setHorizontalAlignment(SwingConstants.LEFT);
        commentLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        commentLabel.setVerticalTextPosition(SwingConstants.TOP);
        commentLabel.setOpaque(true);

        // Create a JScrollPane for commentPanel
        JScrollPane commentScrollPane = new JScrollPane(commentLabel);
        commentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Set preferred size for the scroll pane
        commentScrollPane.setPreferredSize(new Dimension(200, 300)); // Adjust size as needed

        // Add the scroll pane to your UI layout instead of commentPanel directly
        add(commentScrollPane, BorderLayout.SOUTH);

        // Revalidate and repaint the scrollPane
        commentScrollPane.revalidate();
        commentScrollPane.repaint();

    }


    public void displayPapers() {
            displayArea.setText("");//clear before updating
            ArrayList<String> references = papersList.getReferenceList();
            StringBuilder stringBuilder = new StringBuilder();


            for (int i = 0; i < references.size(); i++) {
                stringBuilder.append(i + 1).append(". ").append(references.get(i)).append("\n");
            }

            displayArea.setText(stringBuilder.toString());

        }
        private boolean isValidHarvardReference(String reference) {
        Pattern harvardPattern = Pattern.compile("^([A-Za-z]+), ([A-Za-z]+), \\d{4}\\.$");
        Matcher matcher = harvardPattern.matcher(reference);
        return matcher.matches();
    }
    private void generateBibliography() {
        ArrayList<String> references = papersList.getReferenceList();
        StringBuilder bibliography = new StringBuilder("Bibliography:\n");


        for (int i = 0; i < references.size(); i++) {
            bibliography.append(i + 1).append(". ").append(references.get(i)).append("\n");
        }

        // Display the bibliography in a dialog
        JTextArea bibliographyTextArea = new JTextArea(bibliography.toString(), 20, 50);
        bibliographyTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bibliographyTextArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Generated Bibliography", JOptionPane.PLAIN_MESSAGE);
    }

    private String getSelectedReference() {
        // Fetch the selected reference from your UI element (e.g., displayArea)

        String selectedText = displayArea.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            // Extract the reference from the selected text

            return selectedText.trim(); // Trim any extra spaces
        }
        return null;
    }
}














