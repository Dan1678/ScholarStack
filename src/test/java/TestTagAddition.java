import GroupContent.Comment;
import GroupContent.Paper;
import GroupContent.Tag;
import MainUI.PapersDisplay;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTagAddition {

    @Test
    public void testIfTagAdded() {
        Paper paper = new Paper();
        Tag tag = new Tag("Test tag");

        paper.addTags(tag);

        assertEquals(1, paper.getTags().size(), "One tag should be added to the paper");
        assertEquals(tag, paper.getTags().get(0), "Added tag should match the provided comment");


    }
}
