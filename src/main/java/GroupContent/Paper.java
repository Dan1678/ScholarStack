package GroupContent;

import Managers.DatabaseManager;
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


        addComment(new Comment("Triple click here to add comment", null, null));

        PaperComments(this);

        String commentsNew = DatabaseManager.readRecord2("comments","content", "paperID", 2);

        addComment(new Comment(commentsNew, null, "testing display comments"));

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
                //recurse on each of those to get their children
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
                child = childrenOfChild;
            }
            parentComment.addSubContent(child);
        }

        return  parentComment;
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
