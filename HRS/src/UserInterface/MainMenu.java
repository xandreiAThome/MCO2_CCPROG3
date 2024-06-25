package UserInterface;

import java.io.File;
import java.util.Scanner;

/**
 * Main menu interface for the HRS
 */
public class MainMenu {

    /**
     * Displays the main menu options and prompts the user to choose an option.
     */
    public static int showMenu() {
        try {
            Scanner ascii = new Scanner(new File("textFiles/HRS.txt"));
            while (ascii.hasNextLine()) {
                System.out.println(ascii.nextLine());
            }

            ascii.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("1 - Create Hotel\n2 - View Hotel\n3 - Manage Hotel\n4 - Book Reservation\n5 - Exit\n\n");
        System.out.print("Choose Option: ");
        int option = Integer.valueOf(UserInput.getScanner().nextLine());

        while (option < 1 || option > 5) {
            System.out.println("\nInvalid option Choose again");
            System.out
                    .println("1 - Create Hotel\n2 - View Hotel\n3 - Manage Hotel\n4 - Book Reservation\n5 - Exit\n\n");
            System.out.print("Choose Option: ");
            option = Integer.valueOf(UserInput.getScanner().nextLine());
        }
        return option;
    }
}
