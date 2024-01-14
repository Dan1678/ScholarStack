package GroupContent;

import MainUI.CommentsDisplay;
import MainUI.RightPanel;
import Managers.DatabaseManager;


import java.sql.Timestamp;

public class Comment extends HierarchicalContent{


    public Comment(String content, Integer ID, String UserName) {
        super(content, ID, UserName);
    }


    //Similar to method in tag to ensure it connects to the appropriate table in the database
    @Override
    protected void addNewContent(HierarchicalContent selectedContent, String contentText) {
        int paper = DatabaseManager.getPaperId("papers4", String.format(CommentsDisplay.getPaper().getName()));

        if (selectedContent.getContent() == "Triple click here to add comment"){
            //if it is a starting comment
            DatabaseManager.insertComments(null, String.format(contentText), RightPanel.getLoggedInUser(), new Timestamp(System.currentTimeMillis()), paper);
        } else {
            //if it is a reply to a comment
            int parentID = DatabaseManager.getCommentId("comments", String.format(selectedContent.getContent()));
            DatabaseManager.insertComments(parentID, String.format(contentText), RightPanel.getLoggedInUser(), new Timestamp(System.currentTimeMillis()), paper);
        }
    }


}
