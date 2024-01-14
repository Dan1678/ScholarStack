package MainUI;

import GroupContent.Paper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.AWTEventMulticaster.add;

//PapersDisplay hold all the papers and include the methods to get the paper format, to get a reference and check if the tic box is ticked

public class PapersDisplay extends JScrollPane {
    private String reference;

    private JPanel papersPanel; // Panel to hold PaperUI components
    private ArrayList<PaperUI> papersList;

    public PapersDisplay() {
        papersList = new ArrayList<>();
        papersPanel = new JPanel();
        papersPanel.setLayout(new BoxLayout(papersPanel, BoxLayout.Y_AXIS));
        setViewportView(papersPanel);


        //add scroll bars
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        setPreferredSize(new Dimension(400, 400)); // Set size

        // Ensure the viewport follows the panel's size changes
        getViewport().setPreferredSize(papersPanel.getPreferredSize());
    }

    //method to get the paper ui for displaying them on the right panel
    public void addPaperUI(PaperUI paperUI, ButtonClickListener listener) {
        paperUI.setButtonClickListener(listener);
        papersPanel.add(paperUI);
        papersList.add(paperUI);
        revalidate(); // Revalidate the layout after adding papers
        repaint();
    }
    //method to create a paper when a reference is put in by the user
    public void addReference(String reference, ButtonClickListener listener) {
        Paper paper = new Paper(reference);
        paper.setName(reference);
        PaperUI newPaperUI = new PaperUI(paper);

            newPaperUI.setButtonClickListener(listener);
            addPaperUI(newPaperUI, listener);

        }
    //method to check if the tick box related to the paper is selected
    public ArrayList<Paper> getCheckedPapers() {
        ArrayList<Paper> checkedPapers = new ArrayList<>();
        for (Component component : papersPanel.getComponents()) {
            if (component instanceof PaperUI) {
                PaperUI paperUI = (PaperUI) component;
                if (paperUI.getCheckBox().isSelected()) {
                    checkedPapers.add(paperUI.getPaper());
                }
            }
        }
        return checkedPapers;
    }}




