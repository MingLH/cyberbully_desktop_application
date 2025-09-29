/*
 * Created by Isaac Shagal Anak Tinggal (99176)
 * Tested by Ahmad Izzat Immil bin Ahmad Rushdan (101490)
 * Description
 * Core logic for accumulating points, stars, badges, and motivational messages
 * based on quiz performance and completion time.
 */


import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class GamificationEngine {
    // Thread-safe counter for total accumulated points across sessions
    private final AtomicInteger totalPoints = new AtomicInteger(0);
    private int lastStars; 
    private String lastBadge;
    private String lastMessage;

    /**
     * Convert a quiz result into points, stars, and a badge.
     * For example: 
     *   points = percentScore * 10
     *   stars  = percentScore / 20  (0–5 stars)
     *   badge  = Outstanding/Good/…
     */
    public void record(int percentScore, Duration timeTaken) {
        // Points: base = percentScore * 10
        int pts = percentScore * 10;

        // Add 50 points if quiz completed in under 1 minutes
        if (timeTaken.toMinutes() < 1) {
            int bonus = 50;              
            totalPoints.addAndGet(bonus);
        }
        // Add base points
        totalPoints.addAndGet(pts);

        // Stars: award 0-5 stars based on score/20
        lastStars = Math.min(5, percentScore / 20);

        // Determine badge tier
        if (percentScore >= 80) {
            lastBadge = "Gold";
        } else if (percentScore >= 60) {
            lastBadge = "Silver";
        } else if (percentScore >= 40) {
            lastBadge = "Bronze";
        } else {
            lastBadge = "Encourage";
        }

        // Generate motivational message
        if (percentScore >= 80) {
            lastMessage = "Outstanding!";
        } else if (percentScore >= 60) {
            lastMessage = "That's good!";
        } else if (percentScore >= 40) {
            lastMessage = "Good try!";
        } else if (percentScore >= 20) {
            lastMessage = "You can do better!";
        } else {
            lastMessage = "Don't give up!";
        }
    }

    /** @return The last motivational message generated. */
    public String getLastMotivationalMessage() {
        return lastMessage; 
    }

    /** @return Total points accumulated so far (thread-safe). */
    public int getTotalPoints() { 
        return totalPoints.get(); 
    }

    /** @return Number of stars awarded in the last record (0-5). */
    public int getLastStars() { 
        return lastStars;
    }

    /** @return Badge awarded in the last record. */
    public String getLastBadge() { 
        return lastBadge;       
    }
}
