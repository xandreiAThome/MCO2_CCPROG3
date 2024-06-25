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
     * Constructs an instance of the HRS, initializing an empty list of hotels (hotelList).
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

        while (isHotelDup(name)) {
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
     * Checks if a hotel with the given name already exists in the HRS.
     * 
     * @param hotel
     * @param name
     * @return true if there is a duplicate, false otherwise
     */
    public boolean isHotelDup(String name) {
        for (Hotel h : this.hotelList) {
            if (name.equals(h.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieves the list of hotels (hotelList) in the HRS.
     * 
     * @return Hotel List of the current HRS instance
     */
    public ArrayList<Hotel> getHotelList() {
        return this.hotelList;
    }

    /**
     * Prints the names of all hotels in the HRS.
     */
    public void printHotels() {
        for (Hotel h : this.hotelList) {
            System.out.println(h.getName());
        }
    }

    /**
     * Changes the name of a specific hotel in the HRS.
     * 
     * @param index
     */

    public void changeHotelName(Hotel hotel) {

        /*
         * printHotels();
         * System.out.print("Enter the index of the hotel you want to change: ");
         * int index = scanner.nextInt();
         * scanner.nextLine();
         */

        System.out.print("Enter New Hotel Name: ");
        String name = UserInput.getScanner().nextLine();

        while (isHotelDup(name)) {
            System.out.print("Name already exists, Enter a new name: ");
            name = UserInput.getScanner().nextLine();
        }
        System.out.println("Confirm change(y/n): ");
        String confirm = UserInput.getScanner().nextLine();
        if (confirm.equals("Y") || confirm.equals("y")) {
            hotel.setName(name);
            hotel.changeAllRoomNames();
            System.out.println("Successfully changed name");
        } else {
            System.out.println("Canceled name change");
        }

    }

    /**
     *  Adds a specified number of rooms to a specific hotel in the HRS.
     * 
     * @param hotel
     */
    public void addRoomstoHotel(Hotel hotel) {
        /*
         * printHotels();
         * System.out.print("Enter the index of the hotel you want to modify: ");
         * int index = scanner.nextInt();
         * scanner.nextLine();
         */

        System.out.println("Current Amount of rooms " + hotel.getRoomAmt());
        System.out.print("Enter the amount of rooms to be added: ");
        int amount = Integer.valueOf(UserInput.getScanner().nextLine());
        int currentAmount = hotel.getRoomAmt();
        if (currentAmount > 50) {
            System.out.println("Number of rooms already at maximum");
            return;
        }
        while (amount < 1) {
            System.out.print("Invalid number\nTry again: ");
            amount = Integer.valueOf(UserInput.getScanner().nextLine());
        }

        while (currentAmount + amount > 50) {
            System.out.println("Number exceeds maximum rooms\nTry again: ");
            amount = Integer.valueOf(UserInput.getScanner().nextLine());
        }

        System.out.println("Confirm change(y/n): ");
        String confirm = UserInput.getScanner().nextLine();
        if (confirm.equals("Y") || confirm.equals("y")) {
            hotel.addRooms(amount);
            System.out.println("Successfully added rooms");
        } else {
            System.out.println("Canceled adding of rooms");
        }

        // scanner.close();
    }

    /**
     * Removes a room from a specific hotel in the HRS.
     * 
     * @param index
     */

    public void removeRoomsfromHotel(Hotel hotel) {

        /*
         * printHotels();
         * System.out.print("Enter the index of the hotel you want to modify: ");
         * int index = scanner.nextInt();
         * scanner.nextLine();
         */

        System.out.println("\nCurrent rooms: ");
        for (int i = 0; i < hotel.getRoomList().size(); i++) {
            System.out.print(hotel.getRoomList().get(i).getName() + "   ");
            if (i % 9 == 0 && i != 0) {
                System.out.println();
            }
        }
        System.out.print("\nEnter the name of the room to delete: ");
        String roomName = UserInput.getScanner().nextLine();
        Room room = hotel.getRoom(roomName);
        if (room == null) {
            System.out.println("Room doesn't exist");
            return;
        } else if (room.hasReservation()) {
            System.out.println(room.getName() + " is reserved, cannot be deleted.");
        } else if (hotel.getRoomAmt() == 1) {
            System.out.println("Cannot delete room, Hotel must have atleast 1 room left");
        } else {
            System.out.println("Confirm to delete room(y/n): ");
            String confirm = UserInput.getScanner().nextLine();
            if (confirm.equals("Y") || confirm.equals("y")) {

                hotel.removeRoom(roomName);
                System.out.println(room.getName() + " deleted successfully.");

            } else {
                System.out.println("Canceled room deletion");
            }

        }

        // scanner.close();
    }

    /**
     * Updates the base price of all rooms in a specific hotel in the HRS.
     *
     * @param index
     */

    public void updateBasePrice(Hotel hotel) {

        /*
         * printHotels();
         * System.out.print("Enter the index of the hotel you want to modify: ");
         * int index = scanner.nextInt();
         * scanner.nextLine();
         */

        System.out.println("Current Price " + hotel.getRoom(0).getPrice());

        boolean result = false;

        for (Room i : hotel.getRoomList()) {
            if (i.hasReservation()) {
                System.out.println("Hotel has active reservations, cannot update base price");
                result = true;
                break;
            }

        }

        if (result == false) {
            System.out.print("Enter the new base price: ");
            double newPrice = Double.valueOf(UserInput.getScanner().nextLine());

            while (!(newPrice >= 100.0)) {
                System.out.println("Price must be at least $100.00");
                System.out.print("Enter the new base price: ");
                newPrice = Double.valueOf(UserInput.getScanner().nextLine());
            }
            System.out.println("Confirm to change base price(y/n): ");
            String confirm = UserInput.getScanner().nextLine();
            if (confirm.equals("Y") || confirm.equals("y")) {
                for (Room j : hotel.getRoomList()) {
                    j.setPrice(newPrice);
                }
                System.out.println("Successfully changed base price");
            } else {
                System.out.println("Canceled price change");
            }

        }

        // scanner.close();
    }

    /**
     * Removes a reservation for a guest from a specific hotel in the HRS.
     * 
     * @param index
     */

    public void removeReservation(Hotel hotel) {

        /*
         * printHotels();
         * System.out.print("Enter the index of the hotel you want to modify: ");
         * int index = scanner.nextInt();
         * scanner.nextLine();
         */

        System.out.print("Enter the guest name: ");
        String guestName = UserInput.getScanner().nextLine();
        Reservation chosenReservation = null;
        for (Room room : hotel.getRoomList()) {
            chosenReservation = room.getReservation(guestName);
            if (chosenReservation != null) {
                break;
            }
        }

        if (chosenReservation != null) {
            System.out.println("Confirm to remove reservation(y/n): ");
            String confirm = UserInput.getScanner().nextLine();
            if (confirm.equals("Y") || confirm.equals("y")) {
                chosenReservation.getChosenRoom().removeReservation(guestName);
                System.out.println("Reservation removed successfully!");
            } else {
                System.out.println("Canceled price change");
            }
        } else {
            System.out.println("No reservation found for guest " + guestName);
        }

        // scanner.close();
    }

    /**
     * Removes a specific hotel from the HRS.
     * 
     * @param index
     */

    public void removeHotel(Hotel hotel) {

        /*
         * printHotels();
         * System.out.print("Enter the index of the hotel you want to remove: ");
         * int index = scanner.nextInt();
         * scanner.nextLine();
         */
        System.out.println("Confirm to delete hotel(y/n):");
        String confirm = UserInput.getScanner().nextLine();
        if (confirm.equals("Y") || confirm.equals("y")) {
            this.hotelList.remove(hotel);
            System.out.println("Successfully removed hotel");
        } else {
            System.out.println("Canceled hotel removal");
        }

        // scanner.close();
    }
}