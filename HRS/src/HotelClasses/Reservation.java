package HotelClasses;

/**
 * Represents a reservation for a room
 */
public class Reservation {
    private String guest;
    private Date checkIn;
    private Date checkOut;
    private Room chosenRoom;
    private double totalPrice;

    /**
     * Constructs the Reservation object
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

        this.totalPrice = (checkOut.getDay() - checkIn.getDay()) * chosenRoom.getPrice();
    }

    /**
     * 
     * @return guest of the current Reservation instance
     */
    public String getGuestName() {
        return this.guest;
    }

    /**
     * 
     * @return checkIn of the current Reservation instance
     */
    public Date getCheckInDate() {
        return this.checkIn;
    }

    /**
     * 
     * @return checkOut of the current Reservation instance
     */
    public Date getCheckOutDate() {
        return this.checkOut;
    }

    /**
     * 
     * @return chosenRoom of the current Reservation instance
     */
    public Room getChosenRoom() {
        return this.chosenRoom;
    }

    /**
     * 
     * @return the totalPrice of the reservation of the current Reservation instance
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }
}