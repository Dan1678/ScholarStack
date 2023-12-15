package MainUI;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Papers_list {
    ArrayList<String> Papers = new ArrayList<String>();
    Scanner scanner = new Scanner(System.in);
    public String paper;

    public void addPaper() {
        Papers.add(scanner.next());
        boolean validInput = false;
        String userInput;
        Pattern harvardPattern = Pattern.compile("^([A-Z][a-zA-Z]+), ([A-Z][a-zA-Z]+), ([0-9]{4})\\.$");

        while (!validInput) {
            System.out.println("Enter the Harvard reference (Author Last Name, Author First Name, Year): ");
            userInput = scanner.nextLine();

            Matcher matcher = harvardPattern.matcher(userInput);

            if (matcher.matches()) {
                Papers.add(userInput);
                validInput = true;
            } else {
                System.out.println("Please enter the reference in Harvard format (Last Name, First Name, Year).");
            }
        }
    }



    public ArrayList<String> getReferenceList() {
        return Papers ;
    }
}


//for (int i = 0; i < Papers.size(); i++) {
       // System.out.println(cars.get(i));


