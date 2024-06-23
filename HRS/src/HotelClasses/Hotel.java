package HotelClasses;

import java.util.ArrayList;

/**
 * Represents a hotel in the Hotel Reservation System
 */
public class Hotel {
    private String name;
    private ArrayList<Room> roomList;

    /**
     * Constructs the Hotel Object
     * 
     * @param name
     * @param roomAmount
     */
    public Hotel(String name, int roomAmount) {
        this.name = name;
        this.roomList = new ArrayList<Room>();

        for (int i = 1; i <= roomAmount; i++) {
            this.roomList.add(new Room(name + "00" + i));
        }
    }

    /**
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the current instance
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return ArrayList of roomList
     */
    public ArrayList<Room> getRoomList() {
        return this.roomList;
    }

    /**
     * 
     * @return The total estimated revenue for the month of the hotel across all
     *         reservations in the hotel
     */
    public double earningForMonth() {
        double total = 0;
        for (Room room : this.roomList) {
            for (Reservation r : room.getAllReservations()) {
                total += r.getTotalPrice();
            }
        }

        return total;
    }

}