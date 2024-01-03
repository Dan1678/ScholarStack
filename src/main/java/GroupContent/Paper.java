package GroupContent;

import Managers.DatabaseManager;

import java.util.ArrayList;


public class Paper {

    private String name, n;
    private ArrayList<Comment> comments;
    private ArrayList<Tag> tags;
    public static ArrayList<Paper> allPapers = new ArrayList<>(); //is public ok for this attribute, I believe paper instances can't be modified from an arraylist?

    public Paper() {
        comments = new ArrayList<>();
        tags = new ArrayList<>();

        addComment(new Comment("Triple click here to add comment", null, null));
        System.out.println(this.getUser(this.getName()));

        allPapers.add(this);
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

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public ArrayList<String> getTagsStringList() {
        ArrayList<String> tagsStringList = new ArrayList<>();;
        for(Tag tag:tags) {
            String tagName = tag.toString();
            tagsStringList.add(tagName);
        }
        return tagsStringList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser(String n){
        this.n = name;
        String UserUpload = Managers.DatabaseManager.readRecord("papers4", "username", "papertitle", this.n);
        return UserUpload;
    }


}
