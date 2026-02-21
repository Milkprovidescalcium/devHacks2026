
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;


public class ColorfulPanel extends JPanel {
    private Color color1, color2, color3;

    public ColorfulPanel(Color c1, Color c2, Color c3) {
        this.color1 = c1;
        this.color2 = c2;
        this.color3 = c3;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Create a 3-color vertical gradient from top to bottom
        float[] fractions = {0.f, 0.5f, 1.0f};
        Color[] colors = {color1, color2, color3};
        LinearGradientPaint lgp = new LinearGradientPaint(0f, 0f, 0f, (float) height, fractions, colors);
        g2d.setPaint(lgp);
        g2d.fillRect(0, 0, width, height); // Fill the entire panel with the gradient
    }
}
