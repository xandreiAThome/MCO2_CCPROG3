package UserInterface;

import java.util.Scanner;

public class UserInput {
    private static final Scanner input = new Scanner(System.in);

    public static Scanner getScanner() {
        return input;
    }

    public static void closeScanner() {
        input.close();
    }

}
