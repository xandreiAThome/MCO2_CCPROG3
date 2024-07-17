package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

public class HRSView extends JFrame {
    private JLabel pageName;
    private JPanel homeScreen;
    private JButton goToCreateHotelButton;
    private JButton goToManageHotelButton;
    private JButton goToViewHotelButton;
    private JButton goToBookReservationButton;

    public HRSView() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(1000, 600);

        init();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // Make panels for each of the screens for the HRS,
    // Main, Create Hotel,View Hotel,Manage Hotel
    public void init() {

        // HomeScreen /////////
        homeScreen = new JPanel();
        homeScreen.setLayout(new BorderLayout());
        /////////////////////////

        // North Panel ////////////////////////////////////
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.setBackground(Color.BLUE);
        homeScreen.add(northPanel, BorderLayout.NORTH);

        this.pageName = new JLabel("Hotel Reservation System");
        this.pageName.setForeground(Color.WHITE);
        this.pageName.setFont(new Font("Verdana", Font.BOLD, 20));
        northPanel.add(this.pageName);
        //////////////////////////////////////////////////

        // Central Panel //////////////////////////////////
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridBagLayout());
        centralPanel.setBackground(Color.RED);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.goToCreateHotelButton = new JButton("Create Hotel");
        this.goToManageHotelButton = new JButton("Manage Hotel");
        this.goToViewHotelButton = new JButton("View Hotel");
        this.goToBookReservationButton = new JButton("Book Reservation");
        centralPanel.add(this.goToCreateHotelButton, gbc);
        centralPanel.add(this.goToManageHotelButton, gbc);
        centralPanel.add(this.goToViewHotelButton, gbc);
        centralPanel.add(this.goToBookReservationButton, gbc);
        homeScreen.add(centralPanel, BorderLayout.CENTER);
        ////////////////////////////////////////////////////

        this.add(homeScreen);
    }

    public void setActionListener(ActionListener listener) {
        this.goToCreateHotelButton.addActionListener(listener);
        this.goToManageHotelButton.addActionListener(listener);
        this.goToViewHotelButton.addActionListener(listener);
        this.goToBookReservationButton.addActionListener(listener);

    }

    public JPanel getHomeScreenPanel() {
        return this.homeScreen;
    }
}
