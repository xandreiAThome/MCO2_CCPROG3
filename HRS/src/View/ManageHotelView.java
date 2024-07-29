package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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

import CustomJPanels.DisplayPrices;
import CustomJPanels.DisplayRoomPanel;
import CustomJPanels.JPanelWithBackground;
import CustomJPanels.SelectHotelPanel;
import CustomJPanels.SelectRoomPanel;
import HotelClasses.Hotel;
import HotelClasses.RoomClasses.Room;

public class ManageHotelView extends JPanelWithBackground {
    private JButton returnHomeButton;
    private JPanel chooseHotelContainer;
    private SelectHotelPanel selectHotelPanel;
    // private JPanel cardContainer;
    private CardLayout clayout;
    private Hotel chosenHotel = null;
    private JPanel chooseOptionPanel;
    private JButton changeHotelName, addRooms, removeRooms, updateBasePrice, removeReservation, removeHotel,
            dateModifier;
    private JPanel hotelInfoPanel;
    private SelectRoomPanel selectRoomPanelToRemove, selectRoomPanelToModify;
    private Room chosenRoomToRemove = null, chosenRoomToModify = null;
    private DisplayRoomPanel displayRoomPanel;
    private DisplayPrices displayPrices;
    private JPanelWithBackground cardContainer;
    private String currentCardPanel;

    public ManageHotelView() throws IOException {
        super("View/pics/ManageHotelBackground.jpg");

        this.setLayout(new BorderLayout());

        this.returnHomeButton = new JButton("Home");

        JLabel label = new JLabel("Manage Hotel");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.BLUE);
        northPanel.add(this.returnHomeButton);
        northPanel.add(label);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Choose Room Panel to remove //////////////////////////////
        selectRoomPanelToRemove = new SelectRoomPanel("Choose Room to Remove", Color.BLACK, new Color(0, 0, 0, 0));

        // Choose Room Panel to modify
        selectRoomPanelToModify = new SelectRoomPanel("Choose Room to modify", Color.BLACK, new Color(0, 0, 0, 0));

        // Room display Panel /////////////////////////////
        displayRoomPanel = new DisplayRoomPanel();

        // Display Price Panel /////////////////////////////
        displayPrices = new DisplayPrices();

        // Choose Hotel Container///////////////////////////////
        chooseHotelContainer = new JPanel(new BorderLayout());
        chooseHotelContainer.setOpaque(false);
        JLabel chooseHotelLabel = new JLabel("Choose Hotel to Manage");
        chooseHotelLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseHotelLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseHotelLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
        chooseHotelLabel.setForeground(Color.WHITE);

        selectHotelPanel = new SelectHotelPanel(new Color(0, 0, 0, 0));

        chooseHotelContainer.add(chooseHotelLabel, BorderLayout.NORTH);
        chooseHotelContainer.add(selectHotelPanel, BorderLayout.CENTER);

        //////////////////////////////////////////////////
        JPanel chooseOptionBorderWrapper = new JPanel(new BorderLayout());
        chooseOptionBorderWrapper.setOpaque(false);
        chooseOptionPanel = new JPanel(new GridBagLayout());
        chooseOptionPanel.setOpaque(false);

        // Choose Option Panel /////////////////////////
        changeHotelName = new JButton("Change Hotel Name");
        // modifyRoomType = new JButton("Modify Room Type");
        addRooms = new JButton("Add Rooms");
        removeRooms = new JButton("Remove Rooms");
        updateBasePrice = new JButton("Update Base Price");
        removeReservation = new JButton("Remove Reservation");
        dateModifier = new JButton("Price Rate Modifier");
        removeHotel = new JButton("Remove Hotel");
        chooseOptionPanel.add(changeHotelName, gbc);
        // chooseOptionPanel.add(modifyRoomType, gbc);
        chooseOptionPanel.add(addRooms, gbc);
        chooseOptionPanel.add(removeRooms, gbc);
        chooseOptionPanel.add(updateBasePrice, gbc);
        chooseOptionPanel.add(removeReservation, gbc);
        chooseOptionPanel.add(dateModifier, gbc);
        chooseOptionPanel.add(removeHotel, gbc);
        JLabel chooseOptionLabel = new JLabel("Choose Option");
        chooseOptionLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseOptionLabel.setVerticalAlignment(JLabel.TOP);
        chooseOptionLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseOptionLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        chooseOptionBorderWrapper.add(chooseOptionLabel, BorderLayout.SOUTH);
        chooseOptionBorderWrapper.add(chooseOptionPanel, BorderLayout.CENTER);

        hotelInfoPanel = new JPanel(new GridBagLayout());
        chooseOptionBorderWrapper.add(hotelInfoPanel, BorderLayout.NORTH);
        hotelInfoPanel.setOpaque(false);

        /////////////////////////////////////////////////

        cardContainer = new JPanelWithBackground("View/pics/ManageHotelBackground.jpg");
        clayout = new CardLayout();
        cardContainer.setLayout(clayout);
        cardContainer.add(chooseHotelContainer, "chooseHotel");
        cardContainer.add(chooseOptionBorderWrapper, "chooseOption");
        cardContainer.add(selectRoomPanelToRemove, "roomToRemove");
        cardContainer.add(selectRoomPanelToModify, "roomToModify");
        cardContainer.add(displayPrices, "prices");
        cardContainer.add(displayRoomPanel, "room2");

        clayout.show(cardContainer, "chooseHotel");
        currentCardPanel = "chooseHotel";

