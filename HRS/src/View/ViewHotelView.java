package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import CustomJPanels.JPanelWithBackground;
import CustomJPanels.SelectDatePanel;
import CustomJPanels.SelectHotelPanel;
import CustomJPanels.SelectRoomPanel;
import HotelClasses.Date;
import HotelClasses.Day;
import HotelClasses.Hotel;
import HotelClasses.Reservation;
import HotelClasses.RoomClasses.Room;

public class ViewHotelView extends JPanelWithBackground {
    private JButton returnHomeButton;
    private JPanel chooseHotelContainer;
    private SelectHotelPanel selectHotelPanel;
    private JPanelWithBackground cardContainer;
    private CardLayout clayout;
    private Hotel chosenHotel = null;
    private JPanel chooseOptionPanel;
    private JButton checkRoomAvail, checkRoomInfo, checkReserveInfo;
    private JPanel roomAvailPanel, roomAvailPanelContainer, roomInfoPanel, reserveInfoPanel, hotelInfoPanel;
    private Room chosenRoom = null;
    private JPanel chooseRoomForInformationPanel, chooseDateForAvailabilityPanel;
    private Reservation chosenReservationToCompare = null;

    public ViewHotelView() throws IOException {
        super("View/pics/pages.jpg");
        this.setLayout(new BorderLayout());

        this.returnHomeButton = new JButton("Home");

        JLabel label = new JLabel("View Hotel Information");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        JPanel northPanel = new JPanel();
        northPanel.add(this.returnHomeButton);
        northPanel.add(label);
        northPanel.setBackground(new Color(0, 0, 0, 120));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Choose Hotel Container///////////////////////////////
        chooseHotelContainer = new JPanel(new BorderLayout());
        chooseHotelContainer.setOpaque(false);
        JPanel northLabelContainer = new JPanel(new FlowLayout());
        northLabelContainer.setBackground(new Color(0, 0, 0, 120));
        JLabel chooseHotelLabel = new JLabel("Choose Hotel to View");
        chooseHotelLabel.setForeground(Color.white);
        chooseHotelLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseHotelLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseHotelLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
        selectHotelPanel = new SelectHotelPanel(new Color(0, 0, 0, 120));
        northLabelContainer.add(chooseHotelLabel);
        chooseHotelContainer.add(northLabelContainer, BorderLayout.NORTH);
        chooseHotelContainer.add(selectHotelPanel, BorderLayout.CENTER);

        //////////////////////////////////////////////////

        // Choose Option Panel /////////////////////////
        JPanel chooseOptionBorderWrapper = new JPanel(new BorderLayout());
        chooseOptionBorderWrapper.setOpaque(false);
        chooseOptionPanel = new JPanel(new GridBagLayout());
        chooseOptionPanel.setBackground(new Color(0, 0, 0, 120));
        checkRoomInfo = new JButton("Check Room Information");
        checkRoomAvail = new JButton("Check Room Availability");
        checkReserveInfo = new JButton("Check Reservation Information");
        chooseOptionPanel.add(checkReserveInfo, gbc);
        chooseOptionPanel.add(checkRoomAvail, gbc);
        chooseOptionPanel.add(checkRoomInfo, gbc);
        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.setBackground(new Color(0, 0, 0, 120));
        JLabel chooseOptionLabel = new JLabel("Choose Option");
        chooseOptionLabel.setForeground(Color.white);
        chooseOptionLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseOptionLabel.setVerticalAlignment(JLabel.TOP);
        chooseOptionLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseOptionLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        southPanel.add(chooseOptionLabel);
        chooseOptionBorderWrapper.add(southPanel, BorderLayout.SOUTH);
        chooseOptionBorderWrapper.add(chooseOptionPanel, BorderLayout.CENTER);

        hotelInfoPanel = new JPanel(new GridBagLayout());
        hotelInfoPanel.setBackground(new Color(0, 0, 0, 120));
        chooseOptionBorderWrapper.add(hotelInfoPanel, BorderLayout.NORTH);

        /////////////////////////////////////////////////

        // Choose Room for to view Room Information
        chooseRoomForInformationPanel = new SelectRoomPanel("Choose Room", Color.WHITE, new Color(0, 0, 0, 120));

        // Room Information Panel
        roomInfoPanel = new JPanel(new BorderLayout());
        roomInfoPanel.setOpaque(false);

        // Available Rooms Panel
        roomAvailPanelContainer = new JPanel(new BorderLayout());
        roomAvailPanelContainer.setOpaque(false);

        // Choose Date to see available rooms
        chooseDateForAvailabilityPanel = new SelectDatePanel("See Available Rooms for the Date", Color.white,
                new Color(0, 0, 0, 120));

        // Reservation Information panel
        reserveInfoPanel = new JPanel(new BorderLayout());
        reserveInfoPanel.setOpaque(false);

        cardContainer = new JPanelWithBackground("View/pics/pages.jpg");
        clayout = new CardLayout();
        cardContainer.setLayout(clayout);
        cardContainer.add(chooseHotelContainer, "chooseHotel");
        cardContainer.add(chooseOptionBorderWrapper, "chooseOption");
        cardContainer.add(chooseRoomForInformationPanel, "chooseRoom");
        cardContainer.add(chooseDateForAvailabilityPanel, "chooseDate");
        cardContainer.add(roomAvailPanelContainer, "availableRooms");
        cardContainer.add(roomInfoPanel, "roomInformation");
        cardContainer.add(reserveInfoPanel, "reservationInformation");

        clayout.show(cardContainer, "chooseHotel");

        this.add(northPanel, BorderLayout.NORTH);
        this.add(cardContainer, BorderLayout.CENTER);

    }

