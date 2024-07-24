package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import HotelClasses.Hotel;

public class ViewHotelView extends JPanel {
    private JButton returnHomeButton;
    private JPanel centerPanel;
    private JPanel chooseHotelContainer;
    private ArrayList<JButton> hotelListButton = new ArrayList<JButton>();
    private JPanel cardContainer;
    private CardLayout clayout;
    private Hotel chosenHotel = null;
    private JPanel chooseOptionPanel;
    private JButton checkRoomAvail, checkRoomInfo, checkReserveInfo;
    private JPanel roomAvailPanel, roomInfoPanel, reserveInfoPanel, chooseRoomPanel, hotelInfoPanel;

    public ViewHotelView() {
        this.setLayout(new BorderLayout());

        this.returnHomeButton = new JButton("Home");

        JLabel label = new JLabel("View Hotel Information");
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

        // Center panel ///////////////////////////////
        this.centerPanel = new JPanel();
        this.centerPanel.setLayout(new BorderLayout());
        JLabel chooseHotelLabel = new JLabel("Choose Hotel to View");
        chooseHotelLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseHotelLabel.setVerticalAlignment(JLabel.TOP);
        chooseHotelLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseHotelLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
        chooseHotelContainer = new JPanel();
        chooseHotelContainer.setLayout(new GridBagLayout());
        chooseHotelContainer.add(chooseHotelLabel, gbc);
        JPanel chooseHotelBorderWrapper = new JPanel(new BorderLayout());
        chooseHotelBorderWrapper.add(chooseHotelLabel, BorderLayout.NORTH);
        chooseHotelBorderWrapper.add(chooseHotelContainer, BorderLayout.CENTER);

        //////////////////////////////////////////////////
        JPanel chooseOptionBorderWrapper = new JPanel(new BorderLayout());
        chooseOptionPanel = new JPanel(new GridBagLayout());
        // Choose Option Panel /////////////////////////
        checkRoomInfo = new JButton("Check Room Information");
        checkRoomAvail = new JButton("Check Room Availability");
        checkReserveInfo = new JButton("Check Reservation Information");
        chooseOptionPanel.add(checkReserveInfo, gbc);
        chooseOptionPanel.add(checkRoomAvail, gbc);
        chooseOptionPanel.add(checkRoomInfo, gbc);
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
        cardContainer.add(chooseHotelBorderWrapper, "chooseHotel");
        cardContainer.add(chooseOptionBorderWrapper, "chooseOption");

        clayout.show(cardContainer, "chooseHotel");

        this.add(northPanel, BorderLayout.NORTH);
        this.add(cardContainer, BorderLayout.CENTER);

    }

    public void setActionListener(ActionListener listener) {
        this.returnHomeButton.addActionListener(listener);
    }

    public void dynamicSetActionListenerOfHotelButtons(ActionListener listener) {
        for (JButton button : hotelListButton) {
            button.addActionListener(listener);
        }
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

    public void updateHotelDisplay(ArrayList<Hotel> hotelList) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        // Very resource wasting process, but cant think of a better solution, will be
        // fine for now
        // done this way so for when editing hotel name is implemented, the option names
        // are also updated
        for (JButton hotelOption : this.hotelListButton) {
            this.chooseHotelContainer.remove(hotelOption);
        }
        this.hotelListButton.clear();
        if (hotelList.size() > 0) {
            for (Hotel hotel : hotelList) {
                this.hotelListButton.add(new JButton(hotel.getName()));
            }

            for (JButton hotelButton : this.hotelListButton) {
                this.chooseHotelContainer.add(hotelButton, gbc);
            }
        }

    }

    public ArrayList<JButton> getHotelListButton() {
        return this.hotelListButton;
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
