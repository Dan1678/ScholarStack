package GroupContent;

import Managers.DatabaseManager;

import javax.swing.*;
import java.util.ArrayList;


public class Paper {

    private String name, n;
    private ArrayList<Comment> comments;
    private ArrayList<Tag> tags;
    public static ArrayList<Paper> allPapers = new ArrayList<>(); //is public ok for this attribute, I believe paper instances can't be modified from an arraylist?

    public Paper(String paperTitle) {
        this.name = paperTitle;
        comments = new ArrayList<>();
        tags = new ArrayList<>();

        System.out.println("this.name test:  "+this.name);

        addComment(new Comment("Triple click here to add comment", null, null));
        PaperComments(this);

        //find content of commetns where paperID = this paperID and parent id = null
        //find content of comments with parent ID =! null display underneath


        //int paperID;
        //paperID = DatabaseManager.getPaperId("papers4", this.getName());
        //PaperComments(this);

        allPapers.add(this);
    }

    public void PaperComments(Paper p){
        String name = p.getName();

        System.out.println("This.getName test: "+ name);
        if (name!= null) {
            String paperID = String.valueOf((DatabaseManager.getPaperId("papers4", name)));
            String CommentContent = DatabaseManager.readRecord("comments", "content", "paperID", paperID);
            System.out.println("Paper from comments name: "+this.name);
            System.out.println("Comment content: "+CommentContent);

            p.addComment(new Comment(CommentContent, null, "testing user"));
        }

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
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser(String n){
        this.n = name;
        String UserUpload = Managers.DatabaseManager.readRecord("papers4", "username", "papertitle", this.n);
        return UserUpload;
    }

    /*public int getThisPaperId(String n){
        this.n = name;

        int PaperID = Managers.DatabaseManager.getPaperId("papers4", this.n);
        return PaperID;
    } */


}
