package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.HRSView;

import View.ViewHotelView;

public class ViewHotelController implements ActionListener {
    ViewHotelView viewHotelView;
    HRSView hrsWindow;

    public ViewHotelController(ViewHotelView viewHotelView, HRSView hrsWindow) {
        this.viewHotelView = viewHotelView;
        this.hrsWindow = hrsWindow;
        viewHotelView.setActionListener(this);
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
