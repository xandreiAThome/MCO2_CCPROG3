package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

import javax.swing.border.EmptyBorder;

import CustomJPanels.JPanelWithBackground;
import CustomJPanels.SelectDatePanel;
import CustomJPanels.SelectDatePanelWithDiscount;
import CustomJPanels.SelectHotelPanel;
import CustomJPanels.SelectRoomPanel;
import HotelClasses.Hotel;
import HotelClasses.RoomClasses.Room;

/**
 * The BookReservationView class represents the view for booking a reservation
 * in a hotel.
 * It extends the JPanelWithBackground class to display a background image.
 * The class contains various panels and components for selecting a hotel, room,
 * and date for the reservation.
 * It also provides methods for setting action listeners, getting and setting
 * chosen hotel and room, and resetting entries.
 */
public class BookReservationView extends JPanelWithBackground {
    private JPanel chooseHotelContainer;
    private Hotel chosenHotel = null;
    private JTextField userNameTextField;
    private JPanel cardContainer;
    private CardLayout clayout;
    private Room chosenRoom = null;
    private SelectRoomPanel selectRoomPanel;
    private SelectDatePanel selectDatePanel;
    private SelectHotelPanel selectHotelPanel;
    private String currentCardPanel;

    private JButton returnHomeButton;

    public BookReservationView() throws IOException {
        super("View/pics/door.png");

        this.setLayout(new BorderLayout());

        this.returnHomeButton = new JButton("Home");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // North panel ///////////////////////////////////
        JLabel label = new JLabel("Book Reservation");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.BLUE);
        northPanel.add(this.returnHomeButton);
        northPanel.setOpaque(false);
        northPanel.add(label);
        ///////////////////////////////////////////////

        // Choose Room Panel //////////////////////////////
        selectRoomPanel = new SelectRoomPanel("Choose Room", Color.WHITE, new Color(0, 0, 0, 0));

        // Choose Hotel Container ///////////////////////////////
        this.chooseHotelContainer = new JPanel();
        this.chooseHotelContainer.setLayout(new BorderLayout());
        chooseHotelContainer.setOpaque(false);
        userNameTextField = new JTextField();
        userNameTextField.setColumns(20);
        selectHotelPanel = new SelectHotelPanel(new Color(0, 0, 0, 0));
        selectHotelPanel.setOpaque(false);

        JPanel northContainer = new JPanel();
        northContainer.setOpaque(false);
        northContainer.setLayout(new GridBagLayout());
        JLabel enterNameLabel = new JLabel("Enter Guest Name");
        enterNameLabel.setForeground(Color.WHITE);
        northContainer.add(enterNameLabel, gbc);

        northContainer.add(userNameTextField, gbc);
        northContainer.setBorder(new EmptyBorder(20, 0, 0, 0));

        JLabel chooseHotelLabel = new JLabel("Choose Hotel to Book in");
        chooseHotelLabel.setForeground(Color.WHITE);
        chooseHotelLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseHotelLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseHotelLabel.setBorder(new EmptyBorder(0, 0, 100, 0));

        this.chooseHotelContainer.add(selectHotelPanel, BorderLayout.CENTER);
        this.chooseHotelContainer.add(northContainer, BorderLayout.NORTH);
        this.chooseHotelContainer.add(chooseHotelLabel, BorderLayout.SOUTH);
        //////////////////////////////////////////////////

        // Choose Date Panel

        selectDatePanel = new SelectDatePanelWithDiscount("Book", Color.white, new Color(0, 0, 0, 0));

        ////////////////////////////////////////

        cardContainer = new JPanel();
        cardContainer.setOpaque(false);
        clayout = new CardLayout();
        cardContainer.setLayout(clayout);
        cardContainer.add(chooseHotelContainer, "chooseHotel");
        cardContainer.add(selectDatePanel, "date");
        cardContainer.add(selectRoomPanel, "room");

        clayout.show(cardContainer, "chooseHotel");
        currentCardPanel = "chooseHotel";

        this.add(northPanel, BorderLayout.NORTH);
        this.add(cardContainer, BorderLayout.CENTER);
    }

    public void setActionListener(ActionListener listener) {
        this.returnHomeButton.addActionListener(listener);
        ((SelectDatePanel) selectDatePanel).getButton().addActionListener(listener);
    }

    public void setChosenHotel(Hotel hotel) {
        this.chosenHotel = hotel;
    }

    public String getCurrentCardPanel() {
        return currentCardPanel;
    }

    public Hotel getChosenHotel() {
        return this.chosenHotel;
    }

    public JPanel getChooseHotelContainer() {
        return this.chooseHotelContainer;
    }

    public JPanel getChooseDatePanel() {
        return this.selectDatePanel;
    }

    /**
     * Resets the text entries and chosen rooms and hotel for the panel
     */
    public void resetEntries() {
        ((SelectDatePanel) selectDatePanel).resetEntries();
        userNameTextField.setText("");
        chosenRoom = null;
        chosenHotel = null;
        showDefaultCenterPanel();
    }

    public String getUserNameField() {
        return this.userNameTextField.getText();
    }

    public void showDefaultCenterPanel() {
        clayout.show(cardContainer, "chooseHotel");
        currentCardPanel = "chooseHotel";
    }

    public void showChooseDatePanel() {
        clayout.show(cardContainer, "date");
        currentCardPanel = "date";
    }

    public void showChooseRoomPanel() {
        clayout.show(cardContainer, "room");
        currentCardPanel = "room";
    }

    public void setChosenRoom(Room room) {
        this.chosenRoom = room;
    }

    public Room getChosenRoom() {
        return this.chosenRoom;
    }

    public JPanel getSelectHotelPanel() {
        return selectHotelPanel;
    }

    public JPanel getSelectDatePanel() {
        return selectDatePanel;
    }

    public JPanel getSelectRoomPanel() {
        return selectRoomPanel;
    }

}
