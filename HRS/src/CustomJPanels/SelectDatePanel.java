package CustomJPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class SelectDatePanel extends JPanel {
    private JSpinner checkInTimeSpinner;
    private JSpinner checkInDaySpinner;
    private JSpinner checkOutTimeSpinner;
    private JSpinner checkOutDaySpinner;
    private JButton button;
    private JPanel wrapper;

    public SelectDatePanel(String buttonAction, Color fontColor, Color backgroundColor) {
        wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        checkInTimeSpinner = new JSpinner(new SpinnerNumberModel(12, 0, 23, 1));
        checkInTimeSpinner.setPreferredSize(new Dimension(60, 30));
        checkInDaySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        checkInDaySpinner.setPreferredSize(new Dimension(60, 30));
        checkOutTimeSpinner = new JSpinner(new SpinnerNumberModel(12, 0, 23, 1));
        checkOutTimeSpinner.setPreferredSize(new Dimension(60, 30));
        checkOutDaySpinner = new JSpinner(new SpinnerNumberModel(2, 2, 31, 1));
        checkOutDaySpinner.setPreferredSize(new Dimension(60, 30));

        JPanel checkInContainer = new JPanel();
        checkInContainer.setOpaque(false);
        JPanel checkOutContainer = new JPanel();
        checkOutContainer.setOpaque(false);
        JLabel pickHourIn = new JLabel("24HH");
        JLabel pickHourOut = new JLabel("24HH");
        pickHourIn.setForeground(fontColor);
        pickHourOut.setForeground(fontColor);
        JLabel checkInHourFormatLabel = pickHourIn;
        JLabel checkOutHourFormatLabel = pickHourOut;
        checkInHourFormatLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
        checkOutHourFormatLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
        checkInContainer.add(checkInTimeSpinner);
        checkInContainer.add(checkInHourFormatLabel);
        checkInContainer.add(checkInDaySpinner);
        JLabel pickDayIn = new JLabel("Pick Day");
        pickDayIn.setForeground(fontColor);
        checkInContainer.add(pickDayIn);

        checkOutContainer.add(checkOutTimeSpinner);
        checkOutContainer.add(checkOutHourFormatLabel);
        checkOutContainer.add(checkOutDaySpinner);
        JLabel pickDayOut = new JLabel("Pick Day");
        pickDayOut.setForeground(fontColor);
        checkOutContainer.add(pickDayOut);
        JLabel pickCheckInLabel = new JLabel("Pick Check-in Date");
        JLabel pickCheckOutLabel = new JLabel("Pick Check-out Date");
        pickCheckInLabel.setForeground(fontColor);
        pickCheckOutLabel.setForeground(fontColor);
        pickCheckInLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        pickCheckOutLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        pickCheckInLabel.setHorizontalAlignment(JLabel.CENTER);
        pickCheckOutLabel.setHorizontalAlignment(JLabel.CENTER);
        pickCheckOutLabel.setBorder(new EmptyBorder(30, 0, 0, 0));
        wrapper.add(pickCheckInLabel, gbc);
        wrapper.add(checkInContainer, gbc);
        wrapper.add(pickCheckOutLabel, gbc);
        wrapper.add(checkOutContainer, gbc);
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBorder(new EmptyBorder(30, 0, 0, 0));
        buttonContainer.setOpaque(false);
        button = new JButton(buttonAction);
        buttonContainer.add(button);
        wrapper.add(buttonContainer, gbc);

        this.add(wrapper, BorderLayout.CENTER);
    }

    public JButton getButton() {
        return this.button;
    }

    public void setActionListener(ActionListener listener) {
        button.addActionListener(listener);
    }

    public int getCheckInDay() {
        return (Integer) this.checkInDaySpinner.getValue();
    }

    public int getCheckOutDay() {
        return (Integer) this.checkOutDaySpinner.getValue();
    }

    public int getCheckInHour() {
        return (Integer) this.checkInTimeSpinner.getValue();
    }

    public int getCheckOutHour() {
        return (Integer) this.checkOutTimeSpinner.getValue();
    }

    /**
     * Call in the resetEntries method of the main View class
     */
    public void resetEntries() {
        checkInDaySpinner.setValue(1);
        checkOutDaySpinner.setValue(2);
        checkInTimeSpinner.setValue(12);
        checkOutTimeSpinner.setValue(12);
    }

    public JPanel getWrapper() {
        return wrapper;
    }

}
