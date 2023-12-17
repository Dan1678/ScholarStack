//Panel on the right side, displaying papers and comments

package MainUI;

import GroupContent.Paper;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {

    CommentsDisplay commentsDisplay;
    PapersDisplay papersDisplay;

    public RightPanel() {

        setLayout(new BorderLayout());

        commentsDisplay = new CommentsDisplay();
        papersDisplay = new PapersDisplay();

        add(commentsDisplay, BorderLayout.SOUTH);
        add(papersDisplay, BorderLayout.NORTH);

        Paper paper = new Paper();
        paper.setName("PAPER TITLE");

        papersDisplay.addPaper(new PaperUI(paper));
        papersDisplay.addPaper(new PaperUI(paper));
        papersDisplay.addPaper(new PaperUI(paper));
        papersDisplay.drawUI();
    }

}
