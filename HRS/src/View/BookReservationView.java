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
    private ArrayList<JButton> hotelListDisplay = new ArrayList<JButton>();
    private JPanel centerPanel;
    private Hotel chosenHotel = null;
    private JPanel chooseDatePanel;
    private JSpinner checkInTimeSpinner;
    private JSpinner checkInDaySpinner;
    private JSpinner checkOutTimeSpinner;
    private JSpinner checkOutDaySpinner;
    private JPanel chooseHotelContainer;
    private JButton bookReservationButton;
    private JTextField userNameTextField;
    private JPanel cardContainer;
    private CardLayout clayout;
    private Room chosenRoom = null;
    private JPanel chooseRoomPanel;
    private ArrayList<JButton> roomList = new ArrayList<JButton>();
    private JPanel chooseRoomButtonContainer;

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

        // Center panel ///////////////////////////////
        this.centerPanel = new JPanel();
        this.centerPanel.setLayout(new BorderLayout());
        JLabel chooseHotelLabel = new JLabel("Choose Hotel to Book in");
        chooseHotelLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseHotelLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseHotelLabel.setBorder(new EmptyBorder(0, 0, 100, 0));
        userNameTextField = new JTextField();
        userNameTextField.setColumns(20);
        this.chooseHotelContainer = new JPanel();
        JPanel northContainer = new JPanel();
        northContainer.setLayout(new GridBagLayout());
        this.chooseHotelContainer.setLayout(new GridBagLayout());
        northContainer.add(new JLabel("Enter Guest Name"), gbc);
        northContainer.add(userNameTextField, gbc);
        northContainer.setBorder(new EmptyBorder(20, 0, 0, 0));
        //////////////////////////////////////////////////

        // Choose Date Panel ///////////////////////////
        this.chooseDatePanel = new JPanel();
        this.chooseDatePanel.setLayout(new GridBagLayout());
        checkInTimeSpinner = new JSpinner(new SpinnerNumberModel(12, 0, 23, 1));
        checkInTimeSpinner.setPreferredSize(new Dimension(60, 30));
        checkInDaySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        checkInDaySpinner.setPreferredSize(new Dimension(60, 30));
        checkOutTimeSpinner = new JSpinner(new SpinnerNumberModel(12, 0, 23, 1));
        checkOutTimeSpinner.setPreferredSize(new Dimension(60, 30));
        checkOutDaySpinner = new JSpinner(new SpinnerNumberModel(2, 2, 31, 1));
        checkOutDaySpinner.setPreferredSize(new Dimension(60, 30));

        JPanel checkInContainer = new JPanel();
        JPanel checkOutContainer = new JPanel();
        JLabel checkInHourFormatLabel = new JLabel("24HH");
        JLabel checkOutHourFormatLabel = new JLabel("24HH");
        checkInHourFormatLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
        checkOutHourFormatLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
        checkInContainer.add(checkInTimeSpinner);
        checkInContainer.add(checkInHourFormatLabel);
        checkInContainer.add(checkInDaySpinner);
        checkInContainer.add(new JLabel("Pick Day"));

        checkOutContainer.add(checkOutTimeSpinner);
        checkOutContainer.add(checkOutHourFormatLabel);
        checkOutContainer.add(checkOutDaySpinner);
        checkOutContainer.add(new JLabel("Pick Day"));
        JLabel pickCheckInLabel = new JLabel("Pick Check-in Date");
        JLabel pickCheckOutLabel = new JLabel("Pick Check-out Date");
        pickCheckInLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        pickCheckOutLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        pickCheckInLabel.setHorizontalAlignment(JLabel.CENTER);
        pickCheckOutLabel.setHorizontalAlignment(JLabel.CENTER);
        pickCheckOutLabel.setBorder(new EmptyBorder(30, 0, 0, 0));
        this.chooseDatePanel.add(pickCheckInLabel, gbc);
        this.chooseDatePanel.add(checkInContainer, gbc);
        this.chooseDatePanel.add(pickCheckOutLabel, gbc);
        this.chooseDatePanel.add(checkOutContainer, gbc);
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBorder(new EmptyBorder(30, 0, 0, 0));
        bookReservationButton = new JButton("Book");
        buttonContainer.add(bookReservationButton);
        this.chooseDatePanel.add(buttonContainer, gbc);

        ///////////////////////////////////////////////

        cardContainer = new JPanel();
        clayout = new CardLayout();
        cardContainer.setLayout(clayout);
        cardContainer.add(centerPanel, "center");
        cardContainer.add(chooseDatePanel, "date");
        cardContainer.add(chooseRoomPanel, "room");

        this.centerPanel.add(chooseHotelContainer, BorderLayout.CENTER);
        this.centerPanel.add(northContainer, BorderLayout.NORTH);
        this.centerPanel.add(chooseHotelLabel, BorderLayout.SOUTH);

        clayout.show(cardContainer, "center");

        this.add(northPanel, BorderLayout.NORTH);
        this.add(cardContainer, BorderLayout.CENTER);
    }

    public void updateHotelDisplay(ArrayList<Hotel> hotelList) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        // Very resource wasting process, but cant think of a better solution, will be
        // fine for now
        // done this way so for when editing hotel name is implemented, the option names
        // are also updated
        for (JButton hotelOption : this.hotelListDisplay) {
            this.chooseHotelContainer.remove(hotelOption);
        }
        this.hotelListDisplay.clear();
        if (hotelList.size() > 0) {
            for (Hotel hotel : hotelList) {
                this.hotelListDisplay.add(new JButton(hotel.getName()));
            }

            for (JButton hotelDisplay : this.hotelListDisplay) {
                this.chooseHotelContainer.add(hotelDisplay, gbc);
            }
        }

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
        this.bookReservationButton.addActionListener(listener);
    }

    public void dynamicSetActionListenerOfHotelButtons(ActionListener listener) {
        for (JButton button : hotelListDisplay) {
            button.addActionListener(listener);
        }

        for (JButton button : roomList) {
            button.addActionListener(listener);
        }
    }

    public ArrayList<JButton> getHotelListDisplayOptions() {
        return this.hotelListDisplay;
    }

    public void setChosenHotel(Hotel hotel) {
        this.chosenHotel = hotel;
    }

    public Hotel getChosenHotel() {
        return this.chosenHotel;
    }

    public JPanel getCenterPanel() {
        return this.centerPanel;
    }

    public JPanel getChooseDatePanel() {
        return this.chooseDatePanel;
    }

    public int getCheckInDay() {
        return (Integer) this.checkInDaySpinner.getValue();
    }

    public int getCheckOutDay() {
        return (Integer) this.checkOutDaySpinner.getValue();
    }

    public int getCheckInHour() {
        return (Integer) this.checkInTimeSpinner.getValue();
    }

    public int getCheckOutHour() {
        return (Integer) this.checkOutTimeSpinner.getValue();
    }

    public void resetEntries() {
        checkInDaySpinner.setValue(1);
        checkOutDaySpinner.setValue(2);
        checkInTimeSpinner.setValue(12);
        checkOutTimeSpinner.setValue(12);
        userNameTextField.setText("");
        chosenRoom = null;
        chosenHotel = null;
    }

    public String getUserNameField() {
        return this.userNameTextField.getText();
    }

    public void showDefaultCenterPanel() {
        clayout.show(cardContainer, "center");
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

}
