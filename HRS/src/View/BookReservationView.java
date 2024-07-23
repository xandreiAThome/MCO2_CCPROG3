package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;

import HotelClasses.Hotel;

public class BookReservationView extends JPanel {
    private ArrayList<JButton> hotelListDisplay = new ArrayList<JButton>();
    private JPanel centerPanel;
    private Hotel chosenHotel = null;
    private JPanel chooseDatePanel;
    private JSpinner timeSpinner;

    private JButton returnHomeButton;

    public BookReservationView() {

        this.setLayout(new BorderLayout());

        this.returnHomeButton = new JButton("Home");

        // North panel ///////////////////////////////////
        JLabel label = new JLabel("Book Reservation");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.BLUE);
        northPanel.add(this.returnHomeButton);
        northPanel.add(label);
        ///////////////////////////////////////////////

        // Center panel ///////////////////////////////
        this.centerPanel = new JPanel();
        //////////////////////////////////////////////////

        // Choose Date Panel ///////////////////////////
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.chooseDatePanel = new JPanel();
        this.chooseDatePanel.setLayout(new GridBagLayout());
        this.timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner,
                "HH");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date()); // will only show the current time
        timeSpinner.setPreferredSize(new Dimension(60, 30));
        JPanel pickHour = new JPanel();
        pickHour.add(timeSpinner);
        pickHour.add(new JLabel("24HH"));
        JLabel pickCheckInLabel = new JLabel("Pick Check-in Date");
        pickCheckInLabel.setHorizontalAlignment(JLabel.CENTER);
        this.chooseDatePanel.add(pickCheckInLabel, gbc);
        this.chooseDatePanel.add(pickHour, gbc);

        ///////////////////////////////////////////////

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    public void updateHotelDisplay(ArrayList<Hotel> hotelList) {
        // Very resource wasting process, but cant think of a better solution, will be
        // fine for now
        // done this way so for when editing hotel name is implemented, the option names
        // are also updated
        for (JButton hotelOption : this.hotelListDisplay) {
            this.centerPanel.remove(hotelOption);
        }
        this.hotelListDisplay.clear();
        if (hotelList.size() > 0) {
            for (Hotel hotel : hotelList) {
                this.hotelListDisplay.add(new JButton(hotel.getName()));
            }

            for (JButton hotelDisplay : this.hotelListDisplay) {
                this.centerPanel.add(hotelDisplay);
            }
        }

    }

    public void setActionListener(ActionListener listener) {
        this.returnHomeButton.addActionListener(listener);
    }

    public void dynamicSetActionListenerOfHotelButtons(ActionListener listener) {
        for (JButton button : hotelListDisplay) {
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
}
