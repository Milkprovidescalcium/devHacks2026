import javax.swing.*;
import java.awt.*;
import java.awt.AlphaComposite; //making quares disappear.
import java.util.ArrayList;
import java.util.List;
import java.util.Random; //for sqares

public class GradientPanel extends JPanel {
    private Color color1, color2;

    public GradientPanel(Color c1, Color c2) {
        this.color1 = c1;
        this.color2 = c2;
        setLayout(new GridBagLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw gradient background
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
