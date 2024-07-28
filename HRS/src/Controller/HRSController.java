package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.*;

import CustomJPanels.DisplayPrices;
import CustomJPanels.DisplayRoomPanel;
import CustomJPanels.JPanelWithBackground;
import CustomJPanels.SelectDatePanel;
import CustomJPanels.SelectDatePanelWithDiscount;
import CustomJPanels.SelectHotelPanel;
import CustomJPanels.SelectRoomPanel;
import HotelClasses.Date;
import HotelClasses.Reservation;
import HotelClasses.RoomClasses.DeluxeRoom;
import HotelClasses.RoomClasses.ExecutiveRoom;
import HotelClasses.RoomClasses.Room;
import Model.HRSModel;
import View.BookReservationView;
import View.CreateHotelView;
import View.HRSView;
import View.ManageHotelView;
import View.ViewHotelView;

public class HRSController implements ActionListener {
    private HRSModel hrsModel;

    private HRSView hrsWindow;
    private JPanelWithBackground createHotelView;
    private JPanel manageHotelView;
    private JPanelWithBackground viewHotelView;
    private JPanelWithBackground bookReservationView;

    public HRSController(HRSView hrsWindow, HRSModel hrsModel) throws IOException {
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
            ManageHotelView manageHotelTemp = ((ManageHotelView) manageHotelView);
            manageHotelTemp.resetEntries();
            SelectHotelPanel selectHotelTemp = ((SelectHotelPanel) manageHotelTemp.getSelectHotelPanel());
            selectHotelTemp.updateHotelDisplay(hrsModel.getHotelList());
            selectHotelTemp.dynamicSetActionListenerOfHotelButtons(this);
            hrsWindow.setContentPane(this.manageHotelView);
            hrsWindow.invalidate();
            hrsWindow.validate();
        } else if (e.getActionCommand().equals("View Hotel")) {
            ViewHotelView viewHotelTemp = ((ViewHotelView) viewHotelView);
            viewHotelTemp.resetEntries();
            SelectHotelPanel selectHotelTemp = ((SelectHotelPanel) viewHotelTemp.getSelectHotelPanel());
            selectHotelTemp.updateHotelDisplay(hrsModel.getHotelList());
            selectHotelTemp.dynamicSetActionListenerOfHotelButtons(this);
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
            JTextField deluxeQuantiField = ((CreateHotelView) this.createHotelView).getDeluxeQuantiField();
            JTextField executiveQuantiField = ((CreateHotelView) this.createHotelView).getExecutiveQuantiField();

            int amount = 0;
            int deluxeAmount = 0;
            int executiveAmount = 0;

            try {
                amount = Integer.valueOf(roomQuantiField.getText());
                deluxeAmount = Integer.valueOf(deluxeQuantiField.getText());
                executiveAmount = Integer.valueOf(executiveQuantiField.getText());
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
            } else if (deluxeAmount + executiveAmount > amount) {
                JOptionPane.showMessageDialog(this.hrsWindow,
                        "Number of Deluxe and Executive Rooms exceed Rooms created",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                boolean success = hrsModel.addHotel(hotelNameField.getText(), amount, deluxeAmount, executiveAmount);

                if (success) {
                    hrsWindow.setContentPane(hrsWindow.getHomeScreenPanel());
                    hrsWindow.invalidate();
                    hrsWindow.validate();
                    JOptionPane.showMessageDialog(this.hrsWindow, "Succesfully added new Hotel",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    hrsWindow.setContentPane(hrsWindow.getHomeScreenPanel());
                    hrsWindow.invalidate();
                    hrsWindow.validate();
                    JOptionPane.showMessageDialog(this.hrsWindow, "Failed to created Hotel",
                            "Error", JOptionPane.WARNING_MESSAGE);
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
                            BookReservationView bookViewTemp = ((BookReservationView) bookReservationView);
                            bookViewTemp.setChosenHotel(hrsModel.getHotelGivenName(e.getActionCommand()));
                            bookViewTemp.showChooseRoomPanel();

                            SelectRoomPanel roomPanelTemp = ((SelectRoomPanel) bookViewTemp.getSelectRoomPanel());
                            roomPanelTemp.updateRoomListButtons((bookViewTemp.getChosenHotel().getRoomList()));
                            roomPanelTemp.dynamicSetActionListenerOfHotelButtons(this);
                            // Update action listener for rooms here because hotel is chosen at this moment
                        }

                    }
                }
                // choose room
            } else if (((BookReservationView) bookReservationView).getChosenRoom() == null) {
                for (JButton button : ((SelectRoomPanel) ((BookReservationView) bookReservationView)
                        .getSelectRoomPanel())
                        .getRoomListButtons()) {
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
                SelectDatePanelWithDiscount selectDatePanel = ((SelectDatePanelWithDiscount) ((BookReservationView) bookReservationView)
                        .getSelectDatePanel());
                int checkInDay = selectDatePanel.getCheckInDay();
                int checkOutDay = selectDatePanel.getCheckOutDay();
                int checkInHour = selectDatePanel.getCheckInHour();
                int checkOutHour = selectDatePanel.getCheckOutHour();
                String discountCode = selectDatePanel.getDiscountTextField().getText();

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
                    Reservation reservation = new Reservation(
                            ((BookReservationView) bookReservationView).getUserNameField(),
                            checkIn, checkOut, chosenRoom);

                    boolean discountApplied = reservation.applyDiscount(discountCode);

                    int confirm = 0;

                    if (discountCode.length() == 0 || discountApplied) {
                        confirm = JOptionPane.showConfirmDialog(this.hrsWindow,
                                "Total price of booking: " + reservation.getTotalPrice() + "\nConfirm Booking?",
                                "Confirm",
                                JOptionPane.YES_NO_OPTION);
                    } else if (!discountApplied
                            && Arrays.asList(reservation.getDiscountCodeList()).contains(discountCode)) {
                        confirm = JOptionPane.showConfirmDialog(this.hrsWindow,
                                "Total price of booking: " + reservation.getTotalPrice()
                                        + "\nDiscount Code Conditions not fulfilled" + "\nConfirm Booking?",
                                "Confirm",
                                JOptionPane.YES_NO_OPTION);
                    } else if (!discountApplied) {
                        confirm = JOptionPane.showConfirmDialog(this.hrsWindow,
                                "Total price of booking: " + reservation.getTotalPrice()
                                        + "\n Invalid discount code" + "\nConfirm Booking?",
                                "Confirm",
                                JOptionPane.YES_NO_OPTION);
                    }

                    if (confirm == JOptionPane.YES_OPTION) {
                        chosenRoom.addReservation(reservation);

                        ((BookReservationView) bookReservationView).resetEntries();

                        hrsWindow.setContentPane(hrsWindow.getHomeScreenPanel());
                        hrsWindow.invalidate();
                        hrsWindow.validate();
                        if (!discountApplied && discountCode.length() == 0) {
                            JOptionPane.showMessageDialog(this.hrsWindow,
                                    "Succesfully booked reservation",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else if (!discountApplied
                                && Arrays.asList(reservation.getDiscountCodeList()).contains(discountCode)) {
                            JOptionPane.showMessageDialog(this.hrsWindow,
                                    "Succesfully booked reservation\n Discount Code Conditions not fulfilled",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else if (!discountApplied) {
                            JOptionPane.showMessageDialog(this.hrsWindow,
                                    "Succesfully booked reservation\n Invalid Discount Code",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);

                        } else if (discountApplied) {
                            JOptionPane.showMessageDialog(this.hrsWindow,
                                    "Succesfully booked reservation\nDiscount Code Applied",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }

            }

        }

        // View Hotel Events /////////////////////////
        // choose hotel
        else if (((JPanel) hrsWindow.getContentPane()) == viewHotelView) {
            ViewHotelView viewHotelTemp = ((ViewHotelView) viewHotelView);
            SelectHotelPanel selectHotelTemp = ((SelectHotelPanel) viewHotelTemp.getSelectHotelPanel());
            if (viewHotelTemp.getChosenHotel() == null) {
                for (JButton button : selectHotelTemp.getHotelListButtons()) {
                    if (e.getSource() == button) {
                        viewHotelTemp.setChosenHotel(hrsModel.getHotelGivenName(e.getActionCommand()));
                        viewHotelTemp.showChooseOptionPanel();
                        viewHotelTemp.updateHotelInfoPanel();
                        SelectRoomPanel selectRoomTemp = ((SelectRoomPanel) ((ViewHotelView) viewHotelView)
                                .getChooseRoomPanel());
                        selectRoomTemp.updateRoomListButtons(viewHotelTemp.getChosenHotel().getRoomList());
                        selectRoomTemp.dynamicSetActionListenerOfHotelButtons(this);
                    }
                }
                // Choose Room to display information
            } else if (e.getActionCommand().equals("Check Room Information")) {
                SelectRoomPanel selectRoomTemp = ((SelectRoomPanel) ((ViewHotelView) viewHotelView)
                        .getChooseRoomPanel());
                for (JButton button : selectRoomTemp.getRoomListButtons()) {
                    if (e.getSource() == button) {
                        viewHotelTemp.setChosenRoom(viewHotelTemp.getChosenHotel().getRoom(e.getActionCommand()));
                    }
                }
                viewHotelTemp.showChooseRoomPanel();

            } else if (e.getActionCommand().equals("Check Room Availability")) {
                viewHotelTemp.showChooseDatePanel();
            }
            // Check for reservation info
            else if (e.getActionCommand().equals("Check Reservation Information")) {
                String guestName = JOptionPane.showInputDialog(this.hrsWindow, "Enter Guest Name:",
                        "Enter Name", JOptionPane.QUESTION_MESSAGE);

                if (guestName != null) {
                    if (!viewHotelTemp.getChosenHotel().guestExists(guestName)) {
                        JOptionPane.showMessageDialog(this.hrsWindow, "Guest does not have a Reservation",
                                "Error", JOptionPane.WARNING_MESSAGE);
                    } else if (viewHotelTemp.getChosenHotel().guestExists(guestName)) {
                        Reservation reservation = null;
                        for (Room room : viewHotelTemp.getChosenHotel().getRoomList()) {
                            reservation = room.getReservation(guestName);
                            if (reservation != null) {
                                break;
                            }
                        }

                        if (reservation == null) {
                            JOptionPane.showMessageDialog(this.hrsWindow, "Guest does not have a Reservation",
                                    "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            viewHotelTemp.showReservationInfoPanel(reservation);
                        }
                    }
                }
            }

            else if (e.getActionCommand().equals("See Available Rooms for the Date")) {
                SelectDatePanel selectDatePanel = (SelectDatePanel) viewHotelTemp.getSelectDatePanel();
                int checkInDay = selectDatePanel.getCheckInDay();
                int checkOutDay = selectDatePanel.getCheckOutDay();
                int checkInHour = selectDatePanel.getCheckInHour();
                int checkOutHour = selectDatePanel.getCheckOutHour();

                if (checkOutDay <= checkInDay) {
                    JOptionPane.showMessageDialog(this.hrsWindow, "Check Out must not be before Check In",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    viewHotelTemp.showRoomAvailablePanel(new Date(checkInDay, checkInHour),
                            new Date(checkOutDay, checkOutHour));
                }

            }
            // Choose Room to display information
            else if (viewHotelTemp.getChosenRoom() == null) {
                for (JButton button : ((SelectRoomPanel) viewHotelTemp.getSelectRoomPanel()).getRoomListButtons()) {
                    if (e.getSource() == button) {
                        viewHotelTemp.setChosenRoom(viewHotelTemp.getChosenHotel().getRoom(e.getActionCommand()));
                        viewHotelTemp.showRoomInformationPanel();
                    }
                }
            }

        }
        //////////////////////////////////////////////

        // Manage Hotel
        // Choose Hotel
        else if (((JPanel) hrsWindow.getContentPane()) == manageHotelView) {
            ManageHotelView manageHotelTemp = ((ManageHotelView) manageHotelView);
            SelectHotelPanel selectHotelTemp = ((SelectHotelPanel) manageHotelTemp.getSelectHotelPanel());
            if (manageHotelTemp.getChosenHotel() == null) {
                for (JButton button : selectHotelTemp.getHotelListButtons()) {
                    if (e.getSource() == button) {
                        manageHotelTemp.setChosenHotel(hrsModel.getHotelGivenName(e.getActionCommand()));
                        manageHotelTemp.showChooseOptionPanel();
                        manageHotelTemp.updateHotelInfoPanel();
                        SelectRoomPanel selectRoomTemp = ((SelectRoomPanel) manageHotelTemp.getSelectRoomPanel());
                        selectRoomTemp.updateRoomListButtons(manageHotelTemp.getChosenHotel().getRoomList());
                        selectRoomTemp.dynamicSetActionListenerOfHotelButtons(this);
                        DisplayRoomPanel displayRoomPanelTemp = ((DisplayRoomPanel) manageHotelTemp.getDisplayRoomPanel());
                        displayRoomPanelTemp.updateRoomCounts(manageHotelTemp.getChosenHotel().getRoomList());
                        displayRoomPanelTemp.updateRoomList(manageHotelTemp.getChosenHotel().getRoomList());
                        DisplayPrices displayPricesTemp = ((DisplayPrices) manageHotelTemp.getDisplayPrices());
                        displayPricesTemp.updatePrices(manageHotelTemp.getChosenHotel().getRoom(0).getBasePrice());
                    }
                }
                // Change Hotel Name working, with confirmation, with dup checker
            } else if (e.getActionCommand().equals("Change Hotel Name")) {
                String newHotelName = JOptionPane.showInputDialog(this.hrsWindow, "Enter new hotel name:",
                        "Change Hotel Name", JOptionPane.QUESTION_MESSAGE);
                if (newHotelName != null && !newHotelName.isEmpty()) {
                    if (hrsModel.isHotelDup(newHotelName)
                            && !newHotelName.equals(manageHotelTemp.getChosenHotel().getName())) {
                        JOptionPane.showMessageDialog(this.hrsWindow, "Hotel name already exists", "Error",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        int confirm = JOptionPane.showConfirmDialog(this.hrsWindow,
                                "Are you sure you want to change the hotel name to " + newHotelName + "?", "Confirm",
                                JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            manageHotelTemp.getChosenHotel().setName(newHotelName);
                            manageHotelTemp.updateHotelInfoPanel();
                            JOptionPane.showMessageDialog(this.hrsWindow, "Hotel name changed successfully", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            // REFRESH OPTIONS PANEL SO THAT THE NEW NAME WILL ALSO BE UPDATED
                        }
                    }
                }

            } else if (e.getActionCommand().equals("Modify Room Type")) {
                // String temp = JOptionPane.showInputDialog(this.hrsWindow, "Test");
                manageHotelTemp.showChooseRoomPanel();

                // Add rooms working, with confirmation and check if above 50 rooms
            } else if (e.getActionCommand().equals("Add Rooms")) {
                manageHotelTemp.showDisplayRoomPanel();
                String roomType = JOptionPane.showInputDialog(this.hrsWindow,
                        "Enter room type (Standard, Deluxe, Executive):",
                        "Add Rooms", JOptionPane.QUESTION_MESSAGE);
                if (roomType != null && !roomType.isEmpty()) {
                    int numRooms = 0;
                    try {
                        numRooms = Integer
                                .parseInt(JOptionPane.showInputDialog(this.hrsWindow, "Enter number of rooms to add:",
                                        "Add Rooms", JOptionPane.QUESTION_MESSAGE));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this.hrsWindow, "Invalid input. Please enter a valid number.",
                                "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (numRooms <= 0) {
                        JOptionPane.showMessageDialog(this.hrsWindow, "Number of rooms must be greater than 0.",
                                "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    int currentRoomCount = manageHotelTemp.getChosenHotel().getRoomList().size();
                    if (currentRoomCount + numRooms > 50) {
                        JOptionPane.showMessageDialog(this.hrsWindow, "Total number of rooms cannot exceed 50.",
                                "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    int confirm = JOptionPane.showConfirmDialog(this.hrsWindow,
                            "Are you sure you want to add " + numRooms + " " + roomType + " rooms?", "Confirm",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        String hotelName = manageHotelTemp.getChosenHotel().getName();
                        int roomNumber = manageHotelTemp.getChosenHotel().getRoomList().size() + 1;

                        for (int i = 0; i < numRooms; i++) {
                            String roomName = "";
                            switch (roomType) {
                                case "Standard":
                                    roomName = hotelName + "00" + roomNumber;
                                    manageHotelTemp.getChosenHotel().addRoom(new Room(roomName));
                                    break;
                                case "Deluxe":
                                    roomName = hotelName + "55" + roomNumber;
                                    manageHotelTemp.getChosenHotel().addRoom(new DeluxeRoom(roomName));
                                    break;
                                case "Executive":
                                    roomName = hotelName + "77" + roomNumber;
                                    manageHotelTemp.getChosenHotel().addRoom(new ExecutiveRoom(roomName));
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(this.hrsWindow, "Invalid room type.",
                                            "Error", JOptionPane.WARNING_MESSAGE);
                                    return;
                            }
                            roomNumber++;
                        }

                        DisplayRoomPanel displayRoomPanelTemp = ((DisplayRoomPanel) manageHotelTemp
                                .getDisplayRoomPanel());
                        displayRoomPanelTemp.updateRoomCounts(manageHotelTemp.getChosenHotel().getRoomList());
                        displayRoomPanelTemp.updateRoomList(manageHotelTemp.getChosenHotel().getRoomList());
                        JOptionPane.showMessageDialog(this.hrsWindow, "Rooms added successfully!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else if (e.getActionCommand().equals("Remove Rooms")) {
                manageHotelTemp.showChooseRoomPanel();
                //Show choose room panel then it will show the button rooms then theyll click one of them then present a JPanel asking if they want to remove that room if yes then remove if not not
                
                // Remove hotel working, with confirmation
            } else if (e.getActionCommand().equals("Update Base Price")) {
                manageHotelTemp.showPriceDisplay();

                // Check if there are any reservations in the hotel
                if (manageHotelTemp.getChosenHotel().hasReservations()) {
                    JOptionPane.showMessageDialog(this.hrsWindow, "Cannot update base price: there are existing reservations.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String newBasePriceStr = JOptionPane.showInputDialog(this.hrsWindow, "Enter new base price (>= 100.0):",
                        "Update Base Price", JOptionPane.QUESTION_MESSAGE);
                        if (newBasePriceStr != null && !newBasePriceStr.isEmpty()) {
                            try {
                                double newBasePrice = Double.parseDouble(newBasePriceStr);
                                if (newBasePrice < 100.0) {
                                    JOptionPane.showMessageDialog(this.hrsWindow, "Base price must be greater than or equal to 100.0.",
                                            "Error", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }

                                int confirm = JOptionPane.showConfirmDialog(this.hrsWindow,
                                        "Are you sure you want to update the base price to " + newBasePrice + "?",
                                        "Confirm", JOptionPane.YES_NO_OPTION);
                                if (confirm == JOptionPane.YES_OPTION) {
                                    for (Room room : manageHotelTemp.getChosenHotel().getRoomList()) {
                                        room.setPrice(newBasePrice);
                                    }

                                DisplayPrices displayPricesTemp = ((DisplayPrices) manageHotelTemp.getDisplayPrices());
                                displayPricesTemp.updatePrices(manageHotelTemp.getChosenHotel().getRoom(0).getBasePrice());

                                JOptionPane.showMessageDialog(this.hrsWindow, "Base price updated successfully!",
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                                }

                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(this.hrsWindow, "Invalid input. Please enter a valid number.",
                                        "Error", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        
            } else if (e.getActionCommand().equals("Remove Reservation")){
                manageHotelTemp.showChooseRoomPanel();
                SelectRoomPanel selectRoomTemp = ((SelectRoomPanel) manageHotelTemp.getSelectRoomPanel());
                for (JButton button : selectRoomTemp.getRoomListButtons()) {
                    if (e.getSource() == button) {
                        Room chosenRoom = manageHotelTemp.getChosenHotel().getRoom(e.getActionCommand());
                        if (!chosenRoom.hasReservation()) {
                            JOptionPane.showMessageDialog(this.hrsWindow, "No reservations to remove in this room.",
                                    "Error", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        String guestName = JOptionPane.showInputDialog(this.hrsWindow, "Enter guest name:",
                                "Remove Reservation", JOptionPane.QUESTION_MESSAGE);
                        if (guestName != null) {
                            Reservation reservation = chosenRoom.getReservation(guestName);
                            if (reservation == null) {
                                JOptionPane.showMessageDialog(this.hrsWindow, "Guest does not have a reservation in this room.",
                                        "Error", JOptionPane.WARNING_MESSAGE);
                            } else {
                                int confirm = JOptionPane.showConfirmDialog(this.hrsWindow,
                                        "Are you sure you want to remove the reservation for " + guestName + "?",
                                        "Confirm", JOptionPane.YES_NO_OPTION);
                                if (confirm == JOptionPane.YES_OPTION) {
                                    chosenRoom.removeReservation(guestName);
                                    JOptionPane.showMessageDialog(this.hrsWindow, "Reservation removed successfully!",
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }
                    }
                }

            } else if (e.getActionCommand().equals("Price Rate Modifier")){
                
            } else if (e.getActionCommand().equals("Remove Hotel")) {
                int confirm = JOptionPane.showConfirmDialog(this.hrsWindow,
                        "Are you sure you want to remove the hotel " + manageHotelTemp.getChosenHotel().getName() + "?",
                        "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    hrsModel.removeHotel(manageHotelTemp.getChosenHotel());
                    manageHotelTemp.resetEntries();
                    selectHotelTemp.updateHotelDisplay(hrsModel.getHotelList());
                    JOptionPane.showMessageDialog(this.hrsWindow, "Hotel removed successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
