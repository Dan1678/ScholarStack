package MainUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PapersDisplay extends JPanel{

    private ArrayList<PaperUI> papers;

    public PapersDisplay() {
        //set up panel

        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        papers = new ArrayList<>();

    }

    public void addPaper(PaperUI paper) {
        papers.add(paper);
    }

    public void drawUI() {
        for (PaperUI paper : papers) {
            add(paper);
        }
    }
}
