package UserInterface;

import HotelClasses.Date;
import HotelClasses.HRS;
import HotelClasses.Hotel;
import HotelClasses.Reservation;
import HotelClasses.Room;

/**
 * View Hotel Interface for HRS
 */
public class ViewHotel {

    /**
     * Display information about a selected hotel within HRS.
     * 
     * @param hrs
     */
    public static void DisplayHotelInformation(HRS hrs) {
        DisplayAscii.display("textFiles/ViewHotel.txt");

        if (hrs.getHotelList().isEmpty()) {
            System.out.println("No current hotel in HRS");
            return;
        }

        System.out.println("Choose a hotel");
        for (int i = 0; i < hrs.getHotelList().size(); i++) {
            System.out.println(i + " - " + hrs.getHotelList().get(i).getName());
        }

        System.out.print("\nEnter chosen Hotel: ");
        int hotelIndex = Integer.valueOf(UserInput.getScanner().nextLine());
        while (hotelIndex < 0 || hotelIndex > hrs.getHotelList().size() - 1) {
            System.out.println("Invalid option");
            System.out.print("Enter chosen Hotel: ");
            hotelIndex = Integer.valueOf(UserInput.getScanner().nextLine());
        }

        Hotel chosenHotel = hrs.getHotelList().get(hotelIndex);
        System.out.print("\033\143");
        DisplayAscii.display("textFiles/HotelInformation.txt");
        System.out.println(
                "Hotel name: " + chosenHotel.getName() + "\tNumber of Rooms: " + chosenHotel.getRoomList().size());
        System.out.println("Estimate earnings for the month: $" + chosenHotel.earningForMonth() + "\n");

        lowLevelInfo(chosenHotel);
    }

    /**
     * Provide secondary options after selecting a hotel (Check Room Availability, Check Room Information, Check Reservation Information).
     * 
     * @param hotel
     */

    private static void lowLevelInfo(Hotel hotel) {
        System.out.println("1 - Check Room Availability");
        System.out.println("2 - Check Room Information");
        System.out.println("3 - Check Reservation Information");

        System.out.print("Enter Option: ");
        int option = Integer.valueOf(UserInput.getScanner().nextLine());
        while (option < 1 || option > 3) {
            System.out.println("Invalid Option");
            System.out.print("Enter Option: ");
            option = Integer.valueOf(UserInput.getScanner().nextLine());
        }

        switch (option) {
            case 1:
                displayRoomAvailability(hotel);
                break;
            case 2:
                getRoomToDisplay(hotel);
                break;
            case 3:
                displayReservationInfo(hotel);
                break;

            default:
                break;
        }
    }

    /**
     * Display detailed information about reservations within a selected hotel.
     * 
     * @param hotel
     */
    private static void displayReservationInfo(Hotel hotel) {
        boolean noCurrReservation = true;
        for (Room room : hotel.getRoomList()) {
            if (!room.getAllReservations().isEmpty()) {
                noCurrReservation = false;
                break;
            }
        }
        if (noCurrReservation) {
            System.out.println("No current reservation in the hotel");
            return;
        }

        System.out.print("\nEnter name: ");
        String name = UserInput.getScanner().nextLine();

        Reservation chosenReservation = null;

        for (Room room : hotel.getRoomList()) {
            chosenReservation = room.getReservation(name);
            if (chosenReservation != null) {
                break;
            }
        }
        if (chosenReservation == null) {
            System.out.println("Guest has no prior reservation");
            System.out.print("Press Enter to return to Main Menu: ");
            UserInput.getScanner().nextLine();
        } else {
            Room chosenRoom = chosenReservation.getChosenRoom();
            System.out.println("\nGuest name: " + chosenReservation.getGuestName());
            System.out.print("Check in date: ");
            chosenReservation.getCheckInDate().displayDate();
            System.out.print("Check out date: ");
            chosenReservation.getCheckOutDate().displayDate();
            System.out.println("Total price: " + chosenReservation.getTotalPrice());
            displayRoomInformation(chosenRoom);
        }
    }

