package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  


public class ManageHotelView extends JPanel {
    private JButton returnHomeButton;

    public ManageHotelView() {
        this.setLayout(new BorderLayout());

        this.returnHomeButton = new JButton("Home");

        JLabel label = new JLabel("Manage Hotel");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.WHITE);
        northPanel.add(this.returnHomeButton);
        northPanel.add(label);

        this.add(northPanel);

        JPanel centralPanel = new JPanel();

    }

    public void setActionListener(ActionListener listener) {
        this.returnHomeButton.addActionListener(listener);
    }

}
