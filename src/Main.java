
/*
Write a program that prompts the user to enter a file name, then opens the file in text mode and reads names.
The file contains one name on each line. The program then compares each name with the name that is at
the end of the file in a symmetrical position.

For example if the file contains 10 names, the name #1 is compared with name #10, name #2 is compared with name #9, and so on.
If you find matches you should print the name and the line numbers where the match was found.

While entering the file name, the program should allow the user to type quit to exit the program.
If the file with a given name does not exist, then display a message and allow the user to re-enter the file name.
The file may contain up to 100 names.
You can use an array or  ArrayList object of your choosing, however you can only have one array or ArrayList.
Input validation:

a) If the file does not exist, then you should display a message "File 'somefile.txt' is not found."

and allow the user to re-enter the file name.

b) If the file is empty, then display a message "File 'somefile.txt' is empty." and exit the program.
Hints:
a) Perform file name input validation immediately after the user entry and use a while loop .
b) Use one integer variable to count names in the file and another one for counting matches.
d) You can use either a while loop or a for loop to find the matches.

Grading guide:

1. Functionality: Does the program work and perform basic functionality as per requirements?
(Need to be able to compile and run the program with some input and some output): 40%

2. Correctness: Does the program properly count matches? - 10%


3. Input Validation: Is the user input validated against requirements? - 10%

4. Output Formatting: Is the output formatted according to the requirements: 10%

5. Edge Cases: Does the program work without errors for ALL values of input
(missing file, empty file, odd or even number of lines, very large file, etc.) - 10%

 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the file name or type QUIT to exit:");
        String fileName = scan.nextLine();

        if (fileName.equalsIgnoreCase("quit")) {
            return;
        }


        File file = new File(fileName);
        if (!file.exists()) {
            System.out.printf("File '%s' is not found.\n", fileName);
            System.out.println("Please re-enter the file name or type QUIT to exit:");
            fileName = scan.nextLine();
            if (fileName.equalsIgnoreCase("quit")) {
                return;
            }
            file = new File(fileName);
        }
        ArrayList<String> names = new ArrayList<>();

        try (Scanner fileScan = new Scanner(file)) {
            while (fileScan.hasNextLine()) {
                String name = fileScan.nextLine();
                names.add(name);
            }
            if (names.isEmpty()) {
                System.out.printf("File '%s' is empty.\n", fileName);
                return;
            }

            int count = names.size();
            int foundMatches = 0;
            for (int i = 0; i < count / 2; i++) {
                if (names.get(i).equals(names.get(count - 1 - i))) {
                    System.out.printf("Match found: '%s' on lines %d and %d.\n",
                            names.get(i),
                            (i + 1),
                            (count - i));
                             foundMatches++;
                }
            }

            System.out.printf("Total of %d matches found.\n", foundMatches);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