    public void setActionListener(ActionListener listener) {
        this.returnHomeButton.addActionListener(listener);
        this.checkRoomInfo.addActionListener(listener);
        this.checkRoomAvail.addActionListener(listener);
        this.checkReserveInfo.addActionListener(listener);
        ((SelectDatePanel) chooseDateForAvailabilityPanel).setActionListener(listener);
    }

    public void updateHotelInfoPanel() {
        hotelInfoPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel hotelName = new JLabel("Hotel " + chosenHotel.getName());
        hotelName.setHorizontalAlignment(JLabel.CENTER);
        hotelName.setForeground(Color.white);
        hotelName.setFont(new Font("Verdana", Font.BOLD, 16));
        JLabel numOfRoom = new JLabel("Number of Rooms: " + chosenHotel.getRoomAmt());
        numOfRoom.setForeground(Color.white);
        numOfRoom.setHorizontalAlignment(JLabel.CENTER);
        numOfRoom.setFont(new Font("Verdana", Font.BOLD, 16));
        JLabel earningForMonth = new JLabel("Estimated earnings for the Month: " + chosenHotel.earningForMonth());
        earningForMonth.setForeground(Color.white);
        earningForMonth.setHorizontalAlignment(JLabel.CENTER);
        earningForMonth.setFont(new Font("Verdana", Font.BOLD, 16));

        hotelInfoPanel.add(hotelName, gbc);
        hotelInfoPanel.add(numOfRoom, gbc);
        hotelInfoPanel.add(earningForMonth, gbc);
    }

    public JPanel getSelectHotelPanel() {
        return selectHotelPanel;
    }

    public JPanel getSelectDatePanel() {
        return chooseDateForAvailabilityPanel;
    }

    public JPanel getSelectRoomPanel() {
        return chooseRoomForInformationPanel;
    }

    public Hotel getChosenHotel() {
        return this.chosenHotel;
    }

    public void setChosenHotel(Hotel hotel) {
        this.chosenHotel = hotel;
    }

    public void setChosenRoom(Room room) {
        this.chosenRoom = room;
    }

    public Room getChosenRoom() {
        return this.chosenRoom;
    }

    public Reservation getChosenReservation() {
        return this.chosenReservationToCompare;
    }

    public void setChosenReservation(Reservation reservation) {
        this.chosenReservationToCompare = reservation;
    }

    public void showChooseOptionPanel() {
        clayout.show(cardContainer, "chooseOption");
    }

    public void showChooseHotelPanel() {
        clayout.show(cardContainer, "chooseHotel");
    }

    public void showChooseRoomPanel() {
        clayout.show(cardContainer, "chooseRoom");
    }

    public void showChooseDatePanel() {
        clayout.show(cardContainer, "chooseDate");
    }

    public void resetEntries() {
        showChooseHotelPanel();
        ((SelectDatePanel) chooseDateForAvailabilityPanel).resetEntries();
        chosenHotel = null;
        chosenRoom = null;
    }

    public JPanel getChooseRoomPanel() {
        return chooseRoomForInformationPanel;
    }

