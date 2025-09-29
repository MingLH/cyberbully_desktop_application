/*
 * Created by Isaac Shagal Anak Tinggal (99176)
 * Tested by Ahmad Izzat Immil bin Ahmad Rushdan (101490)
 * Description
 * Interface for retrieving gamification results 
 * including stars, badges, messages, and visual feedback.
 */

import javax.swing.JPanel;
import java.time.Duration;

public interface IGamification {
    /**
     * Feed in a quiz result: %score plus timeTaken.
     * Engine will convert to points, stars, and a badge.
     */
    void recordResult(int percentScore, Duration timeTaken);

    /** @return total points the user has accrued. */
    int getTotalPoints();

    /** @return number of stars earned for this last quiz (e.g. 1–5). */
    int getLastStars();

    /** @return the badge level (e.g. "Gold", "Silver", "Bronze"). */
    String getLastBadge();

    /** New: retrieve the motivational message for the last score */
    String getLastMotivationalMessage();

    /**
     * @return a Swing panel that shows points, stars, badge, and optionally a timer.
     */
    JPanel getGamificationPanel();
}
