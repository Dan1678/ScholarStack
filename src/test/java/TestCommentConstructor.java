import GroupContent.Comment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCommentConstructor {

    @Test
    public void testCommentConstruction() {

        String content = "Test comment content";
        int id = 1;
        String username = "Test user";

        Comment comment = new Comment(content, id, username);

        assertEquals(content, comment.getContent());
        assertEquals(id, comment.getID());
        assertEquals(username, comment.getUserName());
    }
}
