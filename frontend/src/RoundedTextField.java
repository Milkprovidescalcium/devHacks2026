import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class RoundedTextField extends JTextField {
    private int radius;
    private String placeholder;

    public RoundedTextField(int columns, int radius, String placeholder) {
        super(columns);
        this.radius = radius;
        this.placeholder = placeholder;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        setFont(new Font("Arial", Font.PLAIN, 14));
        setForeground(Color.DARK_GRAY);
        setCaretColor(Color.GRAY);
        setHorizontalAlignment(JTextField.CENTER);

       getCaret().setBlinkRate(0); // stops blinking but keeps it visible

        // Force repaint on focus change so placeholder appears/disappears
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));

        super.paintComponent(g);

        if (getText().isEmpty() && !isFocusOwner()) {
            FontMetrics fm = getFontMetrics(getFont());
            int x = getInsets().left;
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

            g2.setFont(getFont());
            g2.setColor(new Color(180, 180, 180));
            g2.drawString(placeholder, x, y);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(220, 220, 220));
        g2.setStroke(new BasicStroke(1.5f));
        g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, radius, radius));

        g2.dispose();
    }

}
