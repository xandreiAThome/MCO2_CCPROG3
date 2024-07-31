
package CustomJPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * This class represents a custom JPanel that displays room prices.
 * It extends the JPanel class and provides methods to update the prices.
 */
public class DisplayPrices extends JPanel {
    private JPanel wrapper;
    private JLabel standardPriceLabel;
    private JLabel deluxePriceLabel;
    private JLabel executivePriceLabel;

    public DisplayPrices() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(0, 0, 0, 0));
        wrapper.setBorder(new EmptyBorder(20, 20, 20, 20)); // added border for spacing

        JLabel roomPriceLabel = new JLabel("Room Prices");
        roomPriceLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        roomPriceLabel.setForeground(Color.BLACK);
        roomPriceLabel.setHorizontalAlignment(JLabel.CENTER);
        wrapper.add(roomPriceLabel, BorderLayout.NORTH);

        JPanel priceContainer = new JPanel(new GridBagLayout());
        priceContainer.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);

        standardPriceLabel = new JLabel("Standard Room: $0.0");
        standardPriceLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        standardPriceLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        priceContainer.add(standardPriceLabel, gbc);

        deluxePriceLabel = new JLabel("Deluxe Room: $0.0");
        deluxePriceLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        deluxePriceLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        priceContainer.add(deluxePriceLabel, gbc);

        executivePriceLabel = new JLabel("Executive Room: $0.0");
        executivePriceLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        executivePriceLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        priceContainer.add(executivePriceLabel, gbc);

        wrapper.add(priceContainer, BorderLayout.CENTER);

        this.add(wrapper, BorderLayout.CENTER);
    }

    /**
     * Updates the prices displayed in the panel
     * 
     * @param standardPrice
     */
    public void updatePrices(double standardPrice) {
        double deluxePrice = standardPrice * 0.2 + standardPrice;
        double executivePrice = standardPrice * 0.35 + standardPrice;

        standardPriceLabel.setText("Standard Room: $" + String.format("%.2f", standardPrice));
        deluxePriceLabel.setText("Deluxe Room: $" + String.format("%.2f", deluxePrice));
        executivePriceLabel.setText("Executive Room: $" + String.format("%.2f", executivePrice));
    }
}