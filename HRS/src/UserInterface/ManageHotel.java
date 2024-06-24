package UserInterface;

import HotelClasses.Date;
import HotelClasses.HRS;
import HotelClasses.Hotel;
import HotelClasses.Reservation;
import HotelClasses.Room;

public class ManageHotel{

    public static void ManageHotelMain (HRS hrs){
        DisplayAscii.display("textFiles/ManageHotel.txt");

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

        System.out.println("What would you like to do: ");
        System.out.println("1 - Change Hotel Name");
        System.out.println("2 - Add Rooms");
        System.out.println("3 - Remove Rooms");
        System.out.println("4 - Update Base Price");
        System.out.println("5 - Remove Reservation");
        System.out.println("6 - Remove Hotel");

        System.out.print("Enter Option: ");
        int option = Integer.valueOf(UserInput.getScanner().nextLine());
        while (option < 1 || option > 6) {
            System.out.println("Invalid Option");
            System.out.print("Enter Option: ");
            option = Integer.valueOf(UserInput.getScanner().nextLine());
        }

        switch (option) {
            case 1:
                hrs.changeHotelName(hotelIndex);
                break;
            case 2:
                hrs.addRoomstoHotel(hotelIndex);
                break;
            case 3:
                hrs.removeRoomsfromHotel(hotelIndex);
                break;
            case 4:
                hrs.updateBasePrice(hotelIndex);
                break;
            case 5:
                hrs.removeReservation(hotelIndex);
                break;
            case 6:
                hrs.removeHotel(hotelIndex);
                break;
            default:
                break;
        }
    }
    
}
