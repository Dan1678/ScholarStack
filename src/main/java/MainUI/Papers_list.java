package MainUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Papers_list {
    private ArrayList<String> Papers = new ArrayList<>();
    private Map<String, String> comments = new HashMap<>();

    public void addPaper(String reference) {


            Papers.add(reference);
    }
    public void addComment(String reference, String comment) {
        comments.put(reference, comment);
    }

    public ArrayList<String> getReferenceList() {
        return Papers;
    }
    public Map<String, String> getCommentsMap() {
        return comments;
    }
}


