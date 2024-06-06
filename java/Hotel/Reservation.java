
public class Reservation {
    private String guest;
    private Date checkIn;
    private Date checkOut;
    private Room chosenRoom;
    private double totalPrice;

    public Reservation(String guest, Date checkIn, Date checkOut, Room chosenRoom) {
        this.guest = guest;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.chosenRoom = chosenRoom;

    }

    public String getGuestName() {
        return this.guest;
    }

    public Date getCheckInDate() {
        return this.checkIn;
    }

    public Date getCheckOuDate() {
        return this.checkOut;
    }

    public Room getChosenRoom() {
        return this.chosenRoom;
    }
}