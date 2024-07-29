package CustomJPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import HotelClasses.Hotel;

/**
 * A custom JPanel that displays a list of hotels and allows the user to select
 * one.
 */
public class SelectHotelPanel extends JPanel {
    private ArrayList<JButton> hotelListButtons = new ArrayList<JButton>();
    private JPanel wrapper;

    /**
     * Constructs a SelectHotelPanel with the specified background color.
     * 
     * @param backgroundColor the background color of the panel
     */
    public SelectHotelPanel(Color backgroundColor) {
        wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.add(wrapper, BorderLayout.CENTER);
    }

    /**
     * Updates the display of hotels in the panel.
     * Call when the parent panel is shown
     * 
     * @param hotelList the list of hotels to be displayed
     */
    public void updateHotelDisplay(ArrayList<Hotel> hotelList) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        wrapper.removeAll();
        this.hotelListButtons.clear();
        if (hotelList.size() > 0) {
            for (Hotel hotel : hotelList) {
                this.hotelListButtons.add(new JButton(hotel.getName()));
            }

            for (JButton hotelDisplay : this.hotelListButtons) {
                wrapper.add(hotelDisplay, gbc);
            }
        }

        if (hotelList.size() == 0) {
            JLabel noAvaiLabel = new JLabel("No Hotels added yet");
            noAvaiLabel.setForeground(Color.white);
            noAvaiLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            wrapper.add(noAvaiLabel);
        }
    }

    /**
     * Sets the action listener for the hotel buttons.
     * Call when the parent panel is shown
     * 
     * @param listener the action listener to be set
     */
    public void dynamicSetActionListenerOfHotelButtons(ActionListener listener) {
        for (JButton button : hotelListButtons) {
            button.addActionListener(listener);
        }
    }

    /**
     * Returns the list of hotel buttons.
     * 
     * @return the list of hotel buttons
     */
    public ArrayList<JButton> getHotelListButtons() {
        return hotelListButtons;
    }
}
