package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.HRSModel;
import View.BookReservationView;
import View.CreateHotelView;
import View.HRSView;
import View.ManageHotelView;
import View.ViewHotelView;

public class HRSController implements ActionListener {
    private HRSModel hrsModel;

    private HRSView hrsWindow;
    private JPanel createHotelView;
    private JPanel manageHotelView;
    private JPanel viewHotelView;
    private JPanel bookReservationView;

    public HRSController(HRSView hrsWindow, HRSModel hrsModel) {
        this.hrsWindow = hrsWindow;
        this.createHotelView = hrsWindow.getCreateHotelPanel();
        this.manageHotelView = hrsWindow.getManageHotelPanel();
        this.viewHotelView = hrsWindow.getViewHotelPanel();
        this.bookReservationView = hrsWindow.getBookReservationPanel();

        this.hrsModel = hrsModel;

        // setting up action listeners for all panels
        hrsWindow.setActionListener(this);
        ((CreateHotelView) createHotelView).setActionListener(this);
        ((ManageHotelView) manageHotelView).setActionListener(this);
        ((ViewHotelView) viewHotelView).setActionListener(this);
        ((BookReservationView) bookReservationView).setActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Home Panel Events /////////////////////////////
        if (e.getActionCommand().equals("Create Hotel")) {
            hrsWindow.setContentPane(hrsWindow.getCreateHotelPanel());
            hrsWindow.invalidate();
            hrsWindow.validate();
            ((CreateHotelView) createHotelView).resetTextFields();
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
        }
        /////////////////////////////////////////////////////

        // Universal Event ////////////////////////////////
        else if (e.getActionCommand().equals("Home")) {
            hrsWindow.setContentPane(hrsWindow.getHomeScreenPanel());
            hrsWindow.invalidate();
            hrsWindow.validate();
        }
        //////////////////////////////////////////////////

        ///////// Create Hotel Events //////////////////
        else if (e.getActionCommand().equals("create")) {

            JTextField hotelNameField = ((CreateHotelView) this.createHotelView).getHotelNameField();
            JTextField roomQuantiField = ((CreateHotelView) this.createHotelView).getRoomQuantiField();

            int amount = 0;

            try {
                amount = Integer.valueOf(roomQuantiField.getText());
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }

            if (amount < 1 || amount > 50) {
                JOptionPane.showMessageDialog(this.hrsWindow, "Rooms amount must be between 1-50",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else if (hotelNameField.getText().length() == 0) {
                JOptionPane.showMessageDialog(this.hrsWindow, "Hotel Name must not be empty",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else if (hrsModel.isHotelDup(hotelNameField.getText())) {
                JOptionPane.showMessageDialog(this.hrsWindow, "Hotel Name already exists",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                boolean success = hrsModel.addHotel(hotelNameField.getText(), amount);

                if (success) {
                    hrsWindow.setContentPane(hrsWindow.getHomeScreenPanel());
                    hrsWindow.invalidate();
                    hrsWindow.validate();
                    JOptionPane.showMessageDialog(this.hrsWindow, "Succesfully added new Hotel",
                            "Success", JOptionPane.WARNING_MESSAGE);
                }

            }

        }
        ///////////////////////////////////////////

    }

}
