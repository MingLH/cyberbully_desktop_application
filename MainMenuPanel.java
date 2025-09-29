/*
 * Created by Isaac Shagal Anak Tinggal (99176)
 * Tested by Ahmad Izzat Immil bin Ahmad Rushdan (101490)
 * Description:
 * Main menu panel that allows users to navigate to other parts of the application:
 * lessons, quiz, and score leaderboard. Includes banner and animated buttons.
 */

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(NavigationController nav) {

        // --- Panel Setup ---
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(184, 227, 209)); // or any light color

        // --- Banner ---
        URL logoUrl = getClass().getResource("/resources/images/MainMenuIcon.png");
        if (logoUrl != null) {
            ImageIcon bannerIcon = new ImageIcon(logoUrl);
            JLabel banner = new JLabel(bannerIcon);

            banner.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(banner);
            add(Box.createRigidArea(new Dimension(0, 20))); // spacing under banner
        }

        add(Box.createVerticalGlue());  // push content toward center

        // --- Buttons ---
        addMenuButton("View Lessons", nav::showLearningModule);
        addMenuButton("Start Quiz",   nav::showQuizModule);
        addMenuButton("View Scores",  nav::showProgressModule);
    }

    /** Helper method to create and style a menu button with hover effect */
    private void addMenuButton(String text, Runnable action) {
        JButton btn = new JButton(text);

        // --- Style ---
        btn.setFont(btn.getFont().deriveFont(Font.BOLD, 16f));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(220, 50));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(0xe0b1b1)); // very warm light pink
        btn.setForeground(Color.BLACK);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // --- Hover animation ---
        btn.addMouseListener(new MouseAdapter() {
            Color orig = btn.getBackground();
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(orig.darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(orig);
            }
        });

        btn.addActionListener(_ -> action.run());
        add(btn);
        add(Box.createRigidArea(new Dimension(0, 15))); // spacing below button
    }
}
