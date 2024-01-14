package GroupContent;

import MainUI.CommentsDisplay;
import MainUI.RightPanel;
import Managers.DatabaseManager;

import javax.swing.*;
import java.sql.Timestamp;

public class Comment extends HierarchicalContent{


    public Comment(String content, Integer ID, String UserName) {
        super(content, ID, UserName);
    }


    @Override
    protected void addNewComment(HierarchicalContent selectedContent, String contentText) {
        int paper = DatabaseManager.getPaperId("papers4", String.format(CommentsDisplay.getPaper().getName()));

        //DatabaseManager.insertComments(parentID, String.format(commentText), RightPanel.getLoggedInUser(), new Timestamp(System.currentTimeMillis()), paper);
        if (selectedContent.getContent() == "Triple click here to add comment"){

            DatabaseManager.insertComments(null, String.format(contentText), RightPanel.getLoggedInUser(), new Timestamp(System.currentTimeMillis()), paper);
        }

        if (selectedContent.getContent() != "Triple click here to add comment"){
            int parentID = DatabaseManager.getCommentId("comments", String.format(selectedContent.getContent()));
            DatabaseManager.insertComments(parentID, String.format(contentText), RightPanel.getLoggedInUser(), new Timestamp(System.currentTimeMillis()), paper);
        }
    }


}
