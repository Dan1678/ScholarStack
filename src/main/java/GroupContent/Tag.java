package GroupContent;

import Managers.DatabaseManager;

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

    //Similar to method in comment to ensure it connects to the appropriate table in the database
    @Override
    protected void addNewContent(HierarchicalContent selectedContent, String contentText) {
        if (selectedContent.getContent() == "Triple click here to add tags"){
            //Parent tag
            DatabaseManager.insertTags(0, String.format(contentText));
        } else {
            //Sub tag
            int parentID = DatabaseManager.getId("\"Tags\"", String.format(selectedContent.getContent()), "\"TagName\"");
            DatabaseManager.insertTags(parentID, String.format(contentText));
        }
    }
    public String getName() {
        return name;
    }

}
