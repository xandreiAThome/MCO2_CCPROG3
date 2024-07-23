package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

import ModifiedClasses.NumberFormatterChild;

import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class CreateHotelView extends JPanel {
    private JButton returnHomeButton;
    private JTextField hotelNameField;
    private JFormattedTextField roomQuantiField;
    private JButton submitButton;

    public CreateHotelView() {
        this.setLayout(new BorderLayout());

        // North Panel /////////////////
        this.returnHomeButton = new JButton("Home");

        JLabel label = new JLabel("Create Hotel");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.BLUE);
        northPanel.add(this.returnHomeButton);
        northPanel.add(label);

        this.add(northPanel, BorderLayout.NORTH);
        /////////////////////////////////

        // Center Panel //////////////
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.hotelNameField = new JTextField(16);
        JLabel enterHotelName = new JLabel("Enter Hotel Name");
        enterHotelName.setHorizontalAlignment(JLabel.CENTER);
        enterHotelName.setFont(new Font("Verdana", Font.BOLD, 16));

        this.submitButton = new JButton("create");

        NumberFormat longFormat = NumberFormat.getIntegerInstance();

        JPanel roomQuantiPanel = new JPanel();
        JLabel roomQuantiLabel = new JLabel("Enter Number of Rooms");
        NumberFormatter numberFormatter = new NumberFormatterChild(longFormat);
        numberFormatter.setValueClass(Long.class); // optional, ensures you will always get a long value
        numberFormatter.setAllowsInvalid(false); // this is the key!!
        numberFormatter.setMinimum(1l); // Optional
        roomQuantiField = new JFormattedTextField(numberFormatter);
        roomQuantiField.setColumns(6);
        roomQuantiPanel.add(roomQuantiLabel);
        roomQuantiPanel.add(roomQuantiField);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(enterHotelName, gbc);
        centerPanel.add(this.hotelNameField, gbc);
        centerPanel.add(roomQuantiPanel, gbc);
        centerPanel.add(submitButton, gbc);

        this.add(centerPanel, BorderLayout.CENTER);
        //////////////////////////////
    }

    public void setActionListener(ActionListener listener) {
        this.returnHomeButton.addActionListener(listener);
        this.submitButton.addActionListener(listener);
    }

    public JTextField getRoomQuantiField() {
        return this.roomQuantiField;
    }

    public JTextField getHotelNameField() {
        return this.hotelNameField;
    }

    public void resetTextFields() {
        this.hotelNameField.setText("");
        this.roomQuantiField.setText("");
    }
}
