package CustomJPanels;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel {

    private Image backgroundImage;

    // Some code to initialize the background image.
    // Here, we use the constructor to load the image. This
    // can vary depending on the use case of the panel.
    public JPanelWithBackground(String fileName) throws IOException {
        backgroundImage = ImageIO.read(new File(fileName));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image scaled = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

        // Draw the background image.
        g.drawImage(scaled, 0, 0, this);
    }
}
