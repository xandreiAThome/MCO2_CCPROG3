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
    private JFormattedTextField roomQuantiField, deluxeQuantiField, executiveQuantiField;
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

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();

        // Total rooms
        JPanel roomQuantiPanel = new JPanel();
        JLabel roomQuantiLabel = new JLabel("Enter Total number of Rooms");
        NumberFormatter numberFormatter = new NumberFormatterChild(integerFormat);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setMinimum(1);
        roomQuantiField = new JFormattedTextField(numberFormatter);
        roomQuantiField.setColumns(6);
        roomQuantiPanel.add(roomQuantiLabel);
        roomQuantiPanel.add(roomQuantiField);

        // How many deluxe rooms
        JPanel deluxeQuantiPanel = new JPanel();
        JLabel deluxeQuantiLabel = new JLabel("How many are Deluxe Rooms?");
        NumberFormatter deluxeNumberFormatter = new NumberFormatterChild(integerFormat);
        deluxeNumberFormatter.setAllowsInvalid(false);
        deluxeNumberFormatter.setMinimum(0);
        deluxeQuantiField = new JFormattedTextField(deluxeNumberFormatter);
        deluxeQuantiField.setColumns(6);
        deluxeQuantiPanel.add(deluxeQuantiLabel);
        deluxeQuantiPanel.add(deluxeQuantiField);

        // How many Executive rooms
        JPanel executiveQuantiPanel = new JPanel();
        JLabel executiveQuantiLabel = new JLabel("How many are Deluxe Rooms?");
        NumberFormatter executiveNumberFormatter = new NumberFormatterChild(integerFormat);
        executiveNumberFormatter.setAllowsInvalid(false);
        executiveNumberFormatter.setMinimum(0);
        executiveQuantiField = new JFormattedTextField(executiveNumberFormatter);
        executiveQuantiField.setColumns(6);
        executiveQuantiPanel.add(executiveQuantiLabel);
        executiveQuantiPanel.add(executiveQuantiField);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(enterHotelName, gbc);
        centerPanel.add(this.hotelNameField, gbc);
        centerPanel.add(roomQuantiPanel, gbc);
        centerPanel.add(deluxeQuantiPanel, gbc);
        centerPanel.add(executiveQuantiPanel, gbc);
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

    public JTextField getDeluxeQuantiField() {
        return this.deluxeQuantiField;
    }

    public JTextField getExecutiveQuantiField() {
        return this.executiveQuantiField;
    }

    public JTextField getHotelNameField() {
        return this.hotelNameField;
    }

    public void resetTextFields() {
        this.hotelNameField.setText("");
        this.roomQuantiField.setText("");
        this.deluxeQuantiField.setText("");
        this.executiveQuantiField.setText("");
    }
}
