/*
 * Created by Isaac Shagal Anak Tinggal (99176)
 * Tested by Ahmad Izzat Immil bin Ahmad Rushdan (101490)
 * Description
 * Navigation controller responsible for switching between modules (views)
 * such as Main Menu, Learning Module, Quiz Module, Gamification, and Progress Module.
 * It also handles saving the user's quiz results.
 */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class NavigationController {
    private final JFrame frame; // The main application frame
    private final IDataPersistence storage = new DataStorageManager(); // Data manager to store quiz results

    /**
     * Constructor to initialize the controller with the main application frame.
     *
     * @param frame The main application window
     */
    public NavigationController(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Displays the main menu panel.
     */
    public void showMainMenu() {
        setContent(new MainMenuPanel(this));
    }

    /**
     * Displays the learning module panel.
     */
    public void showLearningModule() {
        setContent(new LearningModulePanel(this));
    }

    /**
     * Displays the quiz module panel.
     */
    public void showQuizModule() {
        setContent(new QuizModulePanel(this).getQuizPanel());
    }

    /**
     * Displays the gamification screen after the user completes a quiz.
     * Records the score and shows stars, badge, points, and a motivational message.
     *
     * @param percentScore User's score as a percentage
     * @param timeTaken Duration taken to complete the quiz
     */
    public void showGamificationModule(int percentScore, Duration timeTaken) {
        // 1) Build the gamification element via GamificationEngine
        GamificationModulePanel gm;
        try {
            gm = new GamificationModulePanel(this);
        } catch (ResourceNotFoundException rnfe) {
            JOptionPane.showMessageDialog(frame,
                "Required resource missing:\n" + rnfe.getMessage(),
                "Resource Error", JOptionPane.ERROR_MESSAGE);
            // fall back to main menu if we can’t build the gamification screen
            showMainMenu();
            return;
        }

        // 2) Record the result in the engine
        gm.recordResult(percentScore, timeTaken);
        
        // 3) Create and persist the score record
        int pts = gm.getTotalPoints();
        ScoreRecord rec = new ScoreRecord(
            percentScore,
            pts,
            timeTaken.toMillis(),
            gm.getLastBadge(),
            Instant.now()
        );
        try {
            storage.saveRecord(rec);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame,
                "Could not save your score:\n" + e.getMessage(),
                "Save Error", JOptionPane.ERROR_MESSAGE);
        }

        // 4) Show the gamification panel
        setContent(gm.getGamificationPanel());
    }

    /**
     * Displays the progress/score leaderboard module.
     */
    public void showProgressModule() {
        setContent(new ProgressModulePanel(this));
    }

    /**
     * Replaces the current content panel in the frame with the given one.
     *
     * @param panel The new panel to show
     */
    private void setContent(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}
