package GroupContent;

import MainUI.CommentsDisplay;
import MainUI.RightPanel;
import Managers.DatabaseManager;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Tag extends HierarchicalContent{

    private String name;
    ArrayList<Tag> subTags;

    public Tag(String content, Integer ID, String UserName) {
        super(content, ID, UserName);
    }


    public Tag(String content) {
        super(content, null, null);
    }

    @Override
    protected void addNewContent(HierarchicalContent selectedContent, String contentText) {
        //int paper = DatabaseManager.getPaperId("", String.format(CommentsDisplay.getPaper().getName()));

        //DatabaseManager.insertComments(parentID, String.format(commentText), RightPanel.getLoggedInUser(), new Timestamp(System.currentTimeMillis()), paper);
        if (selectedContent.getContent() == "Triple click here to add tags"){
            DatabaseManager.insertTags(0, String.format(contentText));
        }

        else if (selectedContent.getContent() != "Triple click here to add tags"){
            int parentID = DatabaseManager.getId("\"Tags\"", String.format(selectedContent.getContent()), "\"TagName\"");
            DatabaseManager.insertTags(parentID, String.format(contentText));
        }
    }
    public String getName() {
        return name;
    }

   /* public void editName(String name) {
        this.name = name;
    }

    public void addSubTag(Tag tag) {
        subTags.add(tag);
    }

    public void deleteSubTag(Tag tag) {
        // will not work well for removing tags with subtags. todo
        subTags.remove(tag);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Tag> getSubTags() {
        return subTags;
    }


    @Override
    public String toString() {
        return name;
    }*/
}
