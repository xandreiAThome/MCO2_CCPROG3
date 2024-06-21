package UserInterface;

import java.io.File;
import java.util.Scanner;

public class DisplayAscii {
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
