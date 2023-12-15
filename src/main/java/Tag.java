import java.util.ArrayList;

public class Tag {

    private String name;
    ArrayList<Tag> subTags;

    public Tag(String name) {
        this.name = name;
        subTags = new ArrayList<>();
    }

    public void editName(String name) {
        this.name = name;
    }

    public void addSubTag(Tag tag) {
        subTags.add(tag);
    }

    public void deleteSubTag(Tag tag) {
        // will not work well for removing tags with subtags. todo
        subTags.remove(tag);
    }

    public ArrayList<Tag> getSubTags() {
        return subTags;
    }

}
