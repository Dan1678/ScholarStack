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
    public static ArrayList<Paper> allPapers = new ArrayList<>();

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

        String commentsNew = DatabaseManager.readRecord2("comments","content", "paperID", 2);
        System.out.println(commentsNew);

        addComment(new Comment(commentsNew, null, "testing display comments"));
        System.out.println(this.getUser(this.getName()));

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
        Integer paperId = DatabaseManager.getPaperId("papers4", name);

        //if doesnt exist
        if (paperId == null) {
            System.out.println("Paper ID not found for name: " + name);
            return;
        }


        // get all the comments
        ArrayList<Comment> comments = DatabaseManager.getCommentsForPaper(paperId);


        // add comments to paper
        for (Comment comment : comments) {
            int parentID = DatabaseManager.getCommentParentID("comments", comment.toString());
            if (parentID == 0){ //Get all comments with parent ID of null
                //recurse on each of those to get children
                int commentID = DatabaseManager.getCommentId("comments", comment.toString());
                Comment commentsWithChildren = getChildComments(comment, commentID);
                if (commentsWithChildren != null) {
                    this.comments.get(0).addSubContent(commentsWithChildren);
                } else {
                    this.comments.get(0).addSubContent(comment);
                }

            }

        }

    }

    private Comment getChildComments(Comment parentComment, int parentID) {
        ArrayList<Comment> children =  DatabaseManager.getSubComments(parentID); //get all child comments of the parent ID

        //BASE CASE: there are no tags with the parent ID of the current comment (i.e. no children)
        if (children.size() == 0) {
            return null;
        }

        //RECURSIVE CASE: call the current method on each of the children
        for (Comment child : children) {
            int childID = DatabaseManager.getCommentId("comments", child.toString());
            Comment childrenOfChild = getChildComments(child, childID);
            if (childrenOfChild != null) {
                child.addSubContent(childrenOfChild);
            }
            parentComment.addSubContent(child);
        }

        return  parentComment;
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
