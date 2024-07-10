package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.BookReservationView;
import View.HRSView;

public class BookReservationController implements ActionListener {
    BookReservationView bookReservationView;
    HRSView hrsWindow;

    public BookReservationController(BookReservationView bookReservationView, HRSView hrsWindow) {
        this.bookReservationView = bookReservationView;
        this.hrsWindow = hrsWindow;
        bookReservationView.setActionListener(this);
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
