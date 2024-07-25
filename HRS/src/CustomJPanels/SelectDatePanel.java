package CustomJPanels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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

    public SelectDatePanel(String buttonAction) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.setLayout(new GridBagLayout());
        checkInTimeSpinner = new JSpinner(new SpinnerNumberModel(12, 0, 23, 1));
        checkInTimeSpinner.setPreferredSize(new Dimension(60, 30));
        checkInDaySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        checkInDaySpinner.setPreferredSize(new Dimension(60, 30));
        checkOutTimeSpinner = new JSpinner(new SpinnerNumberModel(12, 0, 23, 1));
        checkOutTimeSpinner.setPreferredSize(new Dimension(60, 30));
        checkOutDaySpinner = new JSpinner(new SpinnerNumberModel(2, 2, 31, 1));
        checkOutDaySpinner.setPreferredSize(new Dimension(60, 30));

        JPanel checkInContainer = new JPanel();
        JPanel checkOutContainer = new JPanel();
        JLabel checkInHourFormatLabel = new JLabel("24HH");
        JLabel checkOutHourFormatLabel = new JLabel("24HH");
        checkInHourFormatLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
        checkOutHourFormatLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
        checkInContainer.add(checkInTimeSpinner);
        checkInContainer.add(checkInHourFormatLabel);
        checkInContainer.add(checkInDaySpinner);
        checkInContainer.add(new JLabel("Pick Day"));

        checkOutContainer.add(checkOutTimeSpinner);
        checkOutContainer.add(checkOutHourFormatLabel);
        checkOutContainer.add(checkOutDaySpinner);
        checkOutContainer.add(new JLabel("Pick Day"));
        JLabel pickCheckInLabel = new JLabel("Pick Check-in Date");
        JLabel pickCheckOutLabel = new JLabel("Pick Check-out Date");
        pickCheckInLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        pickCheckOutLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        pickCheckInLabel.setHorizontalAlignment(JLabel.CENTER);
        pickCheckOutLabel.setHorizontalAlignment(JLabel.CENTER);
        pickCheckOutLabel.setBorder(new EmptyBorder(30, 0, 0, 0));
        this.add(pickCheckInLabel, gbc);
        this.add(checkInContainer, gbc);
        this.add(pickCheckOutLabel, gbc);
        this.add(checkOutContainer, gbc);
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBorder(new EmptyBorder(30, 0, 0, 0));
        button = new JButton(buttonAction);
        buttonContainer.add(button);
        this.add(buttonContainer, gbc);
    }

    public JButton getButton() {
        return this.button;
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

    public void resetEntries() {
        checkInDaySpinner.setValue(1);
        checkOutDaySpinner.setValue(2);
        checkInTimeSpinner.setValue(12);
        checkOutTimeSpinner.setValue(12);
    }

}
