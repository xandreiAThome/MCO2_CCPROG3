import Controller.HRSController;
import View.HRSView;

public class Main {
    public static void main(String[] args) {
        HRSView hrsView = new HRSView();
        HRSController hrsControl = new HRSController(hrsView);
    }
}