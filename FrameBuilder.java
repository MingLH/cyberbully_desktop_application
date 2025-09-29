/*
 * Created by Lee Hao Ming (99451)
 * Tested by Isaac Shagal Anak Tinggal (99176)
 * Description
 * Utility class for constructing standardized application frames.
 */

import javax.swing.*;
import java.net.URL;

public class FrameBuilder {
    /**
     * Builds a JFrame with the specified properties.
     *
     * @param title     The window title to display in the title bar.
     * @param iconPath  The resource path to the window icon (e.g. "/icons/app.png").
     * @param width     The desired width of the window in pixels.
     * @param height    The desired height of the window in pixels.
     * @return A fully-configured JFrame instance, ready to be shown.
     */
    public static JFrame buildFrame(String title, String iconPath, int width, int height) {
        // Create the frame and set basic properties
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Attempt to load the icon image from the given resource path
        URL imgURL = FrameBuilder.class.getResource(iconPath);
        if (imgURL != null) {
            // Set the window icon if the resource was found
            frame.setIconImage(new ImageIcon(imgURL).getImage());
        } else {
            // Log an error if the icon resource could not be located
            System.err.println("Error: icon not found at path: " + iconPath);
        }
        
        // Set the fixed size, center on screen, and disable resizing
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        return frame;
    }
}
