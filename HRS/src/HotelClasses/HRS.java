package HotelClasses;

import java.util.ArrayList;

import UserInterface.DisplayAscii;
import UserInterface.UserInput;

/**
 * Represents the Hotel Reservation System
 */
public class HRS {
    private ArrayList<Hotel> hotelList;

    /**
     * Constructs the HRS
     */
    public HRS() {
        this.hotelList = new ArrayList<Hotel>();
    }

    /**
     * Adds a new hotel to the HRS
     */
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

    /**
     * Checks if the given name of the hotel alreadys exists
     * 
     * @param hotel
     * @param name
     * @return true if there is a duplicate, false otherwise
     */
    public boolean isHotelDup(ArrayList<Hotel> hotel, String name) {
        for (Hotel h : hotel) {
            if (name.equals(h)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 
     * @return Hotel List of the current HRS instance
     */
    public ArrayList<Hotel> getHotelList() {
        return this.hotelList;
    }
}