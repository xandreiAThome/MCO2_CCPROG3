package UserInterface;

import HotelClasses.Hotel;
import HotelClasses.Reservation;
import HotelClasses.Room;
import HotelClasses.Date;
import HotelClasses.HRS;

/**
 * Simulate booking interface for the HRS
 */
public class SimulateBooking {

    /**
     * Prompts user for hotel, check in and check out time
     * 
     * @param hrs
     */
    public static void BookReservation(HRS hrs) {
        DisplayAscii.display("textFiles/SimulateBooking.txt");

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
        boolean hotelFull = true;
        for (Room room : chosenHotel.getRoomList()) {
            for (int i = 0; i < room.getMonth().getMonth().length; i++) {
                if (!room.getMonth().getMonth()[i].getIsBooked()) {
                    hotelFull = false;
                    break;
                }
            }
        }
        if (hotelFull) {
            System.out.println("Hotel is full");
            return;
        }

        System.out.print("Enter Name: ");
        String userName = UserInput.getScanner().nextLine();
        boolean guestExists = chosenHotel.guestExists(userName);
        while (guestExists) {
            System.out.print("Guest already has prior reservation in this hotel\nEnter Name: ");
            userName = UserInput.getScanner().nextLine();
            guestExists = chosenHotel.guestExists(userName);
        }

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
        System.out.println("Succesfully booked reservation");
    }
}