        this.add(northPanel, BorderLayout.NORTH);
        this.add(cardContainer, BorderLayout.CENTER);

    }

    public void setActionListener(ActionListener listener) {
        this.returnHomeButton.addActionListener(listener);
        this.changeHotelName.addActionListener(listener);
        this.removeHotel.addActionListener(listener);
        // this.modifyRoomType.addActionListener(listener);
        this.addRooms.addActionListener(listener);
        this.removeRooms.addActionListener(listener);
        this.updateBasePrice.addActionListener(listener);
        this.removeReservation.addActionListener(listener);
        this.dateModifier.addActionListener(listener);
    }

    public void updateHotelInfoPanel() {
        hotelInfoPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel hotelName = new JLabel("Hotel " + chosenHotel.getName());
        hotelName.setHorizontalAlignment(JLabel.CENTER);
        hotelName.setFont(new Font("Verdana", Font.BOLD, 16));
        hotelName.setForeground(Color.WHITE); // Set font color to white

        JLabel numOfRoom = new JLabel("Number of Rooms: " + chosenHotel.getRoomAmt());
        numOfRoom.setHorizontalAlignment(JLabel.CENTER);
        numOfRoom.setFont(new Font("Verdana", Font.BOLD, 16));
        numOfRoom.setForeground(Color.WHITE); // Set font color to white

        JLabel earningForMonth = new JLabel("Estimated earnings for the Month: " + chosenHotel.earningForMonth());
        earningForMonth.setHorizontalAlignment(JLabel.CENTER);
        earningForMonth.setFont(new Font("Verdana", Font.BOLD, 16));
        earningForMonth.setForeground(Color.WHITE); // Set font color to white

        hotelInfoPanel.add(hotelName, gbc);
        hotelInfoPanel.add(numOfRoom, gbc);
        hotelInfoPanel.add(earningForMonth, gbc);
    }

    public JPanel getSelectHotelPanel() {
        return selectHotelPanel;
    }

    public Hotel getChosenHotel() {
        return this.chosenHotel;
    }

    public void setChosenHotel(Hotel hotel) {
        this.chosenHotel = hotel;
    }

    public void showChooseOptionPanel() {
        clayout.show(cardContainer, "chooseOption");
        currentCardPanel = "chooseOption";
    }

    public void showChooseHotelPanel() {
        clayout.show(cardContainer, "chooseHotel");
        currentCardPanel = "chooseHotel";
    }

    public void showChooseRoomPanelToRemove() {
        clayout.show(cardContainer, "roomToRemove");
        currentCardPanel = "roomToRemove";
    }

    public void showChooseRoomPanelToModify() {
        clayout.show(cardContainer, "roomToModify");
        currentCardPanel = "roomToModify";
    }

    public void showPriceDisplay() {
        clayout.show(cardContainer, "prices");
        currentCardPanel = "prices";
    }

    public void showDisplayRoomPanel() {
        clayout.show(cardContainer, "room2");
        currentCardPanel = "room2";
    }

    public void resetEntries() {
        showChooseHotelPanel();
        chosenHotel = null;
        chosenRoomToRemove = null;
        chosenRoomToModify = null;
    }

    public JPanel getSelectRoomPanelToRemove() {
        return selectRoomPanelToRemove;
    }

    public JPanel getSelectRoomPanelToModify() {
        return selectRoomPanelToModify;
    }

    public void setChosenRoomToRemove(Room room) {
        this.chosenRoomToRemove = room;
    }

    public void setChosenRoomToModify(Room room) {
        this.chosenRoomToModify = room;
    }

    public Room getChosenRoomToRemove() {
        return this.chosenRoomToRemove;
    }

    public Room getChosenRoomToModify() {
        return this.chosenRoomToModify;
    }

    public JPanel getDisplayRoomPanel() {
        return displayRoomPanel;
    }

    public String getCurrentCardPanel() {
        return currentCardPanel;
    }

    public JPanel getDisplayPrices() {
        return displayPrices;
    }

    public void showDatePriceModifierPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 0, 7, 0);

        JLabel roomAvailLabel = new JLabel("Price Rate Modifier");
        roomAvailLabel.setForeground(Color.white);
        roomAvailLabel.setHorizontalAlignment(JLabel.CENTER);
        roomAvailLabel.setVerticalAlignment(JLabel.TOP);
        roomAvailLabel.setFont(new Font("Verdana", Font.PLAIN, 16));

        JPanel calendarContainer = new JPanel(new GridLayout(4, 10, 5, 5));
        calendarContainer.setBackground(new Color(0, 0, 0, 120));
        calendarContainer.setBorder(new EmptyBorder(80, 250, 80, 250));

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridwidth = GridBagConstraints.REMAINDER;
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 1; i <= 31; i++) {
            JPanel dayContainer = new JPanel(new GridBagLayout());
            dayContainer.setBorder(new LineBorder(Color.BLACK, 2, true));
            JLabel day = new JLabel("" + i);
            day.setHorizontalAlignment(JLabel.CENTER);
            double priceRateOfDay = chosenHotel.getRoom(0).getMonth().getDay(i).getPriceRate() * 100;
            dayContainer.add(day, gbc2);
            dayContainer.add(new JLabel(String.valueOf(priceRateOfDay) + "%"), gbc2);
            calendarContainer.add(dayContainer);
        }

        // Create a new panel to hold the price rate modifier information
        JPanel priceRateModifierPanel = new JPanel(new BorderLayout());
        priceRateModifierPanel.add(roomAvailLabel, BorderLayout.NORTH);
        priceRateModifierPanel.add(calendarContainer, BorderLayout.CENTER);
        priceRateModifierPanel.setOpaque(false);

        // Add the priceRateModifierPanel to the cardContainer
        cardContainer.add(priceRateModifierPanel, "roomInformation");

        // Show the roomInformation panel
        clayout.show(cardContainer, "roomInformation");
    }

}