    public JPanel getChooseDatePanel() {
        return chooseDateForAvailabilityPanel;
    }

    public void showRoomAvailablePanel(Date checkIn, Date checkOut) {
        roomAvailPanelContainer.removeAll();
        roomAvailPanel = new SelectRoomPanel("Available Rooms from day " + checkIn.getDay() + "-" + checkIn.getHour()
                + "HH to day " + checkOut.getDay() + "-" + checkOut.getHour() + "HH", Color.white,
                new Color(0, 0, 0, 120));

        roomAvailPanelContainer.add(roomAvailPanel);
        ((SelectRoomPanel) roomAvailPanel).updateRoomListButtonsAvailableGivenDate(chosenHotel.getRoomList(), checkIn,
                checkOut);

        clayout.show(cardContainer, "availableRooms");
    }

    public void showRoomInformationPanel() {
        roomInfoPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 0, 7, 0);

        JPanel roomInfoLabelContainer = new JPanel(new GridBagLayout());
        roomInfoLabelContainer.setBackground(new Color(0, 0, 0, 120));

        JLabel roomInfoLabel = new JLabel("Room Name: " + chosenRoom.getName());
        roomInfoLabel.setHorizontalAlignment(JLabel.CENTER);
        roomInfoLabel.setVerticalAlignment(JLabel.TOP);
        roomInfoLabel.setForeground(Color.white);
        roomInfoLabel.setFont(new Font("Verdana", Font.PLAIN, 20));

        JLabel roomInfoPrice = new JLabel("Price per night: " + chosenRoom.getBasePrice());
        roomInfoPrice.setForeground(Color.white);
        roomInfoPrice.setHorizontalAlignment(JLabel.CENTER);
        roomInfoPrice.setVerticalAlignment(JLabel.TOP);
        roomInfoPrice.setFont(new Font("Verdana", Font.PLAIN, 20));

        JLabel roomAvailLabel = new JLabel("Room Availability for the month");
        roomAvailLabel.setForeground(Color.white);
        roomAvailLabel.setHorizontalAlignment(JLabel.CENTER);
        roomAvailLabel.setVerticalAlignment(JLabel.TOP);
        roomAvailLabel.setFont(new Font("Verdana", Font.PLAIN, 16));

        roomInfoLabelContainer.add(roomInfoLabel, gbc);
        roomInfoLabelContainer.add(roomInfoPrice, gbc);
        roomInfoLabelContainer.add(roomAvailLabel, gbc);

        Day roomCalendar[] = chosenRoom.getMonth().getCalendar();

