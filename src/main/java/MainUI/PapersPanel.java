package MainUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PapersPanel extends JPanel {

        private Papers_list papersList;
        private JTextArea displayArea;

        public PapersPanel() {
            papersList = new Papers_list();
            setSize(1100, 930);
            setLocation(340, 20);
            setVisible(true);
            setBorder(BorderFactory.createLineBorder(Color.black));

            displayArea = new JTextArea(30, 50);
            displayArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(displayArea);
            add(scrollPane);
            JButton addReferenceButton = new JButton("Add Reference");
            addReferenceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String reference = JOptionPane.showInputDialog("Enter the Harvard reference:");
                    if (reference != null && !reference.isEmpty()) {
                        papersList.addPaper(reference);
                        displayPapers();
                    }
                }
            });
            add(addReferenceButton);

            displayPapers();
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


    }










