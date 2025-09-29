/*
 * Created by Ahmad Izzat Immil bin Ahmad Rushdan (101490)
 * Tested by Muhammad Kalil bin Sammat (99955)
 * Description
 * Displays a leaderboard of all quiz attempts by the user,
 * sorted by score, time taken, and recency. Data is shown in a JTable.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ProgressModulePanel extends AbstractModulePanel {
    // Handles loading saved score records
    private final IDataPersistence storage = new DataStorageManager();

    public ProgressModulePanel(NavigationController nav) {
        super(nav);
        initModule(); // Initializes the layout inherited from AbstractModulePanel
    }

    /**
     * Builds the main content of the leaderboard panel.
     *
     * @return A scrollable table showing score records
     */
    @Override
    protected JComponent buildMainContent() {
        // 1) Load & sort records
        List<ScoreRecord> all;
        try {
            all = storage.loadAllRecords();
        } catch (DataFormatException dfe) {
            // If data format is corrupted, show error and fallback to empty list
            JOptionPane.showMessageDialog(
                this,
                "Corrupt score data:\n" + dfe.getMessage(),
                "Data Error", JOptionPane.ERROR_MESSAGE
                );
            all = List.of();
        } catch (IOException ioe) {
            // If file access fails, show error and fallback to empty list
            JOptionPane.showMessageDialog(this,
                "Error loading scores:\n" + ioe.getMessage(),
                "Load Error", JOptionPane.ERROR_MESSAGE);
            all = List.of();
        }

         // Sort by score descending, then time ascending, then most recent
        Comparator<ScoreRecord> byScoreDesc = Comparator.comparingInt(ScoreRecord::getScorePercent).reversed();
        Comparator<ScoreRecord> byTimeAsc  = Comparator.comparingLong(ScoreRecord::getTimeTakenMs);
        Comparator<ScoreRecord> byTimeDesc = Comparator.comparing(ScoreRecord::getTimestamp, Comparator.reverseOrder());
        all.sort(byScoreDesc.thenComparing(byTimeAsc).thenComparing(byTimeDesc));

        // 2) Build table data from records
        String[] cols = {"Rank","Score","Points","Time","Badge","Date"};
        Object[][] data = new Object[all.size()][cols.length];
        DateFormat df = DateFormat.getDateTimeInstance();

        for (int i = 0; i < all.size(); i++) {
            ScoreRecord r = all.get(i);
            data[i][0] = i + 1; // Rank
            data[i][1] = r.getScorePercent() + "%";
            data[i][2] = r.getPoints();
            long ms = r.getTimeTakenMs();
            data[i][3] = String.format("%02d:%02d", (ms/1000)/60, (ms/1000)%60);
            data[i][4] = r.getBadge();
            data[i][5] = df.format(new Date(r.getTimestamp().toEpochMilli()));
        }

        JTable table = new JTable(data, cols);
        table.setEnabled(false); // Make it read-only

        // 3) Center-align all table cells
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int c = 0; c < table.getColumnCount(); c++) {
            table.getColumnModel().getColumn(c).setCellRenderer(center);
        }

        // Center-align the table header
        DefaultTableCellRenderer hdr = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        hdr.setHorizontalAlignment(SwingConstants.CENTER);

        // 4) Set fixed column widths for better formatting
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel cm = table.getColumnModel();
        cm.getColumn(0).setPreferredWidth(40);
        cm.getColumn(1).setPreferredWidth(50);
        cm.getColumn(2).setPreferredWidth(57);
        cm.getColumn(3).setPreferredWidth(60);
        cm.getColumn(4).setPreferredWidth(80);
        cm.getColumn(5).setPreferredWidth(150);

        // 5) Wrap the table in a scroll pane with a titled border
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Leaderboard"));
        
        return scroll;
    }

}
