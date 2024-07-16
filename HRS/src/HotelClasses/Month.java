package HotelClasses;

/**
 * Represents a month calendar for the availability of a room for the month
 */
public class Month {
    private Day month[];
    private double[] priceRateList;

    /**
     * Constructs a Month object for a given number of days.
     * 
     * @param numOfDays
     */
    public Month(int numOfDays) {
        month = new Day[numOfDays];
        for (int i = 0; i < numOfDays; i++) {
            this.month[i] = new Day();
        }

        priceRateList = new double[numOfDays];

        for (int i = 0; i < numOfDays; i++) {
            this.priceRateList[i] = 1.0;
        }
    }

    /**
     * Retrieves the array of Day objects representing each day in the month.
     * 
     * @return month of the current instance
     */
    public Day[] getMonth() {
        return this.month;
    }

    /**
     * Sets the availability of days in the month for a specific reservation.
     * 
     * @param reservation
     */
    public void setAvailability(Reservation reservation) {
        int checkInDay = reservation.getCheckInDate().getDay();
        int checkOutDay = reservation.getCheckOutDate().getDay();
        for (int i = checkInDay - 1; i < checkOutDay; i++) {
            if (i == checkInDay - 1) {
                this.month[i].setIsBooked(true);
                this.month[i].setIsCheckIn(true);
                this.month[i].setReservation(reservation);
            } else if (i == checkOutDay - 1) {
                this.month[i].setIsBooked(true);
                this.month[i].setIsCheckOut(true);
                this.month[i].setReservation(reservation);
            } else {
                this.month[i].setIsBooked(true);
                this.month[i].setReservation(reservation);
            }
        }
    }

    /**
     * Resets the availability of days in the month for a specific reservation.
     * 
     * @param reservation
     */
    public void resetAvailability(Reservation reservation) {
        int checkInDay = reservation.getCheckInDate().getDay();
        int checkOutDay = reservation.getCheckOutDate().getDay();

        for (int i = checkInDay - 1; i < checkOutDay; i++) {
            if (i == checkInDay - 1) {
                this.month[i].setIsBooked(false);
                this.month[i].setIsCheckIn(false);
                this.month[i].setReservation(null);
            } else if (i == checkOutDay - 1) {
                this.month[i].setIsBooked(false);
                this.month[i].setIsCheckOut(false);
                this.month[i].setReservation(null);
            } else {
                this.month[i].setIsBooked(false);
                this.month[i].setReservation(null);
            }
        }
    }

    /**
     * Displays the month and the availability of each of the days, - for booked, +
     * for available
     */
    public void displayMonth() {
        for (int i = 0; i < month.length; i++) {
            if (i % 10 == 0 && i != 0) {
                System.out.print("|\n");
            }
            if (month[i].getIsBooked()) {
                if (i < 9) {
                    System.out.print("| " + (i + 1) + " - ");
                } else {
                    System.out.print("|" + (i + 1) + " - ");
                }

            } else {
                if (i < 9) {
                    System.out.print("| " + (i + 1) + " + ");
                } else {
                    System.out.print("|" + (i + 1) + " + ");
                }
            }

        }
        System.out.print("|\n");
    }

    /**
     * Checks if a given reservation conflicts with existing reservations in the
     * month.
     * 
     * @param reservation the reservation to be checked
     * @return true if there is a conflict, false otherwise
     */
    public boolean isConflict(Reservation reservation) {
        boolean isConflict = false;
        int checkInDay = reservation.getCheckInDate().getDay() - 1;// checkin day - 1 so that it follows the array of
                                                                   // indeces starting at 0
        int checkOutDay = reservation.getCheckOutDate().getDay() - 1;
        for (int i = checkInDay; i <= checkOutDay && !isConflict; i++) {
            if (this.month[i].getIsCheckIn() && i == checkOutDay) {
                if (this.month[i].getReservation().getCheckInDate().getHour() <= reservation.getCheckOutDate()
                        .getHour()) {
                    isConflict = true;
                }
            } else if (this.month[i].getIsCheckOut() && i == checkInDay) {
                if (this.month[i].getReservation().getCheckOutDate().getHour() >= reservation.getCheckInDate()
                        .getHour()) {
                    isConflict = true;
                }

            } else if (this.month[i].getIsBooked()) {
                isConflict = true;
            }
        }

        return isConflict;
    }

    public void setPriceRate(int day, double rate) {
        if (day >= 1 && day <= 31) {
            this.priceRateList[day] = rate;
        } else {
            throw new IllegalArgumentException("Day must be between 1 and 31");
        }
    }

    public double getPriceRate(int day) {
        if (day >= 1 && day <= 31) {
            return this.priceRateList[day - 1];
        } else {
            throw new IllegalArgumentException("Day must be between 1 and 31");
        }
    }

}
