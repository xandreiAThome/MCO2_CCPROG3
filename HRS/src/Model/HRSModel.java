
package Model;

import java.util.ArrayList;

import HotelClasses.Hotel;

/**
 * The HRSModel class represents the model component of the Hotel Reservation
 * System.
 * It manages the list of hotels and provides methods to add, remove, and
 * retrieve hotels.
 */
public class HRSModel {
    private ArrayList<Hotel> hotelList;

    /**
     * Constructs a new HRSModel object with an empty hotel list.
     */
    public HRSModel() {
        this.hotelList = new ArrayList<Hotel>();
    }

    /**
     * Returns the list of hotels.
     * 
     * @return the list of hotels
     */
    public ArrayList<Hotel> getHotelList() {
        return this.hotelList;
    }

    /**
     * Adds a new hotel to the list with the given name and room amount.
     * 
     * @param hotelName  the name of the hotel
     * @param roomAmount the number of rooms in the hotel
     * @return true if the hotel was successfully added, false otherwise
     */
    public boolean addHotel(String hotelName, int roomAmount) {
        try {
            this.hotelList.add(new Hotel(hotelName, roomAmount));
            return true;
        } catch (Exception e) {

        }

        return false;
    }

    /**
     * Adds a new hotel to the list with the given name, room amount, deluxe amount,
     * and executive amount.
     * 
     * @param hotelName       the name of the hotel
     * @param roomAmount      the number of rooms in the hotel
     * @param deluxeAmount    the number of deluxe rooms in the hotel
     * @param executiveAmount the number of executive rooms in the hotel
     * @return true if the hotel was successfully added, false otherwise
     */
    public boolean addHotel(String hotelName, int roomAmount, int deluxeAmount, int executiveAmount) {
        try {
            this.hotelList.add(new Hotel(hotelName, roomAmount, deluxeAmount, executiveAmount));
            return true;
        } catch (Exception e) {

        }

        return false;
    }

    /**
     * Checks if a hotel with the given name already exists in the list.
     * 
     * @param name the name of the hotel to check
     * @return true if a hotel with the given name exists, false otherwise
     */
    public boolean isHotelDup(String name) {
        for (Hotel h : this.hotelList) {
            if (name.equals(h.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieves a hotel from the list based on the given name.
     * 
     * @param name the name of the hotel to retrieve
     * @return the hotel with the given name, or null if no such hotel exists
     */
    public Hotel getHotelGivenName(String name) {
        for (Hotel h : this.hotelList) {
            if (name.equals(h.getName())) {
                return h;
            }
        }

        return null;
    }

    /**
     * Removes a hotel from the list.
     * 
     * @param hotel the hotel to remove
     * @return true if the hotel was successfully removed, false otherwise
     */
    public boolean removeHotel(Hotel hotel) {
        return this.hotelList.remove(hotel);
    }

}
