package CustomJPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import HotelClasses.Date;
import HotelClasses.RoomClasses.DeluxeRoom;
import HotelClasses.RoomClasses.ExecutiveRoom;
import HotelClasses.RoomClasses.Room;

public class SelectRoomPanel extends JPanel {
    private JPanel chooseRoomButtonContainer;
    private ArrayList<JButton> roomListButtons = new ArrayList<JButton>();

    private JPanel wrapper;

    public SelectRoomPanel(String label, Color fontColor, Color backgroundColor) {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(backgroundColor);

        JLabel chooseRoomLabel = new JLabel(label);
        chooseRoomLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        chooseRoomLabel.setBorder(new EmptyBorder(20, 0, 80, 0));
        chooseRoomLabel.setForeground(fontColor);
        chooseRoomLabel.setHorizontalAlignment(JLabel.CENTER);
        wrapper.add(chooseRoomLabel, BorderLayout.NORTH);
        chooseRoomButtonContainer = new JPanel();
        chooseRoomButtonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        chooseRoomButtonContainer.setOpaque(false);
        wrapper.add(chooseRoomButtonContainer, BorderLayout.CENTER);
        JLabel legendLabel = new JLabel(
                "Legend: White - Standard Room    Purple - Deluxe Room    Gold - Executive Room");
        legendLabel.setHorizontalAlignment(JLabel.CENTER);
        legendLabel.setForeground(fontColor);
        wrapper.add(legendLabel, BorderLayout.SOUTH);

        this.add(wrapper, BorderLayout.CENTER);
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
            if (room instanceof ExecutiveRoom) {

                temp.setPreferredSize(new Dimension(148, 30));
                temp.setBackground(Color.decode("#FFCF40"));
                this.roomListButtons.add(temp);
            } else if (room instanceof DeluxeRoom) {
                temp.setPreferredSize(new Dimension(148, 30));
                temp.setBackground(Color.decode("#5D3FD3"));
                temp.setForeground(Color.WHITE);
                this.roomListButtons.add(temp);
            } else {
                temp.setPreferredSize(new Dimension(148, 30));
                this.roomListButtons.add(temp);
            }

        }

        for (JButton button : this.roomListButtons) {
            this.chooseRoomButtonContainer.add(button);
        }
    }

    /**
     * Call when the selectRoomPanel will be displayed
     * 
     * @param rooms
     */
    public void updateRoomListButtonsAvailableGivenDate(ArrayList<Room> rooms, Date checkIn, Date checkOut) {
        for (JButton roomOption : this.roomListButtons) {
            this.chooseRoomButtonContainer.remove(roomOption);
        }
        this.roomListButtons.clear();

        for (Room room : rooms) {
            JButton temp = new JButton(room.getName());
            if (room instanceof ExecutiveRoom && room.isRoomAvailableGivenDate(checkIn, checkOut)) {
                temp.setPreferredSize(new Dimension(148, 30));
                temp.setBackground(Color.decode("#FFCF40"));
                this.roomListButtons.add(temp);
            } else if (room instanceof DeluxeRoom && room.isRoomAvailableGivenDate(checkIn, checkOut)) {
                temp.setPreferredSize(new Dimension(148, 30));
                temp.setBackground(Color.decode("#5D3FD3"));
                temp.setForeground(Color.WHITE);
                this.roomListButtons.add(temp);
            } else if (room.isRoomAvailableGivenDate(checkIn, checkOut)) {
                temp.setPreferredSize(new Dimension(148, 30));
                this.roomListButtons.add(temp);
            }

        }

        for (JButton button : this.roomListButtons) {
            this.chooseRoomButtonContainer.add(button);
        }
    }

    public ArrayList<JButton> getRoomListButtons() {
        return this.roomListButtons;
    }

}
