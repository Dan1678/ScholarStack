package GroupContent;

import java.util.ArrayList;

public class Paper {

    private String name;
    private ArrayList<Comment> comments;
    private ArrayList<Tag> tags;

    public Paper() {

    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public ArrayList getComments() {
        return comments;
    }

    public void addTags(Tag tag) {
        tags.add(tag);
    }

    public ArrayList getTags() {
        return tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
