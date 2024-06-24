package HotelClasses;

import java.util.ArrayList;
import java.util.Scanner;

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

     public void printHotels(){
        for(Hotel h : this.hotelList){
            System.out.println(h.getName());
        }
    }

    /*
     * Change a hotel's name
     * 
     * @param index
     */

    public void changeHotelName(int index){
        
        Scanner scanner = new Scanner(System.in);
    
        /*
        printHotels();
        System.out.print("Enter the index of the hotel you want to change: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 
        */

        System.out.print("Enter New Hotel Name: ");
        String name = scanner.nextLine();
    
        while (isHotelDup(name)) {
            System.out.print("Name already exists, Enter a new name: ");
            name = scanner.nextLine();
        }
    
        if(index >= 0 && index < hotelList.size()-1) {
            hotelList.get(index).setName(name); 
        } else {
            System.out.println("Invalid index. Hotel not found.");
        }

        //scanner.close();
    }

    /*
     * Add a certain amount of rooms to a hotel
     * 
     * @param index
     * 
     */
    public void addRoomstoHotel(int index){
        
        Scanner scanner = new Scanner(System.in);
        /*
        printHotels();
        System.out.print("Enter the index of the hotel you want to modify: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 
        */

        System.out.println("Current Amount of rooms "+ hotelList.get(index).getRoomAmt());
        System.out.print("Enter the amount of rooms to be added: ");
        int amount = Integer.valueOf(UserInput.getScanner().nextLine());

        int currentAmount = hotelList.get(index).getRoomAmt();

        int finalAmount = currentAmount + amount;

        while (finalAmount < 1 || finalAmount > 50) {
            System.out.print("Number of rooms must be between 1 and 50\nTry again: ");
            amount = Integer.valueOf(UserInput.getScanner().nextLine());
            finalAmount = currentAmount + amount;
        }

        hotelList.get(index).addRooms(amount);

        //scanner.close();
    }

    /*
     * Removes rooms from a hotel
     * 
     * @param index
     */

    public void removeRoomsfromHotel(int index){
        
        Scanner scanner = new Scanner(System.in);
        /*
        printHotels();
        System.out.print("Enter the index of the hotel you want to modify: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 
        */

        System.out.println("Current Amount of rooms "+ hotelList.get(index).getRoomAmt());
        System.out.println("Enter the range of rooms you want to delete: ");
        System.out.print("From: ");
        int from = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("To: ");
        int To = scanner.nextInt();
        scanner.nextLine(); 

        if (from < 1 || To > hotelList.get(index).getRoomList().size()-1){
            System.out.println("Invalid range");
            scanner.close();
            return;
        } else {
            for (int i = from; i < To; i++){
                Room room = hotelList.get(index).getRoomList().get(i-1);
                if (room.hasReservation()){
                    System.out.println("Room " + i + " is reserved, cannot be deleted.");
                } else {
                    hotelList.get(index).removeRoom(i-1);
                    System.out.println("Room " + i + " deleted successfully.");
                }
            }
        }
       
        //scanner.close();
    }

    /*
     * Updates the price of the Hotel
     *
     * @param index
     */

    public void updateBasePrice(int index){
        Scanner scanner = new Scanner(System.in);

        /* 
        printHotels();
        System.out.print("Enter the index of the hotel you want to modify: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 
        */

        Room temp =hotelList.get(index).checkRoom(0);

        System.out.println("Current Price "+ temp.getPrice());

        boolean result = false;

        for (Room i : hotelList.get(index).getRoomList()){
            if(i.hasReservation() == true){
                System.out.println("Hotel has active reservations, cannot update base price");
                result = true;
            }
            break;
        }

        if (result == false){
            System.out.print("Enter the new base price: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();

            while (!(newPrice >= 100.0)){
                System.out.println("Price must be at least $100.00");
                System.out.print("Enter the new base price: ");
                newPrice = scanner.nextDouble();
                scanner.nextLine();
            }

            for(Room j: hotelList.get(index).getRoomList()){
                j.setPrice(newPrice);
            }
        }

        //scanner.close();
    }

    /*
     * Removes a reservation from a hotel
     * 
     * @param index
     */

    public void removeReservation(int index){
        Scanner scanner = new Scanner(System.in);

        /* 
        printHotels();
        System.out.print("Enter the index of the hotel you want to modify: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 
        */

        System.out.print("Enter the guest name: ");
        String guestName = scanner.nextLine();

        for(Room i : hotelList.get(index).getRoomList()){
            if(i.getReservation(guestName) != null){
                i.removeReservation(guestName);
                System.out.println("Reservation removed successfully!");
                scanner.close();
                return;
            }
        }

        System.out.println("No reservation found for guest " + guestName);

        //scanner.close();
    }

    /*
     * Removes a hotel from the list
     * 
     * @param index
     */

    public void removeHotel(int index){
        Scanner scanner = new Scanner(System.in);

        /* 
        printHotels();
        System.out.print("Enter the index of the hotel you want to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 
        */

        if(index > hotelList.size()-1){
            System.out.println("Invalid index");
        } else {
            hotelList.remove(index);
        }

        //scanner.close();
    }
}