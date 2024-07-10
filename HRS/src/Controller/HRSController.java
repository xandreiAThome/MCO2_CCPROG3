package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.BookReservationView;
import View.CreateHotelView;
import View.HRSView;
import View.ManageHotelView;
import View.ViewHotelView;

public class HRSController implements ActionListener {
    private HRSView hrsWindow;
    private CreateHotelController createHotelController;
    private ManageHotelController manageHotelController;
    private ViewHotelController viewHotelController;
    private BookReservationController bookReservationController;

    public HRSController(HRSView hrsWindow) {
        this.hrsWindow = hrsWindow;

        this.createHotelController = new CreateHotelController((CreateHotelView) hrsWindow.getCreateHotelPanel(),
                this.hrsWindow);
        this.manageHotelController = new ManageHotelController((ManageHotelView) hrsWindow.getManageHotelPanel(),
                hrsWindow);
        this.viewHotelController = new ViewHotelController((ViewHotelView) hrsWindow.getViewHotelPanel(),
                hrsWindow);
        this.bookReservationController = new BookReservationController(
                (BookReservationView) hrsWindow.getBookReservationPanel(),
                hrsWindow);

        hrsWindow.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Create Hotel")) {
            hrsWindow.setContentPane(hrsWindow.getCreateHotelPanel());
            hrsWindow.invalidate();
            hrsWindow.validate();
        } else if (e.getActionCommand().equals("Manage Hotel")) {
            hrsWindow.setContentPane(hrsWindow.getManageHotelPanel());
            hrsWindow.invalidate();
            hrsWindow.validate();
        } else if (e.getActionCommand().equals("View Hotel")) {
            hrsWindow.setContentPane(hrsWindow.getViewHotelPanel());
            hrsWindow.invalidate();
            hrsWindow.validate();
        } else if (e.getActionCommand().equals("Book Reservation")) {
            hrsWindow.setContentPane(hrsWindow.getBookReservationPanel());
            hrsWindow.invalidate();
            hrsWindow.validate();
        } else if (e.getActionCommand().equals("Home")) {
            hrsWindow.setContentPane(hrsWindow.getHomeScreenPanel());
            hrsWindow.invalidate();
            hrsWindow.validate();
        }
    }

}
