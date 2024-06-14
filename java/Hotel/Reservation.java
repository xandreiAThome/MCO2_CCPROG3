
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

        this.totalPrice = (checkOut.getDay() - checkIn.getDay()) * chosenRoom.getPrice();
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

    public double getTotalPrice() {
        return this.totalPrice;
    }
}