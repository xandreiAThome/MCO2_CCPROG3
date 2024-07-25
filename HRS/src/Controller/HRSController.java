package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import HotelClasses.Date;
import HotelClasses.Hotel;
import HotelClasses.Reservation;
import HotelClasses.RoomClasses.Room;
import Model.HRSModel;
import View.BookReservationView;
import View.CreateHotelView;
import View.HRSView;
import View.ManageHotelView;
import View.SelectDatePanel;
import View.SelectHotelPanel;
import View.ViewHotelView;

public class HRSController implements ActionListener {
    private HRSModel hrsModel;

    private HRSView hrsWindow;
    private JPanel createHotelView;
    private JPanel manageHotelView;
    private JPanel viewHotelView;
    private JPanel bookReservationView;

    public HRSController(HRSView hrsWindow, HRSModel hrsModel) {
        this.hrsModel = hrsModel;

        this.hrsWindow = hrsWindow;

        this.createHotelView = new CreateHotelView();
        this.viewHotelView = new ViewHotelView();
        this.manageHotelView = new ManageHotelView();
        this.bookReservationView = new BookReservationView();

        hrsWindow.setActionListener(this);
        ((CreateHotelView) createHotelView).setActionListener(this);
        ((ViewHotelView) viewHotelView).setActionListener(this);
        ((ManageHotelView) manageHotelView).setActionListener(this);
        ((BookReservationView) bookReservationView).setActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Home Panel Events /////////////////////////////
        if (e.getActionCommand().equals("Create Hotel")) {
            hrsWindow.setContentPane(this.createHotelView);
            hrsWindow.invalidate();
            hrsWindow.validate();
            ((CreateHotelView) createHotelView).resetTextFields();
        } else if (e.getActionCommand().equals("Manage Hotel")) {
            hrsWindow.setContentPane(this.manageHotelView);
            hrsWindow.invalidate();
            hrsWindow.validate();
        } else if (e.getActionCommand().equals("View Hotel")) {
            ((ViewHotelView) viewHotelView).resetEntries();
            ((ViewHotelView) viewHotelView).updateHotelDisplay(hrsModel.getHotelList());
            ((ViewHotelView) viewHotelView).dynamicSetActionListenerOfHotelButtons(this);
            hrsWindow.setContentPane(this.viewHotelView);
            hrsWindow.invalidate();
            hrsWindow.validate();
        } else if (e.getActionCommand().equals("Book Reservation")) {
            ((BookReservationView) bookReservationView).resetEntries();
            SelectHotelPanel selectHotelPanel = ((SelectHotelPanel) ((BookReservationView) bookReservationView)
                    .getSelectHotelPanel());
            selectHotelPanel.updateHotelDisplay(hrsModel.getHotelList());
            selectHotelPanel.dynamicSetActionListenerOfHotelButtons(this);
            hrsWindow.setContentPane(this.bookReservationView);
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
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        }
        // Book Reservation Events /////////////////////////
        // choose hotel
        else if (((JPanel) hrsWindow.getContentPane()) == bookReservationView) {
            if (((BookReservationView) bookReservationView).getChosenHotel() == null) {
                SelectHotelPanel selectHotelPanel = ((SelectHotelPanel) ((BookReservationView) bookReservationView)
                        .getSelectHotelPanel());
                for (JButton button : selectHotelPanel.getHotelListButtons()) {
                    if (e.getSource() == button) {
                        if (((BookReservationView) bookReservationView).getUserNameField().length() == 0) {
                            JOptionPane.showMessageDialog(this.hrsWindow, "Enter name",
                                    "Error", JOptionPane.WARNING_MESSAGE);
                        } else if (hrsModel.getHotelGivenName(e.getActionCommand())
                                .guestExists(((BookReservationView) bookReservationView).getUserNameField())) {
                            JOptionPane.showMessageDialog(this.hrsWindow, "Guest already has prior reservation",
                                    "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            ((BookReservationView) bookReservationView)
                                    .setChosenHotel(hrsModel.getHotelGivenName(e.getActionCommand()));
                            ((BookReservationView) bookReservationView).showChooseRoomPanel();
                            ((BookReservationView) bookReservationView)
                                    .updateRoomList(
                                            ((BookReservationView) bookReservationView).getChosenHotel().getRoomList());
                        }

                    }
                }
                // choose room
            } else if (((BookReservationView) bookReservationView).getChosenRoom() == null) {
                for (JButton button : ((BookReservationView) bookReservationView).getRoomList()) {
                    if (e.getSource() == button) {
                        ((BookReservationView) bookReservationView)
                                .setChosenRoom((((BookReservationView) bookReservationView)
                                        .getChosenHotel().getRoom(e.getActionCommand())));
                        ((BookReservationView) bookReservationView).showChooseDatePanel();
                    }
                }
            }
            // choose reservation
            else if (e.getActionCommand().equals("Book")) {
                SelectDatePanel selectDatePanel = ((SelectDatePanel) ((BookReservationView) bookReservationView)
                        .getSelectDatePanel());
                int checkInDay = selectDatePanel.getCheckInDay();
                int checkOutDay = selectDatePanel.getCheckOutDay();
                int checkInHour = selectDatePanel.getCheckInHour();
                int checkOutHour = selectDatePanel.getCheckOutHour();

                Room chosenRoom = ((BookReservationView) bookReservationView).getChosenRoom();

                Date checkIn = new Date(checkInDay, checkInHour);
                Date checkOut = new Date(checkOutDay, checkOutHour);

                if (checkOutDay <= checkInDay) {
                    JOptionPane.showMessageDialog(this.hrsWindow, "Check Out must not be before Check In",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (chosenRoom.getMonth().isConflict(checkInDay, checkInHour, checkOutDay, checkOutHour)) {
                    JOptionPane.showMessageDialog(this.hrsWindow, "Schedule is is conflict with prior Reservations",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    chosenRoom.addReservation(
                            new Reservation(((BookReservationView) bookReservationView).getUserNameField(),
                                    checkIn, checkOut, chosenRoom));
                    ((BookReservationView) bookReservationView).resetEntries();

                    hrsWindow.setContentPane(hrsWindow.getHomeScreenPanel());
                    hrsWindow.invalidate();
                    hrsWindow.validate();
                    JOptionPane.showMessageDialog(this.hrsWindow, "Succesfully booked reservation",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        }

        // View Hotel Events /////////////////////////
        // choose hotel
        else if (((JPanel) hrsWindow.getContentPane()) == viewHotelView) {
            if (((ViewHotelView) viewHotelView).getChosenHotel() == null) {
                for (JButton button : ((ViewHotelView) viewHotelView).getHotelListButton()) {
                    if (e.getSource() == button) {

                        ((ViewHotelView) viewHotelView)
                                .setChosenHotel(hrsModel.getHotelGivenName(e.getActionCommand()));
                        ((ViewHotelView) viewHotelView).dynamicSetActionListenerOfHotelButtons(this);
                        ((ViewHotelView) viewHotelView).showChooseOptionPanel();
                        ((ViewHotelView) viewHotelView).updateHotelInfoPanel();
                    }
                }
            } else {

            }
        }
        //////////////////////////////////////////////
    }

}
