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

    /**
     * 
     * @param name
     * @return true if guest already has reservation in the hotel instance, false
     *         otherwise
     */
    public boolean guestExists(String name) {
        for (Room room : this.roomList) {
            for (Reservation r : room.getAllReservations()) {
                if (r.getGuestName().equals(name)) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getRoomAmt(){
        int size = this.roomList.size();

        return size;
    }

    public void addRooms(int newAmt){

        int currentAmt = roomList.size();
        for (int i = currentAmt + 1; i <= currentAmt + newAmt; i++){
            this.roomList.add(new Room("HR" + i));
        }

    }

    public void removeRoom(int index){
        roomList.remove(index);
    }
}