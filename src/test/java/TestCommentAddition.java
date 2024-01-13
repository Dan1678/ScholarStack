import GroupContent.Comment;
import GroupContent.Paper;
import MainUI.PapersDisplay;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCommentAddition {

    @Test
    public void testIfCommentsAdded() {
        Paper paper = new Paper();
        Comment comment = new Comment("Test Comment", null, null);

        paper.addComment(comment);

        assertEquals(2, paper.getComments().size(), "One comment should be added to the paper");
        assertEquals(comment, paper.getComments().get(1), "Added comment should match the provided comment");


    }
}
