package UserInterface;

import java.util.Scanner;

/**
 * Helper function for getting user input
 */
public class UserInput {
    private static final Scanner input = new Scanner(System.in);

    /**
     * 
     * @return scanner if system.in
     */
    public static Scanner getScanner() {
        return input;
    }

    /**
     * Closes the scanner of system.in
     */
    public static void closeScanner() {
        input.close();
    }

}
