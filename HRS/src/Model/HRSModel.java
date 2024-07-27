package Model;

import java.util.ArrayList;

import HotelClasses.Hotel;

public class HRSModel {
    private ArrayList<Hotel> hotelList;

    public HRSModel() {
        this.hotelList = new ArrayList<Hotel>();
    }

    public ArrayList<Hotel> getHotelList() {
        return this.hotelList;
    }

    public boolean addHotel(String hotelName, int roomAmount) {
        try {
            this.hotelList.add(new Hotel(hotelName, roomAmount));
            return true;
        } catch (Exception e) {

        }

        return false;
    }

    public boolean addHotel(String hotelName, int roomAmount, int deluxeAmount, int executiveAmount) {
        try {
            this.hotelList.add(new Hotel(hotelName, roomAmount, deluxeAmount, executiveAmount));
            return true;
        } catch (Exception e) {

        }

        return false;
    }

    public boolean isHotelDup(String name) {
        for (Hotel h : this.hotelList) {
            if (name.equals(h.getName())) {
                return true;
            }
        }

        return false;
    }

    public Hotel getHotelGivenName(String name) {
        for (Hotel h : this.hotelList) {
            if (name.equals(h.getName())) {
                return h;
            }
        }

        return null;
    }

    public boolean removeHotel (Hotel hotel){
        return this.hotelList.remove(hotel);
    }

}
