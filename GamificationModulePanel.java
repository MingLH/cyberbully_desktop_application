/*
 * Created by Isaac Shagal Anak Tinggal (99176)
 * Tested by Ahmad Izzat Immil bin Ahmad Rushdan (101490)
 * Description
 * Swing JPanel for displaying gamification results: 
 * score, points, stars, badge, message, and leaderboard link.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.time.Duration;

public class GamificationModulePanel extends AbstractModulePanel implements IGamification {
    private static final int MAX_STARS = 5;

    private final Icon starIcon; // Filled star icon
    private final Icon emptyStarIcon; // Placeholder transparent star
    private final GamificationEngine engine = new GamificationEngine();

    // UI components
    private final JLabel[] starLabels = new JLabel[MAX_STARS];
    private JLabel lblPoints, lblBadge, lblMessage, lblPercentScore;

    /**
     * Constructor loads star icons and initializes the panel.
     * @param nav NavigationController for handling module switches
     * @throws ResourceNotFoundException if icon resources are missing
     */
    public GamificationModulePanel(NavigationController nav) throws ResourceNotFoundException {
        super(nav);

        // load your star icon once
        URL starUrl = getClass().getResource("/resources/images/star.png");
        if (starUrl == null) {
            throw new ResourceNotFoundException("Missing badge icon: star.png");
        }
        starIcon = new ImageIcon(starUrl);

        // Create a transparent icon matching starIcon dimensions for empty stars
        emptyStarIcon = new ImageIcon(
                new BufferedImage(starIcon.getIconWidth(), 
                                starIcon.getIconHeight(), 
                                BufferedImage.TYPE_INT_ARGB)
            );
        // now wire up the panel
        initModule();
    }

     /**
     * Builds the main content area of the gamification panel.
     * @return Center-aligned component stack with score, points, stars, badge, message, and leaderboard button.
     */
    @Override
    protected JComponent buildMainContent() {
        // 1) Score label (bold, large)
        lblPercentScore = new JLabel("", SwingConstants.CENTER);
        lblPercentScore.setFont(lblPercentScore.getFont().deriveFont(Font.BOLD, 40f));
        lblPercentScore.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 2) Total points label (bold)
        lblPoints = new JLabel("", SwingConstants.CENTER);
        lblPoints.setFont(lblPoints.getFont().deriveFont(Font.BOLD, 36f));
        lblPoints.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 3) Stars panel: placeholders for up to MAX_STARS
        JPanel starsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        starsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        for (int i = 0; i < MAX_STARS; i++) {
            JLabel star = new JLabel(emptyStarIcon);
            starLabels[i] = star;
            starsPanel.add(star);
        }

        // 4) Badge label (plain, medium size)
        lblBadge = new JLabel("", SwingConstants.CENTER);
        lblBadge.setFont(lblBadge.getFont().deriveFont(Font.PLAIN, 32f));
        lblBadge.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 5) Motivational message (italic) 
        lblMessage = new JLabel("", SwingConstants.CENTER);
        lblMessage.setFont(lblMessage.getFont().deriveFont(Font.ITALIC, 24f));
        lblMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMessage.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        // 6) “View Leaderboard” button inside content
        JButton btnLeaderboard = new JButton("📊 View Leaderboard");
        btnLeaderboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLeaderboard.addActionListener(_ -> nav.showProgressModule());

        // 7) Assemble components vertically
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(lblPercentScore);
        center.add(Box.createRigidArea(new Dimension(0, 10)));
        center.add(lblPoints);
        center.add(Box.createRigidArea(new Dimension(0, 10)));
        center.add(starsPanel);
        center.add(Box.createRigidArea(new Dimension(0, 10)));
        center.add(lblBadge);
        center.add(Box.createRigidArea(new Dimension(0, 10)));
        center.add(lblMessage);
        center.add(Box.createRigidArea(new Dimension(0, 20)));
        center.add(btnLeaderboard);

        return center;
    }

     /**
     * Called after quiz completion to record results and update UI.
     * @param percentScore The score percentage achieved (0-100).
     * @param timeTaken    Duration taken to complete the quiz.
     */
    @Override
    public void recordResult(int percentScore, Duration timeTaken) {
        engine.record(percentScore, timeTaken);

        // Update labels with new values
        lblPercentScore.setText("Score: " + percentScore + "%");
        lblPoints.setText("Points: " + engine.getTotalPoints());
        lblBadge .setText("Badge: "  + engine.getLastBadge());
        lblMessage.setText(engine.getLastMotivationalMessage());

        // Update star icons: filled or empty based on lastStars
        for (int i = 0; i < MAX_STARS; i++) {
            Icon icon = (i < engine.getLastStars()) ? starIcon : emptyStarIcon;
            starLabels[i].setIcon(icon);
        }
    }

    // Expose gamification metrics via interface
    @Override 
    public String getLastMotivationalMessage() { 
        return engine.getLastMotivationalMessage();
    }
    
    @Override 
    public int getTotalPoints() { 
        return engine.getTotalPoints(); 
    }
    
    @Override 
    public int getLastStars() { 
        return engine.getLastStars();   
    }
    
    @Override 
    public String getLastBadge() { 
        return engine.getLastBadge();   
    }

    /**
     * Retrieve the gamification panel component.
     * @return this panel as a JPanel.
     */
    @Override
    public JPanel getGamificationPanel() {
        return this;
    }
}
