package HotelClasses;

import java.util.ArrayList;

public class Hotel {
    private String name;
    private ArrayList<Room> roomList;

    public Hotel(String name, int roomAmount) {
        this.name = name;
        this.roomList = new ArrayList<Room>();

        for (int i = 1; i <= roomAmount; i++) {
            this.roomList.add(new Room("HR" + i));
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}