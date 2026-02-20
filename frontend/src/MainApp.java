import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class MainApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hocus Focus");
        java.net.URL iconUrl = MainApp.class.getResource("/icon.png");
        ImageIcon icon;
        if (iconUrl != null) {
            icon = new ImageIcon(iconUrl);
        } else {
            icon = new ImageIcon("C:\\Users\\aaron\\OneDrive\\Desktop\\devHacks2026-main\\frontend\\src\\icon.png");
            System.err.println("Icon not found on classpath; using fallback path.");
        }
        frame.setIconImage(icon.getImage());
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ColorfulPanel(
                java.awt.Color.BLUE,
                java.awt.Color.PINK
        ));

        frame.setVisible(true);
    }
}


