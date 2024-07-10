package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.HRSView;
import View.ManageHotelView;

public class ManageHotelController implements ActionListener {
    ManageHotelView manageHotelView;
    HRSView hrsWindow;

    public ManageHotelController(ManageHotelView manageHotelView, HRSView hrsWindow) {
        this.manageHotelView = manageHotelView;
        this.hrsWindow = hrsWindow;
        manageHotelView.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Home")) {
            hrsWindow.setContentPane(hrsWindow.getHomeScreenPanel());
            hrsWindow.invalidate();
            hrsWindow.validate();
        }
    }
}
