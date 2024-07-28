package CustomJPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import HotelClasses.RoomClasses.DeluxeRoom;
import HotelClasses.RoomClasses.ExecutiveRoom;
import HotelClasses.RoomClasses.Room;

public class DisplayRoomPanel extends JPanel {
    private JPanel roomCountPanel;
    private JPanel roomListPanel;
    private JLabel standardRoomCountLabel;
    private JLabel deluxeRoomCountLabel;
    private JLabel executiveRoomCountLabel;

    public DisplayRoomPanel() {
        this.setLayout(new BorderLayout());

        roomCountPanel = new JPanel();
        roomCountPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.add(roomCountPanel, BorderLayout.NORTH);

        standardRoomCountLabel = new JLabel("Standard Rooms: 0");
        standardRoomCountLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        roomCountPanel.add(standardRoomCountLabel);

        deluxeRoomCountLabel = new JLabel("Deluxe Rooms: 0");
        deluxeRoomCountLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        roomCountPanel.add(deluxeRoomCountLabel);

        executiveRoomCountLabel = new JLabel("Executive Rooms: 0");
        executiveRoomCountLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        roomCountPanel.add(executiveRoomCountLabel);

        roomListPanel = new JPanel();
        roomListPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.add(roomListPanel, BorderLayout.CENTER);

        JLabel legendLabel = new JLabel(
                "Legend: White - Standard Room    Purple - Deluxe Room    Gold - Executive Room");
        legendLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(legendLabel, BorderLayout.SOUTH);
    }

    public void updateRoomCounts(ArrayList<Room> rooms) {
        int standardRoomCount = 0;
        int deluxeRoomCount = 0;
        int executiveRoomCount = 0;

        for (Room room : rooms) {
            if (room instanceof ExecutiveRoom) {
                executiveRoomCount++;
            } else if (room instanceof DeluxeRoom) {
                deluxeRoomCount++;
            } else {
                standardRoomCount++;
            }
        }

        standardRoomCountLabel.setText("Standard Rooms: " + standardRoomCount);
        deluxeRoomCountLabel.setText("Deluxe Rooms: " + deluxeRoomCount);
        executiveRoomCountLabel.setText("Executive Rooms: " + executiveRoomCount);
    }

    public void updateRoomList(ArrayList<Room> rooms) {
        roomListPanel.removeAll();

        for (Room room : rooms) {
            JButton roomButton = new JButton(room.getName());
            if (room instanceof ExecutiveRoom) {
                roomButton.setBackground(Color.decode("#FFCF40"));
            } else if (room instanceof DeluxeRoom) {
                roomButton.setBackground(Color.decode("#5D3FD3"));
                roomButton.setForeground(Color.WHITE);
            }
            roomButton.setPreferredSize(new Dimension(148, 30));
            roomListPanel.add(roomButton);
        }

        roomListPanel.revalidate();
        roomListPanel.repaint();
    }
}
