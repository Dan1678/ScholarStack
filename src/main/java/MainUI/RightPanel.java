//Panel on the right side, displaying papers and comments

package MainUI;

import GroupContent.Paper;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {

    //private CommentsDisplay commentsDisplay;
    private PapersDisplay papersDisplay;

    public RightPanel() {

        // todo may be able to get rid of this as it only contains the papers display

        setLayout(new BorderLayout());

        //commentsDisplay = new CommentsDisplay();
        papersDisplay = new PapersDisplay();

        //add(commentsDisplay, BorderLayout.SOUTH);
        add(papersDisplay, BorderLayout.NORTH);



        //add some temp papers
        Paper paper = new Paper();
        paper.setName("PAPER TITLE ");

        papersDisplay.addPaper(new PaperUI(paper));
        papersDisplay.addPaper(new PaperUI(paper));
        papersDisplay.addPaper(new PaperUI(paper));
        papersDisplay.drawUI();
    }

}
