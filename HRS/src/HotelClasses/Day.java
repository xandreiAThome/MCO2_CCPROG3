package HotelClasses;

/**
 * Represents the days in the month calendar and contains the reservation status
 * for the day
 */
public class Day {
    private boolean isCheckIn;
    private boolean isCheckOut;
    private Reservation reservation, checkInReservation, checkOutReservation;
    // has seperate variable for check in and check out reservations in the day
    // object so as to support same day booking
    private double priceRate;

    /**
     * Constructs a Day object representing a day in the month's calendar with
     * initial reservation status set to false.
     * 
     */
    public Day() {
        isCheckIn = false;
        isCheckOut = false;
        priceRate = 1;
    }

    /**
     * Retrieves the booking status isBooked of the current instance of Day.
     * 
     * @param hour the hour of the booking status that will be checked, input -1 if
     *             you just want the booking status regardless of the hour
     * @return
     */
    public boolean getIsBooked(int hour) {
        if (hour == -1 && (reservation == null && checkInReservation == null && checkOutReservation == null)) {
            return false;
        } else if (checkInReservation != null && hour != -1) {
            if (hour < checkInReservation.getCheckInDate().getHour()) {
                return false;
            }
        } else if (checkOutReservation != null && hour != -1) {
            if (hour > checkOutReservation.getCheckOutDate().getHour()) {
                return false;
            }
        }
        return true;
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

    /**
     * Retrieves the check in Reservation object associated with the current
     * instance of Day.
     * 
     * @return reservation of the current instance
     */
    public Reservation getCheckInReservation() {
        return this.checkInReservation;
    }

    /**
     * Sets the check in Reservation object for the current instance of Day.
     * 
     * @param reservation
     */
    public void setCheckInReservation(Reservation reservation) {
        this.checkInReservation = reservation;
    }

    /**
     * Retrieves the check in Reservation object associated with the current
     * instance of Day.
     * 
     * @return reservation of the current instance
     */
    public Reservation getCheckOutReservation() {
        return this.checkOutReservation;
    }

    /**
     * Sets the check in Reservation object for the current instance of Day.
     * 
     * @param reservation
     */
    public void setCheckOutReservation(Reservation reservation) {
        this.checkOutReservation = reservation;
    }

}
