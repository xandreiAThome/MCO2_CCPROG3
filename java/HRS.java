package MCO1_CCPROG3.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import MCO1_CCPROG3.java.HotelClasses.Hotel;

// decide on how to book reservation and what classes are involved in it
public class HRS {
    ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

    public static void main(String[] args) {

        try {
            Scanner input = new Scanner(new File("../textFiles/HRS.txt"));
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }

            input.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}