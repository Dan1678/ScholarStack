import GroupContent.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTagConstructor {
    @Test
    public void testTag() {
        String tagName = "Test tag";
        Tag tag = new Tag(tagName);

        assertEquals(tagName, tag.getName());
    }
}
