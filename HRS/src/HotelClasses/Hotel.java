package HotelClasses;

import java.util.ArrayList;

import HotelClasses.RoomClasses.DeluxeRoom;
import HotelClasses.RoomClasses.ExecutiveRoom;
import HotelClasses.RoomClasses.Room;

/**
 * Represents a hotel in the Hotel Reservation System
 */
public class Hotel {
    private String name;
    private ArrayList<Room> roomList;
    private int totalStandardRoom = 0;
    private int totalDeluxeRoom = 0;
    private int totalExecutiveRoom = 0;
    private double basePrice = 1299;

    /**
     * Constructs a Hotel object with a specified name and a number of rooms.
     * 
     * @param name
     * @param roomAmount
     */
    public Hotel(String name, int roomAmount) {
        this.name = name;
        this.roomList = new ArrayList<Room>();

        for (int i = 1; i <= roomAmount; i++) {
            this.roomList.add(new Room(name + "00" + i, basePrice));
        }

        this.totalStandardRoom = roomAmount;
    }

    /**
     * Constructs a Hotel object with a specified name, total number of rooms, and
     * number of deluxe and executive rooms.
     * The remaining rooms in the total amount are just normal rooms
     * 
     * @param name
     * @param roomAmount
     * @param deluxeAmount
     * @param executiveAmount
     */
    public Hotel(String name, int roomAmount, int deluxeAmount, int executiveAmount) {
        this.name = name;
        this.roomList = new ArrayList<Room>();

        for (int i = 1; i <= roomAmount - deluxeAmount - executiveAmount; i++) {
            this.roomList.add(new Room(name + "00" + i, basePrice));
        }

        for (int i = 1; i <= deluxeAmount; i++) {
            this.roomList.add(new DeluxeRoom(name + "55" + i, basePrice));
        }

        for (int i = 1; i <= executiveAmount; i++) {
            this.roomList.add(new ExecutiveRoom(name + "77" + i, basePrice));
        }

        this.totalStandardRoom = roomAmount - deluxeAmount - executiveAmount;
        this.totalDeluxeRoom = deluxeAmount;
        this.totalExecutiveRoom = executiveAmount;
    }

    /**
     * Retrieves the name of the current instance of Hotel.
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the current instance of Hotel.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the list of rooms roomList in the current instance of Hotel.
     * 
     * @return ArrayList of roomList
     */
    public ArrayList<Room> getRoomList() {
        return this.roomList;
    }

    /**
     * Calculates the total estimated revenue for the month across all reservations
     * in the hotel.
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
     * Checks if a guest with the specified name already has a reservation in the
     * hotel.
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
     * Retrieves the number of rooms in the current instance of Hotel.
     * 
     * @return size
     */

    public int getRoomAmt() {
        return this.roomList.size();
    }

    /**
     * Adds a specified number of rooms (newAmt) to the hotel.
     * 
     * @param newAmt
     */
    public void addRoom(Room room) {
        this.roomList.add(room);
    }

    /**
     * Updates the names of all rooms in the hotel based on the hotel's name and
     * room index.
     * 
     */
    public void changeAllRoomNames() {
        for (int i = 0; i < this.roomList.size(); i++) {
            this.roomList.get(i).setName(this.name + "00" + (i + 1));
        }
    }

    /**
     * Removes a room from the hotel based on its index in roomList.
     * 
     * @param index
     */

    public void removeRoom(int index) {
        roomList.remove(index);
    }

    /**
     * Removes a room from the hotel based on its name.
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
     * Remove room from hotel
     * 
     * @param room
     */
    public void removeRoom(Room room) {
        this.roomList.remove(room);
    }

    /**
     * Retrieves a Room object from roomList based on its index.
     * 
     * @param index
     * @return Room given index
     */
    public Room getRoom(int index) {

        return this.roomList.get(index);
    }

    /**
     * Retrieves a Room object from roomList based on its name.
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

    /**
     * 
     * @return true - if there are reservations in any rooms in the hotel
     */
    public boolean hasReservations() {
        for (Room room : this.roomList) {
            if (room.hasReservation()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Sets the price rate for a specific day of the month
     * 
     * @param day
     * @param newRate
     */
    public void setPriceRate(int day, double newRate) {
        for (Room room : this.roomList) {
            room.getMonth().getDay(day).setPriceRate(newRate);
        }
    }

    /**
     * Sets the base price of the rooms in the hotel
     * All room types base their price on the base price
     * With deluxe rooms having 20% higher prices and executive rooms having 35%
     * higher price than the base price
     * 
     * @param basePrice
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
        for (Room room : roomList) {
            room.setPrice(basePrice);
        }
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    /**
     * 
     * @param day
     * @return true - if the specified day is booked;
     */
    public boolean hasReservationDay(int day) {
        for (Room room : this.roomList) {
            if (room.getMonth().getDay(day).getIsBooked(-1)) {
                return true;
            }
        }

        return false;

    }

    public int getTotalDeluxeRoom() {
        return totalDeluxeRoom;
    }

    public int getTotalExecutiveRoom() {
        return totalExecutiveRoom;
    }

    public int getTotalStandardRoom() {
        return totalStandardRoom;
    }

    public void setTotalDeluxeRoom(int totalDeluxeRoom) {
        this.totalDeluxeRoom = totalDeluxeRoom;
    }

    public void setTotalExecutiveRoom(int totalExecutiveRoom) {
        this.totalExecutiveRoom = totalExecutiveRoom;
    }

    public void setTotalStandardRoom(int totalStandardRoom) {
        this.totalStandardRoom = totalStandardRoom;
    }

    /**
     * Removes a reservation from the hotel given the guest name
     * 
     * @param name
     */
    public void removeReservationHotel(String name) {
        for (Room room : this.roomList) {
            room.removeReservation(name);
        }
    }

}