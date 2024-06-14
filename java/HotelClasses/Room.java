package MCO1_CCPROG3.java.HotelClasses;

import java.util.ArrayList;
// Room stores its own reservations

public class Room {
    private String name;
    private double price = 1299;
    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // can guests only reserve at most once cuncurrently
    public Reservation getReservation(int day) {
        for (Reservation r : reservationList) {
            if (r.getCheckInDate().getDay() == day) {
                return r;
            }
        }

        return null;
    }

    public ArrayList<Reservation> getAllReservations() {
        return this.reservationList;
    }

    public void addReservation(Reservation reservation) {
        this.reservationList.add(reservation);
    }
}