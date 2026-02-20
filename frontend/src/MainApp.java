import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainApp {
    
    static List<JFrame> frames = new ArrayList<>();
       public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> 
        launchWindows()); // <-- calls the method
       }

    private static void launchWindows() {

            String[] messages = {
                "Take it deep.",
                "Such a good boy.",
                "One inch at a time.",
                "Yummy Cummy!",
                "Stay on gooning!",
                "Open wide!",
                "Deep breaths.",
                "Keep going!",
                "I'm almost there!",
                "I'm ARRIVING!!!."
            };

            java.util.Collections.shuffle(java.util.Arrays.asList(messages));

            Random rand = new Random();
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int) screen.getWidth();
            int screenHeight = (int) screen.getHeight();

            int w1 = rand.nextInt(300) + 300;
            int h1 = rand.nextInt(200) + 200;
            int w2 = rand.nextInt(300) + 300;
            int h2 = rand.nextInt(200) + 200;
            int w3 = rand.nextInt(300) + 300;
            int h3 = rand.nextInt(200) + 200;

            JLabel timerLabel = new JLabel("Closing in 10...");
            timerLabel.setForeground(Color.WHITE);
            timerLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));
            timerLabel.setHorizontalAlignment(SwingConstants.CENTER);

            WindowManager.showWindow(messages[0], new Color(255, 100, 100), new Color(255, 180, 50), rand.nextInt(screenWidth - w1), rand.nextInt(screenHeight - h1), w1, h1, timerLabel, frames);
            WindowManager.showWindow(messages[1], new Color(100, 180, 255), new Color(150, 80, 255), rand.nextInt(screenWidth - w2), rand.nextInt(screenHeight - h2), w2, h2, timerLabel, frames);
            WindowManager.showWindow(messages[2], new Color(100, 220, 150), new Color(50, 150, 255), rand.nextInt(screenWidth - w3), rand.nextInt(screenHeight - h3), w3, h3, timerLabel, frames);

            WindowManager.startCountdown(timerLabel, 10, frames);
        };
    }
