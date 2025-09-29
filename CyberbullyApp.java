/*
 * Lecturer Name: Puan Nur Azima Alya Binti Narawi (Lecture Group 2)
 * Project Group 6
 * Topic: Cyberbullying Awareness
 * Project Members:
 * 1. Lee Hao Ming (99451)
 * 2. Isaac Shagal Anak Tinggal (99176)
 * 3. Muhammad Kalil bin Sammat (99955)
 * 4. Ahmad Izzat Immil bin Ahmad Rushdan (101490)
 */

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class CyberbullyApp {
    public static void main(String[] args) {
        // Initialize the Swing application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create the main application frame
            JFrame frame = FrameBuilder.buildFrame("Cyberbully Awareness App", "/resources/images/AppIcon.png", 480, 760);

            // Initialize the navigation controller and main menu
            NavigationController nav = new NavigationController(frame);
            nav.showMainMenu();
            frame.setVisible(true);
        });
    }
}
