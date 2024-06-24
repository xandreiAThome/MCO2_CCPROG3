package HotelClasses;

import java.util.ArrayList;
// Room stores its own reservations

/**
 * Represents the room in the hotel
 */
public class Room {
    private String name;
    private double price = 1299;
    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
    private Month month;

    /**
     * constructs the Room object
     * 
     * @param name
     */
    public Room(String name) {
        this.name = name;
        this.month = new Month(31);
    }

    /**
     * 
     * @return month of the current Room instance
     */
    public Month getMonth() {
        return this.month;
    }

    /**
     * 
     * @return name of the current Room instance
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the current Room instance
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return the Room price of the current Room instance
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the Room price of the current Room instance
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 
     * @param guest
     * @return the Reservation of the guest name
     */
    public Reservation getReservation(String guest) {
        for (Reservation r : reservationList) {
            if (r.getGuestName().equals(guest)) {
                return r;
            }
        }

        return null;
    }

    /**
     * 
     * @return the List of all reservations in the current Room instance
     */
    public ArrayList<Reservation> getAllReservations() {
        return this.reservationList;
    }

    /**
     * Removes the reservation of the guest
     * 
     * @param guest
     */
    public void removeReservation(String guest) {
        Reservation toBeRemoved = null;
        for (Reservation r : this.reservationList) {
            if (r.getGuestName().equals(guest)) {
                toBeRemoved = r;
                break;
            }
        }

        if (toBeRemoved != null) {
            this.month.resetAvailability(toBeRemoved);
        }

    }

    /**
     * Adds the reservation and sets the availability of the room for the month
     * 
     * @param reservation
     */
    public void addReservation(Reservation reservation) {
        this.reservationList.add(reservation);
        this.month.setAvailability(reservation);
    }

    /**
     * Displays the availability of the room for the month
     */
    public void displayMonthAvailability() {
        System.out.println("Room " + this.name + " availability for the Month");
        this.month.displayMonth();
    }

    public boolean hasReservation(){

        boolean result = false;

        if(this.reservationList.size()-1 > 0){
            result = true;
        } 

        return result;
    }
}