        JPanel calendarContainer = new JPanel(new GridLayout(4, 10, 5, 5));
        calendarContainer.setBackground(new Color(0, 0, 0, 120));
        calendarContainer.setBorder(new EmptyBorder(80, 180, 80, 180));

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridwidth = GridBagConstraints.REMAINDER;
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 1; i <= 31; i++) {
            JPanel dayContainer = new JPanel(new GridBagLayout());
            dayContainer.setBorder(new LineBorder(Color.BLACK, 2, true));
            JLabel day = new JLabel("" + i);
            day.setHorizontalAlignment(JLabel.CENTER);
            dayContainer.add(day, gbc2);
            JLabel statusLabel = new JLabel();
            statusLabel.setHorizontalAlignment(JLabel.CENTER);
            if (roomCalendar[i - 1].getIsBooked()) {
                statusLabel.setText("Booked");
                dayContainer.add(statusLabel, gbc2);
            } else {
                statusLabel.setText("Available");
                dayContainer.add(statusLabel, gbc2);
            }

            JLabel priceRateLabel = new JLabel((roomCalendar[i - 1].getPriceRate() * 100) + "% of base price");
            priceRateLabel.setFont(new Font("Verdana", Font.PLAIN, 8));
            dayContainer.add(priceRateLabel, gbc2);
            calendarContainer.add(dayContainer);
        }

        roomInfoPanel.add(roomInfoLabelContainer, BorderLayout.NORTH);
        roomInfoPanel.add(calendarContainer, BorderLayout.CENTER);
        clayout.show(cardContainer, "roomInformation");
    }

    public void showReservationInfoPanel(Reservation reservation) {
        reserveInfoPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JPanel labelContainer = new JPanel(new GridBagLayout());
        labelContainer.setBackground(new Color(0, 0, 0, 120));

        JLabel guestName = new JLabel("Guest name: " + reservation.getGuestName());
        guestName.setForeground(Color.white);
        guestName.setHorizontalAlignment(JLabel.CENTER);
        guestName.setVerticalAlignment(JLabel.TOP);
        guestName.setFont(new Font("Verdana", Font.PLAIN, 16));

        JLabel checkInLabel = new JLabel("Check-in Date: Day " + reservation.getCheckInDate().getDay() + " - "
                + reservation.getCheckInDate().getHour() + "HH");
        checkInLabel.setForeground(Color.white);
        checkInLabel.setHorizontalAlignment(JLabel.CENTER);
        checkInLabel.setVerticalAlignment(JLabel.TOP);
        checkInLabel.setFont(new Font("Verdana", Font.PLAIN, 16));

        JLabel checkOutLabel = new JLabel("Check-out Date: Day " + reservation.getCheckOutDate().getDay() + " - "
                + reservation.getCheckOutDate().getHour() + "HH");
        checkOutLabel.setForeground(Color.white);
        checkOutLabel.setHorizontalAlignment(JLabel.CENTER);
        checkOutLabel.setVerticalAlignment(JLabel.TOP);
        checkOutLabel.setFont(new Font("Verdana", Font.PLAIN, 16));

        JLabel totalPriceLabel = new JLabel("Total price of reservation: " + reservation.getTotalPrice());
        totalPriceLabel.setForeground(Color.white);
        totalPriceLabel.setHorizontalAlignment(JLabel.CENTER);
        totalPriceLabel.setVerticalAlignment(JLabel.TOP);
        totalPriceLabel.setFont(new Font("Verdana", Font.PLAIN, 16));

        JLabel roomInfoLabel = new JLabel("Room name: " + reservation.getChosenRoom().getName()
                + "    Price per night: " + reservation.getChosenRoom().getBasePrice());
        roomInfoLabel.setForeground(Color.white);
        roomInfoLabel.setHorizontalAlignment(JLabel.CENTER);
        roomInfoLabel.setVerticalAlignment(JLabel.TOP);
        roomInfoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));

        JLabel roomAvailLabel = new JLabel("Room availabilty for the month");
        roomAvailLabel.setForeground(Color.white);
        roomAvailLabel.setHorizontalAlignment(JLabel.CENTER);
        roomAvailLabel.setVerticalAlignment(JLabel.TOP);
        roomAvailLabel.setFont(new Font("Verdana", Font.PLAIN, 16));

        labelContainer.add(guestName, gbc);
        labelContainer.add(checkInLabel, gbc);
        labelContainer.add(checkOutLabel, gbc);
        labelContainer.add(totalPriceLabel, gbc);
        labelContainer.add(roomInfoLabel, gbc);
        labelContainer.add(roomAvailLabel, gbc);

        Day roomCalendar[] = reservation.getChosenRoom().getMonth().getCalendar();

        JPanel calendarContainer = new JPanel(new GridLayout(4, 10, 5, 5));
        calendarContainer.setBackground(new Color(0, 0, 0, 120));
        calendarContainer.setBorder(new EmptyBorder(40, 180, 40, 180));

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridwidth = GridBagConstraints.REMAINDER;
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 1; i <= 31; i++) {
            JPanel dayContainer = new JPanel(new GridBagLayout());
            dayContainer.setBorder(new LineBorder(Color.BLACK, 2, true));
            JLabel day = new JLabel("" + i);
            day.setHorizontalAlignment(JLabel.CENTER);
            dayContainer.add(day, gbc2);
            JLabel statusLabel = new JLabel();
            statusLabel.setHorizontalAlignment(JLabel.CENTER);
            if (roomCalendar[i - 1].getIsBooked()) {
                statusLabel.setText("Booked");
                dayContainer.add(statusLabel, gbc2);
            } else {
                statusLabel.setText("Available");
                dayContainer.add(statusLabel, gbc2);
            }
            JLabel priceRateLabel = new JLabel((roomCalendar[i - 1].getPriceRate() * 100) + "% of base price");
            priceRateLabel.setFont(new Font("Verdana", Font.PLAIN, 8));
            dayContainer.add(priceRateLabel, gbc2);
            calendarContainer.add(dayContainer);
        }

        reserveInfoPanel.add(labelContainer, BorderLayout.NORTH);
        reserveInfoPanel.add(calendarContainer, BorderLayout.CENTER);
        clayout.show(cardContainer, "reservationInformation");
    }
}
