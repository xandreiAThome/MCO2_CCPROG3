package Model;

import java.util.ArrayList;

import HotelClasses.Hotel;

public class HRSModel {
    private ArrayList<Hotel> hotelList;

    public HRSModel() {
        this.hotelList = new ArrayList<Hotel>();
    }

    public boolean addHotel(String hotelName, int roomAmount) {
        try {
            this.hotelList.add(new Hotel(hotelName, roomAmount));
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
}
