package CustomJPanels;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SelectDatePanelWithDiscount extends SelectDatePanel {
    private JTextField discountTextField;

    public SelectDatePanelWithDiscount(String buttonAction) {
        super(buttonAction);

        JPanel textFieldContainer = new JPanel();
        textFieldContainer.add(new JLabel("Enter Discount Code"));
        textFieldContainer.setBorder(new EmptyBorder(20, 0, 0, 0));

        discountTextField = new JTextField();
        discountTextField.setColumns(10);
        textFieldContainer.add(discountTextField);

        this.add(textFieldContainer);
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
