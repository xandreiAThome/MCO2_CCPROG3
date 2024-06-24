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
     * Checks if the given name of the hotel alreadys exists
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
     * 
     * @return Hotel List of the current HRS instance
     */
    public ArrayList<Hotel> getHotelList() {
        return this.hotelList;
    }

    public void printHotels() {
        for (Hotel h : this.hotelList) {
            System.out.println(h.getName());
        }
    }

    /*
     * Change a hotel's name
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

    /*
     * Add a certain amount of rooms to a hotel
     * 
     * @param index
     * 
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

        int finalAmount = currentAmount + amount;

        while (finalAmount < 1 || finalAmount > 50) {
            System.out.print("Number of rooms must be between 1 and 50\nTry again: ");
            amount = Integer.valueOf(UserInput.getScanner().nextLine());
            finalAmount = currentAmount + amount;
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

    /*
     * Removes rooms from a hotel
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

        System.out.println("Current Amount of rooms " + hotel.getRoomAmt());
        System.out.println("Enter the range of rooms you want to delete: ");
        System.out.print("From: ");
        int from = Integer.valueOf(UserInput.getScanner().nextLine());
        System.out.print("To: ");
        int To = Integer.valueOf(UserInput.getScanner().nextLine());

        if (from < 1 || To > hotel.getRoomList().size() - 1) {
            System.out.println("Invalid range");
            return;
        } else {
            System.out.println("Confirm to delete rooms(y/n): ");
            String confirm = UserInput.getScanner().nextLine();
            if (confirm.equals("Y") || confirm.equals("y")) {
                for (int i = from; i < To; i++) {
                    Room room = hotel.getRoomList().get(i - 1);
                    if (room.hasReservation()) {
                        System.out.println("Room " + i + " is reserved, cannot be deleted.");
                    } else {
                        hotel.removeRoom(i - 1);
                        System.out.println("Room " + i + " deleted successfully.");
                    }
                }
            } else {
                System.out.println("Canceled room deletion");
            }

        }

        // scanner.close();
    }

    /*
     * Updates the price of the Hotel
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

    /*
     * Removes a reservation from a hotel
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

    /*
     * Removes a hotel from the list
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