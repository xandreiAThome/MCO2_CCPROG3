import java.util.ArrayList;
// Decide on the design of the reservation and where to store it

public class Hotel {
    private String name;
    private ArrayList<Room> roomList;

    public Hotel(String name, int roomAmount) {
        this.name = name;

        if (roomAmount < 1 || roomAmount > 50) {
            throw new IllegalArgumentException("Amount of Room inputted outside of allowed range!");
        }

        for (int i = 1; i <= roomAmount; i++) {
            roomList.add(new Room("HR" + i));
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}