
import Controller.HRSController;
import Model.HRSModel;
import View.HRSView;

public class Main {
    public static void main(String[] args) {
        HRSView hrsView = new HRSView();
        HRSModel hrsModel = new HRSModel();
        HRSController hrsControl = new HRSController(hrsView, hrsModel);

    }
}