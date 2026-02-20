
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;


public class ColorfulPanel extends JPanel {
    private Color color1, color2;

    public ColorfulPanel(Color c1, Color c2) {
        this.color1 = c1;
        this.color2 = c2;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Create a gradient paint from top to bottom
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height); // Fill the entire panel with the gradient
    }
}