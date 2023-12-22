//Panel on the right side, displaying papers and comments

package MainUI;

import GroupContent.Paper;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel implements ButtonClickListener{

    //private CommentsDisplay commentsDisplay;
    private PapersDisplay papersDisplay;

    private CommentsDisplay commentsDisplay;

    public RightPanel() {

        // todo may be able to get rid of this as it only contains the papers display

        setLayout(new BorderLayout());

        commentsDisplay = new CommentsDisplay();
        papersDisplay = new PapersDisplay();

        add(commentsDisplay, BorderLayout.SOUTH);
        add(papersDisplay, BorderLayout.NORTH);



        //add some temp papers
        Paper paper = new Paper();
        paper.setName("PAPER TITLE ");

        papersDisplay.addPaper(new PaperUI(paper), this);

        paper = new Paper();
        paper.setName("PAPER TITLE 2");
        papersDisplay.addPaper(new PaperUI(paper), this);

        papersDisplay.addPaper(new PaperUI(paper), this);
        papersDisplay.addPaper(new PaperUI(paper), this);
        papersDisplay.addPaper(new PaperUI(paper), this);
        papersDisplay.addPaper(new PaperUI(paper), this);
        papersDisplay.addPaper(new PaperUI(paper), this);
        papersDisplay.addPaper(new PaperUI(paper), this);
        papersDisplay.addPaper(new PaperUI(paper), this);
        papersDisplay.addPaper(new PaperUI(paper), this);
        papersDisplay.addPaper(new PaperUI(paper), this);
        papersDisplay.addPaper(new PaperUI(paper), this);
        //papersDisplay.drawUI();
    }

    @Override
         public void onButtonClicked(Paper paper) {
        commentsDisplay.displayComments(paper);
    }

}
