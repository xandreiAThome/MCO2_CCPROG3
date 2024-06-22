package HotelClasses;

/**
 * Represents the days in the month calendar and contains the reservation status
 * for the day
 */
public class Day {
    private boolean isBooked;
    private boolean isCheckIn;
    private boolean isCheckOut;
    private Reservation reservation;

    /**
     * Constructs the Day object
     */
    public Day() {
        isBooked = false;
        isCheckIn = false;
        isCheckOut = false;
    }

    /**
     * 
     * @return isBooked of the current instance
     */
    public boolean getIsBooked() {
        return this.isBooked;
    }

    /**
     * Sets the isBooked variable
     * 
     * @param isBooked
     */
    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    /**
     * 
     * @return isCheckIn of the current instance
     */
    public boolean getIsCheckIn() {
        return this.isCheckIn;
    }

    /**
     * 
     * @return isCheckOut of the current instance
     */

    public boolean getIsCheckOut() {
        return this.isCheckOut;
    }

    /**
     * Sets the isCheckIn variable
     * 
     * @param isCheckIn
     */
    public void setIsCheckIn(boolean isCheckIn) {
        this.isCheckIn = isCheckIn;
    }

    /**
     * Sets the isCheckOut variable
     * 
     * @param isCheckOut
     */
    public void setIsCheckOut(boolean isCheckOut) {
        this.isCheckOut = isCheckOut;
    }

    /**
     * 
     * @return reservation of the current instance
     */
    public Reservation getReservation() {
        return this.reservation;
    }

    /**
     * Sets the reservation variable
     * 
     * @param reservation
     */
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}
