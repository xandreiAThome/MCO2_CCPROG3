package UserInterface;

import java.util.ArrayList;

import HotelClasses.Hotel;

public class SimulateBooking {

    public static void BookReservation(ArrayList<Hotel> hotelList) {
        DisplayAscii.display("textFiles/SimulateBooking.txt");
        System.out.println("Choose a hotel");
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println(i + " - " + hotelList.get(i).getName());
        }
        System.out.print("\nEnter chosen Hotel: ");
        Hotel chosenHotel = hotelList.get(Integer.valueOf(UserInput.getScanner().nextLine()));

    }
}
