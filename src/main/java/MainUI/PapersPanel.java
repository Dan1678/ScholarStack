package MainUI;

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

        public PapersPanel() {

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
                    if (reference != null && !reference.isEmpty()&& isValidHarvardReference(reference)) {
                        papersList.addPaper(reference);
                        displayPapers();
                    }else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid Harvard reference. Make sure to use , in between and . at the end");
                    }
                }
            });
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // FlowLayout for center alignment
            buttonPanel.add(addReferenceButton);
            add(buttonPanel, BorderLayout.SOUTH);

            displayPapers();
           // JPanel buttonPanel = new JPanel();
            //buttonPanel.add(addReferenceButton);

           // setLayout(new BorderLayout());
           // add(addReferenceButton,BorderLayout.SOUTH);
            //add(scrollPane,BorderLayout.CENTER);


        }

        public void displayPapers() {
            displayArea.setText("");//clear before updating
            ArrayList<String> references = papersList.getReferenceList();
            StringBuilder stringBuilder = new StringBuilder();

            for (String reference : references) {
                stringBuilder.append(reference).append("\n");
            }

            displayArea.setText(stringBuilder.toString());

        }
        private boolean isValidHarvardReference(String reference) {
        Pattern harvardPattern = Pattern.compile("^([A-Za-z]+), ([A-Za-z]+), \\d{4}\\.$");
        Matcher matcher = harvardPattern.matcher(reference);
        return matcher.matches();
    }



}










