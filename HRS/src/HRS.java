
import java.util.ArrayList;

import HotelClasses.Hotel;
import UserInterface.DisplayAscii;
import UserInterface.UserInput;

// decide on how to book reservation and what classes are involved in it
public class HRS {
    private ArrayList<Hotel> hotelList;

    public HRS() {
        this.hotelList = new ArrayList<Hotel>();
    }

    public void AddHotel() {
        DisplayAscii.display("textFiles/CreateHotel.txt");

        // get user input
        System.out.print("Enter Hotel Name: ");
        String name = UserInput.getScanner().nextLine();

        while (isHotelDup(this.hotelList, name)) {
            System.out.print("Name already exists, Enter a new name: ");
            name = UserInput.getScanner().nextLine();
        }

        System.out.print("Enter number of rooms: ");
        int amount = Integer.valueOf(UserInput.getScanner().nextLine());

        while (amount < 1 || amount > 50) {
            System.out.print("Number of rooms must be between 1 and 50\nTry again: ");
            amount = Integer.valueOf(UserInput.getScanner().nextLine());
        }

        this.hotelList.add(new Hotel(name, amount));
    }

    public boolean isHotelDup(ArrayList<Hotel> hotel, String name) {
        for (Hotel h : hotel) {
            if (name.equals(h)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Hotel> getHotelList() {
        return this.hotelList;
    }
}