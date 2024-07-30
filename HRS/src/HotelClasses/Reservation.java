package HotelClasses;

import HotelClasses.RoomClasses.Room;

/**
 * Represents a reservation for a room
 */
public class Reservation {
    private String guest;
    private Date checkIn;
    private Date checkOut;
    private Room chosenRoom;
    private double totalPrice;
    private String discountCodeList[] = { "PAYDAY", "STAY4_GET1", "I_WORK_HERE" };

    /**
     * Initializes a Reservation object with the provided guest name, check-in date,
     * check-out date, and the room chosen for reservation.
     * 
     * @param guest
     * @param checkIn
     * @param checkOut
     * @param chosenRoom
     */
    public Reservation(String guest, Date checkIn, Date checkOut, Room chosenRoom) {
        this.guest = guest;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.chosenRoom = chosenRoom;
        this.totalPrice = chosenRoom.getPriceGivenDateRange(checkIn.getDay(), checkOut.getDay());
    }

    // ADD TO NEW UML
    /**
     * 
     * @return the number of days the reservation is booked for
     */
    public int reservationDuration() {
        return getCheckOutDate().getDay() - getCheckInDate().getDay() + 1;
    }

    /**
     * Retrieves the guest name associated with the reservation.
     * 
     * @return guest of the current Reservation instance
     */
    public String getGuestName() {
        return this.guest;
    }

    /**
     * Retrieves the check-in date of the reservation.
     * 
     * @return checkIn of the current Reservation instance
     */
    public Date getCheckInDate() {
        return this.checkIn;
    }

    /**
     * Retrieves the check-out date of the reservation.
     * 
     * @return checkOut of the current Reservation instance
     */
    public Date getCheckOutDate() {
        return this.checkOut;
    }

    /**
     * Retrieves the room that has been reserved.
     * 
     * @return chosenRoom of the current Reservation instance
     */
    public Room getChosenRoom() {
        return this.chosenRoom;
    }

    /**
     * Retrieves the total price of the reservation.
     * 
     * @return the totalPrice of the reservation of the current Reservation instance
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    public String[] getDiscountCodeList() {
        return this.discountCodeList;
    }

    public boolean applyDiscount(String discountCode) {
        boolean applied = false;
        switch (discountCode) {
            case "I_WORK_HERE":
                this.totalPrice = this.totalPrice - (this.totalPrice * 0.1);
                applied = true;
                break;
            case "STAY4_GET1":
                if (reservationDuration() >= 5) {
                    this.totalPrice -= chosenRoom.getBasePrice()
                            * chosenRoom.getMonth().getDay(checkIn.getDay()).getPriceRate();
                    applied = true;
                }
                break;
            case "PAYDAY":
                if (this.chosenRoom.getMonth().isPayDay(checkIn.getDay(), checkOut.getDay())) {
                    this.totalPrice = totalPrice - (totalPrice * 0.07);
                    applied = true;
                }
                break;

            default:
                break;
        }

        return applied;
    }
}