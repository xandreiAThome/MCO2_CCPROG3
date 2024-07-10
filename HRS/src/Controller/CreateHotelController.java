package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.CreateHotelView;
import View.HRSView;

public class CreateHotelController implements ActionListener {
    CreateHotelView createHotelView;
    HRSView hrsWindow;

    public CreateHotelController(CreateHotelView createHotelView, HRSView hrsWindow) {
        this.createHotelView = createHotelView;
        this.hrsWindow = hrsWindow;
        createHotelView.setActionListener(this);
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
