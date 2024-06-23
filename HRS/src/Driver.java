import HotelClasses.HRS;
import UserInterface.MainMenu;
import UserInterface.SimulateBooking;

public class Driver {
    public static void main(String[] args) {
        HRS hrs = new HRS();
        boolean stop = false;

        while (!stop) {
            switch (MainMenu.showMenu()) {
                case 1:
                    System.out.print("\033\143");
                    hrs.AddHotel();
                    System.out.print("\033\143");
                    System.out.println("Succesfully added Hotel");
                    break;

                case 4:
                    System.out.print("\033\143");
                    SimulateBooking.BookReservation(hrs.getHotelList());
                    System.out.print("\033\143");
                    System.out.println("Succesfully booked reservation");
                    hrs.getHotelList().get(0).getRoomList().get(0).displayMonthAvailability();
                    break;

                case 5:
                    stop = true;
                    System.out.print("\033\143");
                    System.out.println("Exited Hotel Reservation System");
                    break;

                default:
                    break;
            }
        }

    }

}
