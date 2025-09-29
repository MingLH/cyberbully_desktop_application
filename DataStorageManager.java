/*
 * Created by Ahmad Izzat Immil bin Ahmad Rushdan (101490)
 * Tested by Muhammad Kalil bin Sammat (99955)
 * Description
 * Data storage manager for handling user scores and persistence.
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DataStorageManager implements IDataPersistence {
    private static final Path FILE = Paths.get("scores.csv"); // File path and header for CSV
    private static final String HEADER = "ScorePercent,Points,TimeTakenMs,Badge,Timestamp"; // CSV header

    // Format per line: ScorePercent,Points,TimeTakenMs,Badge,Timestamp
    @Override
    public void saveRecord(ScoreRecord record) throws IOException {
        // Determine which directory to create: the parent of FILE, or “.” if FILE has no parent
        Path directory = FILE.getParent();
        if (directory == null) {
            directory = Paths.get(".");
        }

        // Ensure that directory exists (creates all non‑existent parent directories)
        Files.createDirectories(directory);

        // Check if the file already exists
        boolean fileExists = Files.exists(FILE);

        // If it exists, check whether it’s empty (size == 0)
        boolean isEmptyFile = fileExists && Files.size(FILE) == 0;

        // We need to write the header if the file doesn’t exist or is empty
        boolean needHeader = !fileExists || isEmptyFile;

        // Open the file for appending 
        try (BufferedWriter writer = Files.newBufferedWriter(
                FILE,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        )) {
            if (needHeader) {
                // Write CSV header once
                writer.write(HEADER);
                writer.newLine();
            }
            // CSV line: score,points,timeTakenMs,badge,timestamp
            String line = String.format(
                "%d,%d,%d,%s,%d",
                record.getScorePercent(),
                record.getPoints(),
                record.getTimeTakenMs(),
                record.getBadge(),
                record.getTimestamp().toEpochMilli()
            );
            writer.write(line); 
            writer.newLine();
        }
    }

    @Override
    public List<ScoreRecord> loadAllRecords() throws IOException, DataFormatException {
        // Create a list to collect all ScoreRecord objects
        List<ScoreRecord> list = new ArrayList<>();

        // If the file doesn't exist yet, return an empty list
        if (!Files.exists(FILE)) {
            return list;
        }

        // Read all lines from the CSV, including header
        List<String> lines = Files.readAllLines(FILE);
        for (String line : lines) {
            // Skip empty lines or the header row
            if (line.isEmpty() || line.startsWith("ScorePercent")) {
                continue;
            }

            // Split into exactly 5 parts: pct, points, ms, badge, timestamp
            String[] parts = line.split(",", 5);
            if (parts.length < 5) {
                // Malformed line: not enough columns
                throw new DataFormatException("Malformed scores.csv line: " + line);
            }

            try {
                // Parse each field into its appropriate type
                int pct = Integer.parseInt(parts[0]);
                int points = Integer.parseInt(parts[1]);
                long ms = Long.parseLong(parts[2]);
                String badge = parts[3];
                long tstamp = Long.parseLong(parts[4]);

                // Construct ScoreRecord and add to list
                list.add(new ScoreRecord(
                    pct,
                    points,
                    ms,
                    badge,
                    Instant.ofEpochMilli(tstamp)
                ));
            } catch (NumberFormatException nfe) {
                // Invalid numeric parsing in this CSV line
                throw new DataFormatException("Invalid number in scores.csv line: " + line, nfe);
            }
        }
        
        // Return the populated list of record
        return list;
    }
}
