public class SoundPlayer {
    public static void playSound(String filePath) {
        try {
            // Create a File object from the file path
            File soundFile = new File(filePath);
            
            // Get an AudioInputStream from the sound file
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile.getAbsoluteFile());
            
            // Get a clip resource
            Clip clip = AudioSystem.getClip();
            
            // Open the audio clip 
            clip.open(audioIn);
            
            // Start the playback
            clip.start();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
