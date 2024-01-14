package MainUI;

import GroupContent.Tag;
import Managers.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class TagsListDisplay extends JScrollPane {
    //scroll pane which holds multiple comment panels
    private JPanel tagsListPanel; // Panel to hold comment components


    public TagsListDisplay() {
        tagsListPanel = new JPanel();
        tagsListPanel.setLayout(new BorderLayout());
        add(tagsListPanel);
        displayTags();


        //add scroll bars
        setViewportView(tagsListPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    public void displayTags() {
        //display all tags
        //In future this will be called to refresh tree
        tagsListPanel.removeAll();
        Tag tag = getTagsFromDB();
        tagsListPanel.add(tag.getDisplayTree(), BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private Tag getTagsFromDB(){

        // get all the comments
        ArrayList<Tag> tags = DatabaseManager.getTags();

        Tag mainTag = new Tag("Triple click here to add tags", null, null);


        // add comments to paper
        for (Tag tag : tags) {
            int parentID = DatabaseManager.getTagParentID("\"Tags\"", tag.toString());
            if (parentID == 0){ //Get all comments with parent ID of null
                //recurse on each of those to get their children
                int tagID = DatabaseManager.getId("\"Tags\"", tag.toString(), "\"TagName\"");
                Tag tagWithChildren = getChildTags(tag, tagID);
                if (tagWithChildren != null) {
                    mainTag.addSubContent(tagWithChildren);
                } else {
                    mainTag.addSubContent(tag);
                }

            }

        }

        return mainTag;

    }

    private Tag getChildTags(Tag parentTag, int parentID) {
        ArrayList<Tag> children =  DatabaseManager.getSubTags(parentID); //get all child comments of the parent ID

        //BASE CASE: there are no tags with the parent ID of the current comment (i.e. no children)
        if (children.size() == 0) {
            return null;
        }

        //RECURSIVE CASE: call the current method on each of the children
        for (Tag child : children) {
            int childID = DatabaseManager.getId("\"Tags\"", child.toString(), "\"TagName\"");
            Tag childrenOfChild = getChildTags(child, childID);
            if (childrenOfChild != null) {
                child = childrenOfChild;
            }
            parentTag.addSubContent(child);
        }

        return  parentTag;
    }
}