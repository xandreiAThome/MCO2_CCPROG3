package CustomJPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import HotelClasses.Hotel;

public class SelectHotelPanel extends JPanel {
    private ArrayList<JButton> hotelListButtons = new ArrayList<JButton>();

    public SelectHotelPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.setLayout(new GridBagLayout());
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
        for (JButton hotelOption : this.hotelListButtons) {
            this.remove(hotelOption);
        }
        this.hotelListButtons.clear();
        if (hotelList.size() > 0) {
            for (Hotel hotel : hotelList) {
                this.hotelListButtons.add(new JButton(hotel.getName()));
            }

            for (JButton hotelDisplay : this.hotelListButtons) {
                this.add(hotelDisplay, gbc);
            }
        }
    }

    public void dynamicSetActionListenerOfHotelButtons(ActionListener listener) {
        for (JButton button : hotelListButtons) {
            button.addActionListener(listener);
        }
    }

    public ArrayList<JButton> getHotelListButtons() {
        return hotelListButtons;
    }
}
