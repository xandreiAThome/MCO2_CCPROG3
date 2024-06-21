package UserInterface;

import java.util.ArrayList;

import HotelClasses.Hotel;
import HotelClasses.Reservation;
import HotelClasses.Room;
import HotelClasses.Date;
// Create an boolean array of days in the month for if that day is booked or not

public class SimulateBooking {

    public static void BookReservation(ArrayList<Hotel> hotelList) {
        DisplayAscii.display("textFiles/SimulateBooking.txt");

        System.out.print("Enter Name: ");
        String userName = UserInput.getScanner().nextLine();

        System.out.println("Choose a hotel");
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println(i + " - " + hotelList.get(i).getName());
        }
        System.out.print("\nEnter chosen Hotel: ");
        Hotel chosenHotel = hotelList.get(Integer.valueOf(UserInput.getScanner().nextLine()));

        int checkInDay, checkOutDay, checkInHour, checkOutHour;
        Date checkIn, checkOut;
        boolean noConflict = false;
        while (!noConflict) {
            noConflict = true;
            System.out.print("Enter Check-In day(1-30): ");
            checkInDay = Integer.valueOf(UserInput.getScanner().nextLine());
            while (checkInDay < 1 || checkInDay > 30) {
                System.out.println("Invalid Check-in date");
                System.out.print("Enter Check-In day(1-30): ");
                checkInDay = Integer.valueOf(UserInput.getScanner().nextLine());
            }
            System.out.print("Enter Check-in Hour(24H): ");
            checkInHour = Integer.valueOf(UserInput.getScanner().nextLine());
            checkIn = new Date(checkInDay, checkInHour);

            System.out.print("Enter Check-out day(2-31): ");
            checkOutDay = Integer.valueOf(UserInput.getScanner().nextLine());
            while (checkOutDay < 2 || checkOutDay > 31 || checkOutDay < checkInDay) {
                System.out.println("Invalid Check-out date");
                System.out.print("Enter Check-out day: ");
                checkOutDay = Integer.valueOf(UserInput.getScanner().nextLine());
            }
            System.out.print("Enter Check-out Hour(24H): ");
            checkOutHour = Integer.valueOf(UserInput.getScanner().nextLine());
            checkOut = new Date(checkOutDay, checkOutHour);

            boolean isPrinted = false;
            for (Room room : chosenHotel.getRoomList()) {
                for (Reservation reserve : room.getAllReservations()) {
                    if (!reserve.getCheckOuDate().isDateEarlier(checkIn)) {
                        noConflict = false;
                        if (!isPrinted) {
                            System.out.println("Conflicting schedule with prior reservation");
                        }
                        isPrinted = true;
                    }
                    if (reserve.getCheckInDate().isDateEarlier(checkOut)) {
                        noConflict = false;
                        if (!isPrinted) {
                            System.out.println("Conflicting schedule with prior reservation");
                        }
                        isPrinted = true;

                    }

                    if (noConflict) {
                        break;
                    }
                }
                if (noConflict) {
                    room.addReservation(new Reservation(userName, checkIn, checkOut, room));
                    break;
                }
            }
        }

    }
}
