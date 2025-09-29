/*
 * Created by Ahmad Izzat Immil bin Ahmad Rushdan (101490)
 * Tested by Muhammad Kalil bin Sammat (99955)
 * Description
 * Immutable data class representing a user's single quiz attempt.
 * Stores the score percentage, gamification points, time taken,
 * awarded badge, and timestamp of completion.
 */

import java.time.Instant;

public class ScoreRecord {
    private final int scorePercent;     // Quiz score as a percentage (0–100)
    private final int points;           // Gamification points awarded (score×10 + bonuses)
    private final long timeTakenMs;     // Time taken to complete quiz, in milliseconds
    private final String badge;         // Badge tier awarded (e.g., "Gold", "Silver")
    private final Instant timestamp;    // Instant when the quiz was completed

    /**
     * Constructs a new ScoreRecord with all required fields.
     *
     * @param scorePercent Quiz score percentage (0–100)
     * @param points       Total points earned (including any bonuses)
     * @param timeTakenMs  Duration of quiz in milliseconds
     * @param badge        Badge tier awarded for this attempt
     * @param timestamp    Timestamp of when the quiz was submitted
     */
    public ScoreRecord(int scorePercent, int points, long timeTakenMs, String badge, Instant timestamp) {
        this.scorePercent = scorePercent;
        this.points       = points;
        this.timeTakenMs  = timeTakenMs;
        this.badge        = badge;
        this.timestamp    = timestamp;
    }

    /** @return the quiz score percentage (0–100). */
    public int getScorePercent() { 
        return scorePercent; 
    }

    /** @return the gamification points awarded for this attempt. */
    public int getPoints() { 
        return points;     
    }

    /** @return the time taken to complete the quiz, in milliseconds. */
    public long getTimeTakenMs() { 
        return timeTakenMs;  
    }

    /** @return the badge awarded (e.g., "Gold", "Silver"). */
    public String getBadge() { 
        return badge;        
    }

    /** @return the timestamp when the quiz was completed. */
    public Instant getTimestamp() { 
        return timestamp;    
    }
}
