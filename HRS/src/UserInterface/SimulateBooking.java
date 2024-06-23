package UserInterface;

import java.util.ArrayList;

import HotelClasses.Hotel;
import HotelClasses.Reservation;
import HotelClasses.Room;
import HotelClasses.Date;

/**
 * Simulate booking interface for the HRS
 */
public class SimulateBooking {

    /**
     * Prompts user for hotel, check in and check out time
     * 
     * @param hotelList
     */
    public static void BookReservation(ArrayList<Hotel> hotelList) {
        DisplayAscii.display("textFiles/SimulateBooking.txt");

        System.out.print("Enter Name: ");
        String userName = UserInput.getScanner().nextLine();

        System.out.println("Choose a hotel");
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println(i + " - " + hotelList.get(i).getName());
        }

        System.out.print("\nEnter chosen Hotel: ");
        int hotelIndex = Integer.valueOf(UserInput.getScanner().nextLine());
        while (hotelIndex < 0 || hotelIndex > hotelList.size() - 1) {
            System.out.println("Invalid option");
            System.out.print("Enter chosen Hotel: ");
            hotelIndex = Integer.valueOf(UserInput.getScanner().nextLine());
        }

        Hotel chosenHotel = hotelList.get(hotelIndex);

        int checkInDay, checkOutDay, checkInHour, checkOutHour;
        Date checkIn, checkOut;
        Reservation reservation = null;
        Room chosenRoom = null;
        while (reservation == null) {
            System.out.print("Enter Check-In day(1-30): ");
            checkInDay = Integer.valueOf(UserInput.getScanner().nextLine());
            while (checkInDay < 1 || checkInDay > 30) {
                System.out.println("Invalid Check-in date");
                System.out.print("Enter Check-In day(1-30): ");
                checkInDay = Integer.valueOf(UserInput.getScanner().nextLine());
            }
            System.out.print("Enter Check-in Hour(00H - 23H): ");
            checkInHour = Integer.valueOf(UserInput.getScanner().nextLine());
            while (checkInHour < 0 || checkInHour > 23) {
                System.out.println("Invalid Hour format");
                System.out.print("Enter Check-in Hour(00H - 23H): ");
                checkInHour = Integer.valueOf(UserInput.getScanner().nextLine());
            }
            checkIn = new Date(checkInDay, checkInHour);

            System.out.print("Enter Check-out day(2-31): ");
            checkOutDay = Integer.valueOf(UserInput.getScanner().nextLine());
            while (checkOutDay < 2 || checkOutDay > 31 || checkOutDay <= checkInDay) {
                System.out.println("Invalid Check-out date");
                System.out.print("Enter Check-out day: ");
                checkOutDay = Integer.valueOf(UserInput.getScanner().nextLine());
            }
            System.out.print("Enter Check-out Hour(00H - 23H): ");
            checkOutHour = Integer.valueOf(UserInput.getScanner().nextLine());
            while (checkOutHour < 0 || checkOutHour > 23) {
                System.out.println("Invalid Hour format");
                System.out.print("Enter Check-in Hour(00H - 23H): ");
                checkOutHour = Integer.valueOf(UserInput.getScanner().nextLine());
            }
            checkOut = new Date(checkOutDay, checkOutHour);

            for (Room r : chosenHotel.getRoomList()) {
                Reservation temp = new Reservation(userName, checkIn, checkOut, r);
                if (!r.getMonth().isConflict(temp)) {
                    reservation = temp;
                    chosenRoom = r;
                    break;
                }
            }
            System.out.println("Schedule is is conflict with prior Reservations");
        }
        chosenRoom.addReservation(reservation);
    }
}