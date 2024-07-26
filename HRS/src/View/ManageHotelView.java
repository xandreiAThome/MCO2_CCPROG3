package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import CustomJPanels.SelectHotelPanel;
import HotelClasses.Hotel;
import HotelClasses.RoomClasses.Room;

public class ManageHotelView extends JPanel {
    private JButton returnHomeButton;
    private JPanel chooseHotelContainer;
    private SelectHotelPanel selectHotelPanel;
    private JPanel cardContainer;
    private CardLayout clayout;
    private Hotel chosenHotel = null;
    private JPanel chooseOptionPanel;
    private JButton changeHotelName, addRooms, removeRooms, updateBasePrice, removeReservation, removeHotel, dateModifier, modifyRoomType;
    private JPanel hotelInfoPanel;
    
    public ManageHotelView() {
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

        // Choose Hotel Container///////////////////////////////
        chooseHotelContainer = new JPanel(new BorderLayout());

        JLabel chooseHotelLabel = new JLabel("Choose Hotel to Manage");
        chooseHotelLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseHotelLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseHotelLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

        selectHotelPanel = new SelectHotelPanel();

        chooseHotelContainer.add(chooseHotelLabel, BorderLayout.NORTH);
        chooseHotelContainer.add(selectHotelPanel, BorderLayout.CENTER);

        //////////////////////////////////////////////////
        JPanel chooseOptionBorderWrapper = new JPanel(new BorderLayout());
        chooseOptionPanel = new JPanel(new GridBagLayout());

        // Choose Option Panel /////////////////////////
        changeHotelName = new JButton("Change Hotel Name");
        addRooms = new JButton("Add Rooms");
        removeRooms = new JButton("Remove Rooms");
        updateBasePrice = new JButton("Update Base Price");
        removeReservation = new JButton("Remove Reservation");
        removeHotel = new JButton("Remove Hotel");
        dateModifier = new JButton("Modify Date");
        modifyRoomType = new JButton("Modify Room Type");
        chooseOptionPanel.add(changeHotelName, gbc);
        chooseOptionPanel.add(addRooms, gbc);
        chooseOptionPanel.add(removeRooms, gbc);
        chooseOptionPanel.add(updateBasePrice, gbc);
        chooseOptionPanel.add(removeReservation, gbc);
        chooseOptionPanel.add(removeHotel, gbc);
        chooseOptionPanel.add(dateModifier, gbc);
        chooseOptionPanel.add(modifyRoomType, gbc);
        JLabel chooseOptionLabel = new JLabel("Choose Option");
        chooseOptionLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseOptionLabel.setVerticalAlignment(JLabel.TOP);
        chooseOptionLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseOptionLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        chooseOptionBorderWrapper.add(chooseOptionLabel, BorderLayout.SOUTH);
        chooseOptionBorderWrapper.add(chooseOptionPanel, BorderLayout.CENTER);

        hotelInfoPanel = new JPanel(new GridBagLayout());
        chooseOptionBorderWrapper.add(hotelInfoPanel, BorderLayout.NORTH);

        /////////////////////////////////////////////////

        cardContainer = new JPanel();
        clayout = new CardLayout();
        cardContainer.setLayout(clayout);
        cardContainer.add(chooseHotelContainer, "chooseHotel");
        cardContainer.add(chooseOptionBorderWrapper, "chooseOption");

        clayout.show(cardContainer, "chooseHotel");

        this.add(northPanel, BorderLayout.NORTH);
        this.add(cardContainer, BorderLayout.CENTER);

    }

    public void setActionListener(ActionListener listener) {
        this.returnHomeButton.addActionListener(listener);
        this.changeHotelName.addActionListener(listener);
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
        JLabel numOfRoom = new JLabel("Number of Rooms: " + chosenHotel.getRoomAmt());
        numOfRoom.setHorizontalAlignment(JLabel.CENTER);
        numOfRoom.setFont(new Font("Verdana", Font.BOLD, 16));
        JLabel earningForMonth = new JLabel("Estimated earnings for the Month: " + chosenHotel.earningForMonth());
        earningForMonth.setHorizontalAlignment(JLabel.CENTER);
        earningForMonth.setFont(new Font("Verdana", Font.BOLD, 16));

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
    }

    public void showChooseHotelPanel() {
        clayout.show(cardContainer, "chooseHotel");
    }

    public void resetEntries() {
        showChooseHotelPanel();
        chosenHotel = null;
    }
}