package HotelClasses;

import java.util.ArrayList;
// Room stores its own reservations

public class Room {
    private String name;
    private double price = 1299;
    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
    private Month month;

    public Room(String name) {
        this.name = name;
        this.month = new Month(31);
    }

    public Month getMonth() {
        return this.month;
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
    public Reservation getReservation(String guest) {
        for (Reservation r : reservationList) {
            if (r.getGuestName() == guest) {
                return r;
            }
        }

        return null;
    }

    public ArrayList<Reservation> getAllReservations() {
        return this.reservationList;
    }

    public void removeReservation(String guest) {
        Reservation toBeRemoved = null;
        for (Reservation r : this.reservationList) {
            if (r.getGuestName().equals(guest)) {
                toBeRemoved = r;
                break;
            }
        }

        if (toBeRemoved != null) {
            this.month.resetAvailability(toBeRemoved);
        }

    }

    public void addReservation(Reservation reservation) {
        this.reservationList.add(reservation);
        this.month.setAvailability(reservation);
    }
}