import UserInterface.HRS;
import UserInterface.MainMenu;

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
