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
