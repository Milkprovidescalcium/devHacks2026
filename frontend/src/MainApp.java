import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.io.*;
import java.nio.channels.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainApp {
    private static FileOutputStream lockStream;
    private static FileChannel lockChannel;
    private static FileLock lock;
    private static String soundFilePath = "/alert";
    private static String currentGoal = "";
    private static Timer focusTimer;

    public static void main(String[] args) {
        if (!acquireLock()) {
            JOptionPane.showMessageDialog(null,
                    "Hocus Focus is already running!",
                    "Already Running",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }

        JFrame frame = new JFrame("Hocus Focus");

        URL iconUrl = MainApp.class.getResource("/icon.png");
        if (iconUrl != null) {
            frame.setIconImage(new ImageIcon(iconUrl).getImage());
        } else {
            System.err.println("Warning: icon.png not found on classpath.");
        }

        final JLabel title = new JLabel("Hocus Focus", SwingConstants.CENTER);
        final RoundedTextField textField = new RoundedTextField(20, 30, "What are we hocusing focusing on?");
        textField.setHorizontalAlignment(JTextField.LEFT);

        int delay = 10000;
        focusTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Timer triggered, checking focus...");
                if (!currentGoal.isEmpty()) {
                    writeGoal(currentGoal);
                    readVerdict();
                }
            }
        });
        
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();

                if (inputText != null && !inputText.trim().isEmpty()) {
                    // FIX 1: Actually assign to currentGoal
                    currentGoal = "GOAL: " + inputText.toUpperCase();
                    writeGoal(currentGoal); // Write immediately on goal set
                    if (!focusTimer.isRunning()) {
                        focusTimer.start(); // FIX 2: Use focusTimer, avoid restarting if already running
                    }
                }
                textField.setText("");
            }
        });

        ColorfulPanel content = new ColorfulPanel(Color.BLUE, Color.MAGENTA, Color.PINK);
        content.setLayout(new GridBagLayout()); // Center everything

        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.WHITE);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        // textField.setMaximumSize(textField.getPreferredSize()); // Prevent stretching

        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15))); // spacing
        centerPanel.add(textField);

        content.add(centerPanel); // GridBagLayout centers it by default
        content.setFocusable(true);
        frame.setContentPane(content);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        SwingUtilities.invokeLater(() -> content.requestFocusInWindow());
        textField.getCaret().setBlinkRate(0); // stops blinking but keeps it visible
        // textField.requestFocusInWindow();
    }

    public static void writeGoal(String goal) {
        String filepath = "goals.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
            bw.write(goal);
            System.out.println("Saved: " + goal);
        } catch (IOException d) {
            System.err.println("An error occurred: " + d.getMessage());
        }
    }

    public static void readVerdict() {

        File file = new File("verdict.txt");
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equalsIgnoreCase("RESULT:DISTRACTED")) {
                    System.out.println("You are distracted");
                    launchWindows();
                    SoundPlayer.playSound(soundFilePath);
                    file.delete();
                } else if (line.equalsIgnoreCase("RESULT:FOCUS")) {
                    System.out.println("You are focused");

                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred reading verdict: " + e.getMessage());
        }
    }

    private static boolean acquireLock() {
        try {
            File lockFile = new File(System.getProperty("java.io.tmpdir"), "hocusfocus.lock");
            lockStream = new FileOutputStream(lockFile);
            lockChannel = lockStream.getChannel();
            lock = lockChannel.tryLock();
            return lock != null;
        } catch (IOException e) {
            System.err.println("Could not acquire lock: " + e.getMessage());
            return false;
        }
    }

    private static void launchWindows() {

        String[] messages = {
                "Take deep breaths.",
                "Such a good job!.",
                "One step at a time.",
                "STay Focused!",
                "Stay on task!",
                "Open mind.",
                "Keep on going!",
                "You're almost there!",
                "LOCK IN!!!!"
        };

        List<JFrame> frames = new ArrayList<>();
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

        WindowManager.showWindow(messages[0], new Color(255, 100, 100), new Color(255, 180, 50),
                rand.nextInt(screenWidth - w1), rand.nextInt(screenHeight - h1), w1, h1, timerLabel, frames);
        WindowManager.showWindow(messages[1], new Color(100, 180, 255), new Color(150, 80, 255),
                rand.nextInt(screenWidth - w2), rand.nextInt(screenHeight - h2), w2, h2, timerLabel, frames);
        WindowManager.showWindow(messages[2], new Color(100, 220, 150), new Color(50, 150, 255),
                rand.nextInt(screenWidth - w3), rand.nextInt(screenHeight - h3), w3, h3, timerLabel, frames);

        WindowManager.startCountdown(timerLabel, 10, frames);
    }

}
