/*
 * Created by Ahmad Izzat Immil bin Ahmad Rushdan (101490)
 * Tested by Muhammad Kalil bin Sammat (99955)
 * Description
 * Interface for managing persistent storage of quiz score records.
 */

import java.io.IOException;
import java.util.List;

public interface IDataPersistence {
    /**
     * Append a new ScoreRecord to persistent storage.
     * Used to save quiz results incrementally.
     *
     * @param record the ScoreRecord to save
     * @throws IOException if writing fails
     */
    void saveRecord(ScoreRecord record) throws IOException;

    /**
     * Load all saved ScoreRecord entries from persistent storage in chronological order.
     * Typically used to build the leaderboard or progress summary.
     *
     * @return a List of ScoreRecord entries
     * @throws IOException if reading fails
     * @throws DataFormatException if data is malformed or cannot be parsed
     */
    List<ScoreRecord> loadAllRecords() throws IOException, DataFormatException;
}
