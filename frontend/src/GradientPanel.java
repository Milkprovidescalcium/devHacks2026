import javax.swing.*;
import java.awt.*;
import java.awt.AlphaComposite; //making quares disappear.
import java.util.ArrayList;
import java.util.List;
import java.util.Random; //for sqares

public class GradientPanel extends JPanel {
    private Color color1, color2;
    private List<Square> squares = new ArrayList<>();
    private Random rand = new Random();

    public GradientPanel(Color c1, Color c2) {
        this.color1 = c1;
        this.color2 = c2;
        setLayout(new GridBagLayout());

        // squares mover
        Timer squareMove = new Timer(5, e -> {
            updateSquares();
            repaint();
        });
        animationTimer.start();

        // square spawner
        Timer squareSpawn = new Timer(50, e -> spawnSquare());
        spawnTimer.start();
    }

    private void squareType() {
        int size = rand.nextInt(30) + 10; // random size 10-40
        int x = rand.nextInt(Math.max(1, getWidth() - size)); // randomized positioning
        squares.add(new Square(x, getHeight(), size));
    }

    private void updateSquares() {
        List<Square> toRemove = new ArrayList<>();
        for (Square square : squares) {
            square.y -= square.speed;       // float upward
            square.size -= 0.5f;        // shrink
            square.alpha -= 0.05f;     // fade out

            // remove if invisible or too small
            if (sq.alpha <= 0 || sq.size <= 0) {
                toRemove.add(square);
            }
        }
        squares.removeAll(toRemove);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw gradient background
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw each square
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Square square : squares) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.max(0, square.alpha)));
            g2d.setColor(Color.WHITE);
            g2d.fillRect((int) square.x, (int) square.y, (int) square.size, (int) square.size);
        }

        // Reset composite so other components draw normally
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    // Square Tracker
    private static class Square {
        float x, y, size, alpha;
        float speed;

        public Square(float x, float y, float size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.alpha = 0.6f;                      // initial opacity
            this.speed = (float) (Math.random() * 1.5) + 3.0f; // FASTER THAN LIGHT!
        }
    }
}
