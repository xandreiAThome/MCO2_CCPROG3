import HotelClasses.HRS;
import UserInterface.MainMenu;
import UserInterface.ManageHotel;
import UserInterface.SimulateBooking;
import UserInterface.ViewHotel;

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
                case 2:
                    System.out.print("\033\143");
                    ViewHotel.DisplayHotelInformation(hrs);

                    break;

                case 3:
                    System.out.print("\033\143");
                    ManageHotel.ManageHotelMain(hrs);
                    break;

                case 4:
                    System.out.print("\033\143");
                    SimulateBooking.BookReservation(hrs);
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
