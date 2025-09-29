/**
 * Created by Lee Hao Ming (99451)
 * Tested by Isaac Shagal Anak Tinggal (99176)
 * Description:
 * Base class for all “screens” in the app (Learning, Quiz, Gamification, Progress).
 * Encapsulates common frame‑padding, title area, and nav‑button bar.
 */

import javax.swing.*;
import java.awt.*;

public abstract class AbstractModulePanel extends JPanel {
    protected final NavigationController nav;

    /**
     * Constructor for AbstractModulePanel.
     * @param nav NavigationController instance to handle navigation actions.
     */
    public AbstractModulePanel(NavigationController nav) {
        this.nav = nav;
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,5,10));
    }

    /** Should be called by subclass *after* its own fields are ready. */
    protected void initModule() {
        add(buildMainContent(), BorderLayout.CENTER);
        add(buildNavBar(), BorderLayout.SOUTH);
    }

    /** Subclasses provide their main content panel here. */
    protected abstract JComponent buildMainContent();

    /** Common “Home” button bar at bottom */
    private JPanel buildNavBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton home = new JButton("🏠 Main Menu");
        home.addActionListener(_ -> nav.showMainMenu());
        bar.add(home);
        return bar;
    }
}
