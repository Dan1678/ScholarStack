package GroupContent;

import java.util.ArrayList;



public class Paper {

    private String name, n;
    private ArrayList<Comment> comments;
    private ArrayList<Tag> tags;

    public Paper() {
        comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public ArrayList<Comment> getComments() {
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

    public String getUser(String n){
        this.n = n;
        String UserUpload = Managers.DatabaseManager.readRecord("papers3", "username", "paper title", this.n);
        System.out.println(UserUpload);
        return UserUpload;
    }
}
