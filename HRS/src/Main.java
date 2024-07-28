
import java.io.IOException;

import Controller.HRSController;
import Model.HRSModel;

import View.HRSView;

public class Main {
    public static void main(String[] args) {
        HRSView hrsView = new HRSView();
        HRSModel hrsModel = new HRSModel();
        try {
            HRSController hrsControl = new HRSController(hrsView, hrsModel);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}