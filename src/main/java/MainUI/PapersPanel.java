package MainUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
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

            displayPapers();
        }

        public void displayPapers() {
            ArrayList<String> references = papersList.getReferenceList();
            StringBuilder stringBuilder = new StringBuilder();

            for (String reference : references) {
                stringBuilder.append(reference).append("\n");
            }

            displayArea.setText(stringBuilder.toString());


        }


    }










