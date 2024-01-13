import GroupContent.Paper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestPaperCreation {

    @Test
    public void testIfPaperCreated() {
        Paper paper = new Paper();

        assertNotNull(paper, "Paper should not be null after creation");
        assertNotNull(paper.getComments(), "Comments list should not be null");
        assertNotNull(paper.getTags(), "Tags list should not be null");
        assertTrue(paper.getComments().size()==1, "Comments list should only have \"Triple click here to add comment\"");
        assertTrue(paper.getTags().isEmpty(), "Tags list should be empty initially");
    }

}