    /**
     * Display detailed information about a selected room within a hotel.
     * 
     * @param hotel
     */
    private static void getRoomToDisplay(Hotel hotel) {
        System.out.println("\nCurrent rooms: ");
        for (int i = 0; i < hotel.getRoomList().size(); i++) {
            System.out.print(hotel.getRoomList().get(i).getName() + "   ");
            if (i % 9 == 0 && i != 0) {
                System.out.println();
            }
        }

        System.out.print("\nEnter Room name: ");
        String roomName = UserInput.getScanner().nextLine();
        Room chosenRoom = hotel.getRoom(roomName);
        while (chosenRoom == null) {
            System.out.println("Invalid Option");
            System.out.print("Enter Room name: ");
            roomName = UserInput.getScanner().nextLine();
            chosenRoom = hotel.getRoom(roomName);
        }

        System.out.println();
        displayRoomInformation(chosenRoom);
    }

    /**
     * Display detailed information about a selected room within a hotel.
     * 
     * @param chosenRoom
     */
    private static void displayRoomInformation(Room chosenRoom) {
        System.out.println("Room name: " + chosenRoom.getName() + "\tPrice per night: " + chosenRoom.getPrice());
        System.out.println("Room availability (- booked, + available)");
        chosenRoom.displayMonthAvailability();
        System.out.print("Press Enter to return to Main Menu: ");
        UserInput.getScanner().nextLine();
    }

    /**
     * Display the availability of rooms within a selected hotel for a specified date range.
     * 
     * @param hotel
     */
    private static void displayRoomAvailability(Hotel hotel) {
        System.out.print("\nEnter Check-In day(1-30): ");
        int checkInDay = Integer.valueOf(UserInput.getScanner().nextLine());
        while (checkInDay < 1 || checkInDay > 30) {
            System.out.println("Invalid Check-in date");
            System.out.print("Enter Check-In day(1-30): ");
            checkInDay = Integer.valueOf(UserInput.getScanner().nextLine());
        }
        System.out.print("Enter Check-in Hour(00H - 23H): ");
        int checkInHour = Integer.valueOf(UserInput.getScanner().nextLine());
        while (checkInHour < 0 || checkInHour > 23) {
            System.out.println("Invalid Hour format");
            System.out.print("Enter Check-in Hour(00H - 23H): ");
            checkInHour = Integer.valueOf(UserInput.getScanner().nextLine());
        }
        Date checkIn = new Date(checkInDay, checkInHour);

        System.out.print("Enter Check-out day(2-31): ");
        int checkOutDay = Integer.valueOf(UserInput.getScanner().nextLine());
        while (checkOutDay < 2 || checkOutDay > 31 || checkOutDay <= checkInDay) {
            System.out.println("Invalid Check-out date");
            System.out.print("Enter Check-out day: ");
            checkOutDay = Integer.valueOf(UserInput.getScanner().nextLine());
        }
        System.out.print("Enter Check-out Hour(00H - 23H): ");
        int checkOutHour = Integer.valueOf(UserInput.getScanner().nextLine());
        while (checkOutHour < 0 || checkOutHour > 23) {
            System.out.println("Invalid Hour format");
            System.out.print("Enter Check-in Hour(00H - 23H): ");
            checkOutHour = Integer.valueOf(UserInput.getScanner().nextLine());
        }
        Date checkOut = new Date(checkOutDay, checkOutHour);

        int available = 0, booked = 0;

        for (Room room : hotel.getRoomList()) {
            if (room.getMonth().isConflict(new Reservation(null, checkIn, checkOut, room))) {
                booked++;
            } else {
                available++;
            }
        }

        System.out.println("\nAvailable Rooms: " + available + "\tBooked Rooms: " + booked);
        System.out.print("Press Enter to return to Main Menu: ");
        UserInput.getScanner().nextLine();
    }
}
