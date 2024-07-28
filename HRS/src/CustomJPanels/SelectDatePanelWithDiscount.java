package CustomJPanels;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
