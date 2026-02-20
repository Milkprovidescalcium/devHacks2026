import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WindowManager {

    public static void showWindow(String message, Color c1, Color c2, int x, int y, int width, int height, JLabel timerLabel, List<JFrame> frames) {
        JFrame frame = new JFrame("Hocus Focus");
        frame.setSize(width, height);
        frame.setLocation(x, y);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        GradientPanel panel = new GradientPanel(c1, c2);

        JLabel label = new JLabel("<html><div style='text-align:center;'>" + message + "</div></html>");
        label.setForeground(Color.WHITE);
        label.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton closeAll = new JButton("Close All");
        closeAll.setOpaque(true);
        closeAll.setBorderPainted(false);
        closeAll.setBackground(Color.WHITE);
        closeAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeAll.addActionListener(e -> closeAllWindows(frames));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(label, gbc);

        gbc.gridy = 1;
        panel.add(closeAll, gbc);

        gbc.gridy = 2;
        panel.add(timerLabel, gbc);

        frame.add(panel);
        frame.setVisible(true);
        frames.add(frame);
    }

    public static void startCountdown(JLabel timerLabel, int seconds, List<JFrame> frames) {
        Timer timer = new Timer(1000, null);
        final int[] count = {seconds};

        timer.addActionListener(e -> {
            count[0]--;
            timerLabel.setText("Closing in " + count[0] + "...");

            if (count[0] <= 0) {
                timer.stop();
                closeAllWindows(frames);
            }
        });

        timer.start();
    }

    public static void closeAllWindows(List<JFrame> frames) {
        for (JFrame frame : frames) {
            frame.dispose();
        }
        frames.clear();
        System.exit(0);
    }
}
