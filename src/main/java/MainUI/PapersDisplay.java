package MainUI;

import GroupContent.Paper;

import javax.swing.*;
import java.awt.*;

import static java.awt.AWTEventMulticaster.add;


/*public class PapersDisplay extends JPanel{

   private ArrayList<PaperUI> papers;

    public PapersDisplay() {
        //set up panel

        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        papers = new ArrayList<>();


        //todo - Add adelinas stuff (inc. scroll)

    }



    public void addPaper(PaperUI paper, ButtonClickListener listener) {
        paper.setButtonClickListener(listener);
        papers.add(paper);
    }

    public void drawUI() {
        for (PaperUI paper : papers) {
            add(paper);
        }
    }
}
*/

public class PapersDisplay extends JScrollPane {
    private String reference;

    private JPanel papersPanel; // Panel to hold PaperUI components


    public PapersDisplay() {
        papersPanel = new JPanel();
        papersPanel.setLayout(new BoxLayout(papersPanel, BoxLayout.Y_AXIS));

        setViewportView(papersPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Additional settings for the JScrollPane
        setPreferredSize(new Dimension(400, 400)); // Set your preferred size

        // Ensure the viewport follows the panel's size changes
        getViewport().setPreferredSize(papersPanel.getPreferredSize());
    }
    public void addPaperUI(PaperUI paperUI, ButtonClickListener listener) {
        paperUI.setButtonClickListener(listener);
        papersPanel.add(paperUI);

        revalidate(); // Revalidate the layout after adding papers
        repaint();
    }
    public void addReference(String reference, ButtonClickListener listener) {
        Paper paper = new Paper();
        paper.setName(reference);
        PaperUI newPaperUI = new PaperUI(paper);


            newPaperUI.setButtonClickListener(listener);
            addPaperUI(newPaperUI, listener);

        }
//    public void addPaperUI(PaperUI paper, ButtonClickListener listener) {
//
//        // For database - this is where it will add a paper
//
//        paper.setButtonClickListener(listener);
//        papersPanel.add(paper);
//        revalidate(); // Revalidate the layout after adding papers
//        repaint(); // Repaint to reflect changes
    }



