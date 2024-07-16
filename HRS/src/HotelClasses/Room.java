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
     * Initializes a Room object with a given name and creates a Month object to
     * manage availability for 31 days (assuming a month with fixed days).
     * 
     * @param name
     */
    public Room(String name) {
        this.name = name;
        this.month = new Month(31);
    }

    /**
     * Retrieves the Month object associated with this room.
     * 
     * @return month of the current Room instance
     */
    public Month getMonth() {
        return this.month;
    }

    /**
     * Retrieves the name of the room.
     * 
     * @return name of the current Room instance
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the room.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the final price of the room with 
     * 
     * @param day
     * @return finalPrice - computed by multiplying the base price from the price rate.
     */
    public double getPrice(int day) {
        double rate = this.month.getPriceRate(day);
        double finalPrice = this.price * rate;
        return finalPrice;
    }

    public double getBasePrice(){
        return this.price;
    }

    /**
     * Sets the base price per day of the room.
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves a specific reservation made by a guest.
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
     * Retrieves all reservations made for the room.
     * 
     * @return the List of all reservations in the current Room instance
     */
    public ArrayList<Reservation> getAllReservations() {
        return this.reservationList;
    }

    /**
     * RRemoves a reservation made by a specific guest and updates availability.
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
            reservationList.remove(toBeRemoved);
            this.month.resetAvailability(toBeRemoved);
        }

    }

    /**
     * Adds a new reservation to the room and updates availability.
     * 
     * @param reservation
     */
    public void addReservation(Reservation reservation) {
        this.reservationList.add(reservation);
        this.month.setAvailability(reservation);
    }

    /**
     * Displays the availability of the room for the entire month using the Month
     * object's displayMonth() method.
     */
    public void displayMonthAvailability() {
        System.out.println("Room " + this.name + " availability for the Month");
        this.month.displayMonth();
    }

    /**
     * Checks if the room has any reservations.
     * 
     * @return true if there are reservations (reservationList is not empty), false
     *         otherwise.
     */
    public boolean hasReservation() {

        boolean result = false;

        if (this.reservationList.size() > 0) {
            result = true;
        }

        return result;
    }
}