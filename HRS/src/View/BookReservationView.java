package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import HotelClasses.Hotel;
import HotelClasses.RoomClasses.Room;

public class BookReservationView extends JPanel {
    private JPanel chooseHotelContainer;
    private Hotel chosenHotel = null;
    private JTextField userNameTextField;
    private JPanel cardContainer;
    private CardLayout clayout;
    private Room chosenRoom = null;
    private JPanel chooseRoomPanel;
    private ArrayList<JButton> roomList = new ArrayList<JButton>();
    private JPanel chooseRoomButtonContainer;
    private SelectDatePanel selectDatePanel;
    private SelectHotelPanel selectHotelPanel;

    private JButton returnHomeButton;

    public BookReservationView() {

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
        northPanel.add(label);
        ///////////////////////////////////////////////

        // Choose Room Panel //////////////////////////////
        chooseRoomPanel = new JPanel();
        chooseRoomPanel.setLayout(new BorderLayout());
        JLabel chooseRoomLabel = new JLabel("Choose Room");
        chooseRoomLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseRoomLabel.setBorder(new EmptyBorder(20, 0, 150, 0));
        chooseRoomLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseRoomPanel.add(chooseRoomLabel, BorderLayout.NORTH);
        chooseRoomButtonContainer = new JPanel();
        chooseRoomButtonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        chooseRoomPanel.add(chooseRoomButtonContainer, BorderLayout.CENTER);

        // Choose Hotel Container ///////////////////////////////
        this.chooseHotelContainer = new JPanel();
        this.chooseHotelContainer.setLayout(new BorderLayout());
        userNameTextField = new JTextField();
        userNameTextField.setColumns(20);
        selectHotelPanel = new SelectHotelPanel();
        JPanel northContainer = new JPanel();
        northContainer.setLayout(new GridBagLayout());
        northContainer.add(new JLabel("Enter Guest Name"), gbc);
        northContainer.add(userNameTextField, gbc);
        northContainer.setBorder(new EmptyBorder(20, 0, 0, 0));
        //////////////////////////////////////////////////

        selectDatePanel = new SelectDatePanel("Book");

        ///////////////////////////////////////////////

        // South Panel //////////////////////
        JLabel chooseHotelLabel = new JLabel("Choose Hotel to Book in");
        chooseHotelLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseHotelLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseHotelLabel.setBorder(new EmptyBorder(0, 0, 100, 0));
        ////////////////////////////////////////

        cardContainer = new JPanel();
        clayout = new CardLayout();
        cardContainer.setLayout(clayout);
        cardContainer.add(chooseHotelContainer, "chooseHotel");
        cardContainer.add(selectDatePanel, "date");
        cardContainer.add(chooseRoomPanel, "room");

        this.chooseHotelContainer.add(selectHotelPanel, BorderLayout.CENTER);
        this.chooseHotelContainer.add(northContainer, BorderLayout.NORTH);
        this.chooseHotelContainer.add(chooseHotelLabel, BorderLayout.SOUTH);

        clayout.show(cardContainer, "chooseHotel");

        this.add(northPanel, BorderLayout.NORTH);
        this.add(cardContainer, BorderLayout.CENTER);
    }

    public void updateRoomList(ArrayList<Room> rooms) {
        for (JButton roomOption : this.roomList) {
            this.chooseRoomButtonContainer.remove(roomOption);
        }
        this.roomList.clear();

        for (Room room : rooms) {
            JButton temp = new JButton(room.getName());
            temp.setPreferredSize(new Dimension(148, 30));
            this.roomList.add(temp);
        }

        for (JButton button : this.roomList) {
            this.chooseRoomButtonContainer.add(button);
        }
    }

    public void setActionListener(ActionListener listener) {
        this.returnHomeButton.addActionListener(listener);
        ((SelectDatePanel) selectDatePanel).getButton().addActionListener(listener);
    }

    public void setChosenHotel(Hotel hotel) {
        this.chosenHotel = hotel;
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
    }

    public void showChooseDatePanel() {
        clayout.show(cardContainer, "date");
    }

    public void showChooseRoomPanel() {
        clayout.show(cardContainer, "room");
    }

    public void setChosenRoom(Room room) {
        this.chosenRoom = room;
    }

    public Room getChosenRoom() {
        return this.chosenRoom;
    }

    public ArrayList<JButton> getRoomList() {
        return this.roomList;
    }

    public JPanel getSelectHotelPanel() {
        return selectHotelPanel;
    }

    public JPanel getSelectDatePanel() {
        return selectDatePanel;
    }

}
