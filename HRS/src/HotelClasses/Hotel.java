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

    /**
     * 
     * @return size
     */

    public int getRoomAmt() {
        int size = this.roomList.size();

        return size;
    }

    /*
     * @param newAmt
     */
    public void addRooms(int newAmt) {

        int currentAmt = this.roomList.size() - 1;
        for (int i = currentAmt + 1; i <= currentAmt + newAmt; i++) {
            this.roomList.add(new Room(name + "00" + i));
        }
    }

    public void changeAllRoomNames() {
        for (int i = 1; i <= this.roomList.size(); i++) {
            this.roomList.get(i).setName(this.name + "00" + i);
        }
    }

    /**
     * remove room given room index
     * 
     * @param index
     */

    public void removeRoom(int index) {
        roomList.remove(index);
    }

    /**
     * remove room given room name
     * 
     * @param name
     */
    public void removeRoom(String name) {
        for (int i = 0; i < this.roomList.size(); i++) {
            Room room = this.roomList.get(i);
            if (room.getName().equals(name)) {
                this.roomList.remove(room);
            }
        }
    }

    /**
     * 
     * @param index
     * @return Room given index
     */
    public Room getRoom(int index) {

        return this.roomList.get(index);
    }

    /**
     * 
     * @param name
     * @return Room given name
     */
    public Room getRoom(String name) {
        for (Room room : this.roomList) {
            if (room.getName().equals(name)) {
                return room;
            }
        }

        return null;
    }
}