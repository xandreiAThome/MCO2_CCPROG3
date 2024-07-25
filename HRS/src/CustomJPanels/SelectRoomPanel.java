package CustomJPanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import HotelClasses.RoomClasses.Room;

public class SelectRoomPanel extends JPanel {
    private JPanel chooseRoomButtonContainer;
    private ArrayList<JButton> roomListButtons = new ArrayList<JButton>();

    public SelectRoomPanel() {
        this.setLayout(new BorderLayout());

        JLabel chooseRoomLabel = new JLabel("Choose Room");
        chooseRoomLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseRoomLabel.setBorder(new EmptyBorder(20, 0, 80, 0));
        chooseRoomLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(chooseRoomLabel, BorderLayout.NORTH);
        chooseRoomButtonContainer = new JPanel();
        chooseRoomButtonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.add(chooseRoomButtonContainer, BorderLayout.CENTER);
    }

    /**
     * Call when the selectRoomPanel will be displayed
     * 
     * @param listener
     */
    public void dynamicSetActionListenerOfHotelButtons(ActionListener listener) {
        for (JButton button : roomListButtons) {
            button.addActionListener(listener);
        }
    }

    /**
     * Call when the selectRoomPanel will be displayed
     * 
     * @param rooms
     */
    public void updateRoomListButtons(ArrayList<Room> rooms) {
        for (JButton roomOption : this.roomListButtons) {
            this.chooseRoomButtonContainer.remove(roomOption);
        }
        this.roomListButtons.clear();

        for (Room room : rooms) {
            JButton temp = new JButton(room.getName());
            temp.setPreferredSize(new Dimension(148, 30));
            this.roomListButtons.add(temp);
        }

        for (JButton button : this.roomListButtons) {
            this.chooseRoomButtonContainer.add(button);
        }
    }

    public ArrayList<JButton> getRoomListButtons() {
        return this.roomListButtons;
    }

}
