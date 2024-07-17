package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import HotelClasses.Hotel;

public class BookReservationView extends JPanel {
    private ArrayList<JButton> hotelListDisplay = new ArrayList<JButton>();
    private JPanel centerPanel;

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

    public ArrayList<JButton> getHotelListDisplayOptions() {
        return this.hotelListDisplay;
    }
}
