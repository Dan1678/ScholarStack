package MainUI;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*class Papers_list {
    private ArrayList<String> Papers = new ArrayList<>();



    public void addPaper(String reference) {


        Pattern harvardPattern = Pattern.compile("^([A-Z][a-zA-Z]+), ([A-Z][a-zA-Z]+), ([0-9]{4})\\.$");
        Matcher matcher = harvardPattern.matcher(reference);




            if (matcher.matches()) {
                Papers.add(reference);
                ;
            } else {
                System.out.println("Please enter the reference in Harvard format (Last Name, First Name, Year).");
            }
        }
    }



    public ArrayList<String> getReferenceList() {


        return Papers;
    }

}

//for (int i = 0; i < Papers.size(); i++) {
       // System.out.println(cars.get(i));
*/
public class Papers_list {
    private ArrayList<String> Papers = new ArrayList<>();

    public void addPaper(String reference) {
        //Pattern harvardPattern = Pattern.compile("^([A-Z][a-zA-Z]+), ([A-Z][a-zA-Z]+), ([0-9]{4})\\.$");
      /*  Pattern harvardPattern = Pattern.compile("^([A-Za-z]+) , ([A-Za-z]+), \\d{4}\\.$");
        Matcher matcher = harvardPattern.matcher(reference);

        if (matcher.matches()) {
            Papers.add(reference);
        } else {
            System.out.println("Please enter the reference in Harvard format (Last Name, First Name, Year).");
        }*/

            Papers.add(reference);
    }

    public ArrayList<String> getReferenceList() {
        return Papers;
    }
}


