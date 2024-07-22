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
    private double priceRate;

    /**
     * Constructs a Day object representing a day in the month's calendar with
     * initial reservation status set to false.
     * 
     */
    public Day() {
        isBooked = false;
        isCheckIn = false;
        isCheckOut = false;
        priceRate = 1;
    }

    /**
     * Retrieves the booking status isBooked of the current instance of Day.
     * 
     * @return isBooked of the current instance
     */
    public boolean getIsBooked() {
        return this.isBooked;
    }

    /**
     * 
     * @param priceRate
     */
    public void setPriceRate(double priceRate) {
        this.priceRate = priceRate;
    }

    public double getPriceRate() {
        return this.priceRate;
    }

    /**
     * Sets the booking status isBooked of the current instance of Day.
     * 
     * @param isBooked
     */
    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    /**
     * Retrieves the check-in status isCheckIn of the current instance of Day.
     * 
     * @return isCheckIn of the current instance
     */
    public boolean getIsCheckIn() {
        return this.isCheckIn;
    }

    /**
     * Retrieves the check-out status isCheckOut of the current instance of Day.
     * 
     * @return isCheckOut of the current instance
     */

    public boolean getIsCheckOut() {
        return this.isCheckOut;
    }

    /**
     * Sets the check-in status isCheckIn of the current instance of Day.
     * 
     * @param isCheckIn
     */
    public void setIsCheckIn(boolean isCheckIn) {
        this.isCheckIn = isCheckIn;
    }

    /**
     * Sets the check-out status isCheckOut of the current instance of Day.
     * 
     * @param isCheckOut
     */
    public void setIsCheckOut(boolean isCheckOut) {
        this.isCheckOut = isCheckOut;
    }

    /**
     * Retrieves the Reservation object associated with the current instance of Day.
     * 
     * @return reservation of the current instance
     */
    public Reservation getReservation() {
        return this.reservation;
    }

    /**
     * Sets the Reservation object for the current instance of Day.
     * 
     * @param reservation
     */
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}
