package MVC;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

public class HRSView extends JFrame {
    private JLabel pageName;

    public HRSView() {
        super("Hotel Reservation System");
        setLayout(new CardLayout());
        setSize(1000, 600);

        init();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // Make panels for each of the screens for the HRS,
    // Main, Create Hotel,View Hotel,Manage Hotel
    public void init() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.setBackground(Color.BLUE);
        this.add(northPanel, BorderLayout.NORTH);

        this.pageName = new JLabel("Hotel Reservation System");
        this.pageName.setForeground(Color.WHITE);
        this.pageName.setFont(new Font("Verdana", Font.BOLD, 20));
        northPanel.add(this.pageName);
    }
}
