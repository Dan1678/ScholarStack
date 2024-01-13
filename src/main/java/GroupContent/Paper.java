package GroupContent;

import Managers.DatabaseManager;
import GroupContent.Comment;
import javax.swing.*;
import java.awt.*;
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
        System.out.println("TEST");

        if (name == null) {
            System.out.println("Paper name is null, no comments");
            return;
        }


        //get this papers id to use for the comments
        int paperId = DatabaseManager.getPaperId("papers4", name);

        //if doesnt exist
        if (paperId == -1) {
            System.out.println("Paper ID not found for name: " + name);
            return;
        }

        // get all the comments
        ArrayList<Comment> comments = DatabaseManager.getCommentsForPaper(paperId);


        // add comments to paper
        for (Comment comment : comments) {
            p.addComment(comment);
            System.out.println("Comment content: " + comment.getContent());
        }

    }

    /*public void PaperComments(Paper p){
        String name = p.getName();
        System.out.println("TEST");

        for (int b = 0; b<= DatabaseManager.getLargestId("comments"); b++){     //go through all comments in table

            //System.out.println("This.getName test: "+ name);
            if(name == null){
                break;
            }

            if (name!= null) {
                String paperID = String.valueOf((DatabaseManager.getPaperId("papers4", name)));

                //check if for multiple comments on same paper
                //
                //gets comment from the paper
                //String paperTitle = (DatabaseManager.readRecord2("papers4", "papertitle", "id", i));


                String CommentContent = DatabaseManager.readRecord3("comments", "content", "paperID", paperID, b);
                if (CommentContent == null){
                    continue;
                }
                System.out.println("Paper from comments name: "+this.name);
                System.out.println("Comment content: "+CommentContent);

                p.addComment(new Comment(CommentContent, null, "testing user"));
            }
        }


    } */

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
