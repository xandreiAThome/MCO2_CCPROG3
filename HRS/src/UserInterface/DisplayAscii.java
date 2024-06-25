package UserInterface;

import java.io.File;
import java.util.Scanner;

/**
 * A Helper class for displaying ascii art given a text file of the ascii
 */
public class DisplayAscii {
    /**
     * Reads ASCII art from a text file specified by fileName and prints it to the console.
     * 
     * @param fileName
     */
    public static void display(String fileName) {
        try {
            Scanner ascii = new Scanner(new File(fileName));
            while (ascii.hasNextLine()) {
                System.out.println(ascii.nextLine());
            }

            ascii.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
