package CustomJPanels;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * This class extends the SelectDatePanel class and represents a custom panel
 * for selecting a date with a discount feature.
 * It provides a text field for entering a discount code.
 * 
 * The class constructor takes parameters for the button action, font color, and
 * background color.
 * 
 * The class also overrides the resetEntries() method to reset the date
 * selection and discount text field.
 */
public class SelectDatePanelWithDiscount extends SelectDatePanel {
    private JTextField discountTextField;

    public SelectDatePanelWithDiscount(String buttonAction, Color fontColor, Color backgroundColor) {
        super(buttonAction, fontColor, backgroundColor);

        JPanel textFieldContainer = new JPanel();
        JLabel discountLabel = new JLabel("Enter Discount Code");
        discountLabel.setForeground(fontColor);
        textFieldContainer.add(discountLabel);
        textFieldContainer.setBorder(new EmptyBorder(20, 0, 0, 0));
        textFieldContainer.setOpaque(false);

        discountTextField = new JTextField();
        discountTextField.setColumns(10);
        textFieldContainer.add(discountTextField);

        super.getWrapper().add(textFieldContainer);
    }

    public JTextField getDiscountTextField() {
        return this.discountTextField;
    }

    @Override
    public void resetEntries() {
        super.resetEntries();
        discountTextField.setText("");
    }

}
