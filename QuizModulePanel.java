/*
 * Created by Muhammad Kalil bin Sammat (99955)
 * Tested by Lee Hao Ming (99451)
 * Description:
 * JPanel implementing IQuizModule to display quiz questions, track progress,
 * manage timing, and handle user navigation and scoring logic.
 */

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.util.List;

public class QuizModulePanel extends AbstractModulePanel implements IQuizModule {
    private final List<Question> questions; // List of all quiz questions
    private int currentIndex = 0; // Current question index
    private int correctCount = 0; // Number of correct answers
    private final boolean[] answeredCorrect; // Tracks per-question correctness
    private long startMillis; // Quiz start time (ms)

    // UI components
    private JLabel lblQuestion, lblProgress;
    private JProgressBar progressBar;
    private JRadioButton[] optionButtons;
    private ButtonGroup group;
    private JButton btnNext, btnPrev;
    private JLabel lblTimer;
    private Timer swingTimer;

    /**
     * Constructor: initializes quiz data and UI.
     * @param nav Navigation controller for switching modules
     */
    public QuizModulePanel(NavigationController nav) {
        super(nav); // Initialize base panel
        this.questions = QuizModule.getQuestions();  // now directly a List
        this.answeredCorrect = new boolean[questions.size()];
        this.startMillis = System.currentTimeMillis();  // Record quiz start time
        initModule();  // call the method from AbstractModulePanel
    }

    /**
     * Builds the main quiz UI: progress, question text, options, and navigation.
     */
    @Override
    protected JComponent buildMainContent() {
        // NORTH: progress bar / progress label / question / timer
        lblProgress  = new JLabel("", SwingConstants.CENTER);
        progressBar  = new JProgressBar(0, questions.size());
        lblQuestion  = new JLabel("", SwingConstants.CENTER);
        lblTimer     = new JLabel("00:00", SwingConstants.CENTER);

        // Configure fonts and borders
        lblProgress.setFont(lblProgress.getFont().deriveFont(20f));
        progressBar.setStringPainted(true);
        progressBar.setFont(progressBar.getFont().deriveFont(Font.BOLD, 20f));
        progressBar.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));
        lblQuestion.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        lblTimer.setFont(lblTimer.getFont().deriveFont(Font.BOLD, 16f));

        // Layout NORTH panel
        JPanel north = new JPanel(new BorderLayout(5,5));
        north.add(progressBar, BorderLayout.NORTH);
        north.add(lblProgress, BorderLayout.CENTER);
        north.add(lblQuestion, BorderLayout.SOUTH);
        north.add(lblTimer, BorderLayout.EAST);

        // Start Swing timer to update elapsed time every second
        swingTimer = new Timer(1000, _ -> {
            long elapsed = System.currentTimeMillis() - startMillis;
            lblTimer.setText(formatMs(elapsed));
        });
        swingTimer.start();

        // CENTER: your “Select one” hint and options
        JPanel opts = new JPanel();
        opts.setLayout(new BoxLayout(opts, BoxLayout.Y_AXIS));
        opts.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // … build lblHint, optionButtons[], wrap logic …
        JLabel lblHint = new JLabel("Select one:", SwingConstants.LEFT);
        lblHint.setFont(lblHint.getFont().deriveFont(Font.ITALIC, 18f));
        lblHint.setAlignmentX(Component.LEFT_ALIGNMENT);
        opts.add(lblHint);

        // Radio buttons for up to 4 options with wrapping
        optionButtons = new JRadioButton[4];
        group = new ButtonGroup();

        int wrapWidth = 500;
        for (int i = 0; i < optionButtons.length; i++) {
            JRadioButton btn = new JRadioButton();
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setVisible(false); // will be shown in showQuestion()

            // Set HTML text with wrapping
            btn.setFont(btn.getFont().deriveFont(18f));

            // —— constrain the component’s width —— 
            btn.setMaximumSize(new Dimension(wrapWidth, Integer.MAX_VALUE));
            btn.setPreferredSize(new Dimension(wrapWidth, btn.getPreferredSize().height));

            group.add(btn);
            opts.add(btn);
            opts.add(Box.createRigidArea(new Dimension(0,2)));
            optionButtons[i] = btn; // save into the field
        }

        // SOUTH (inside content): Back / Next buttons
        btnPrev = new JButton("← Back"); 
        btnNext = new JButton("Next →");
        btnPrev.addActionListener(_ -> onPrev());
        btnNext.addActionListener(_ -> onNext());
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        flow.add(btnPrev);
        flow.add(btnNext);

        // assemble into one wrapper
        JPanel wrapper = new JPanel(new BorderLayout(10,10));
        wrapper.add(north,  BorderLayout.NORTH);
        wrapper.add(opts,   BorderLayout.CENTER);
        wrapper.add(flow,   BorderLayout.SOUTH);

        // initialize first question
        showQuestion(0);
        return wrapper;
    }


    @Override
    public JPanel getQuizPanel() {
        return this;
    }

    @Override
    public int getScorePercentage() {
        return (int)Math.round(100.0 * correctCount / questions.size());
    }

    /**
     * Helper: formats milliseconds into MM:SS string.
     */
    private static String formatMs(long ms) {
        long totalSec = ms / 1000;
        long minutes  = totalSec / 60;
        long seconds  = totalSec % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Displays question at index: updates text, options, and buttons.
     */
    private void showQuestion(int idx) {
        group.clearSelection();
        Question q = questions.get(idx);

        // Set progress label text and font size
        lblProgress.setText("Question " + (idx+1) + " of " + questions.size());

        // Set progress bar value and string
        progressBar.setValue(idx+1);
        progressBar.setString((idx+1) + " / " + questions.size());

        // Set question text with larger font size
        lblQuestion.setText("<html><body style='font-size: 24px; text-align:center;'>" + q.getQuestionText() + "</body></html>");

        // Populate and wrap option buttons
        int wrapWidth = 250;
        String[] opts = q.getOptions();
        for (int i = 0; i < optionButtons.length; i++) {
            if (i < opts.length) {
                String raw = opts[i];
                String html = "<html>"
                                + "<body style='width:" + (wrapWidth - 10) + "px; text-align:left;'>"
                                + raw
                                + "</body>"
                                + "</html>";

                optionButtons[i].setText(html);
                optionButtons[i].setActionCommand(raw);
                optionButtons[i].setVisible(true);
            } else {
                optionButtons[i].setVisible(false);
            }
        }

        if (idx == questions.size() - 1) {
            btnNext.setText("Submit");
        } else {
            btnNext.setText("Next");
        }

        // Enable/disable previous buttons
        btnPrev.setEnabled(idx > 0);
    }

    /**
     * Handler for Next/Submit button: validate, record, and advance or finish.
     */
    private void onNext() {
        // find selected answer
        String answer = null;
        for (JRadioButton b : optionButtons) {
            if (b.isVisible() && b.isSelected()) {
                answer = b.getActionCommand();
                break;
            }
        }
        // if no selection, prompt
        if (answer == null) {
            JOptionPane.showMessageDialog(this, "Please select an answer.");
            return;
        }

        // check correctness 
        boolean wasCorrect = questions.get(currentIndex).checkAnswer(answer);
        if (wasCorrect) {
            correctCount++;
            answeredCorrect[currentIndex] = true;
        } else {
            answeredCorrect[currentIndex] = false;
        }
        currentIndex++;

        if (currentIndex < questions.size()) {
            showQuestion(currentIndex);
        } else {
            // Finished all questions, show results
            showResults();
        }
    }

     /**
     * Handler for Back button: step back and adjust score if necessary.
     */
    private void onPrev() {
        if (currentIndex > 0) {
            currentIndex--;
            // if they had gotten that one right, remove it
            if (answeredCorrect[currentIndex]) {
                correctCount--;
            }
            showQuestion(currentIndex);
        }
    }

    /**
     * Finalize quiz: stop timer and hand off to gamification module.
     */
    private void showResults() {
        int pct = getScorePercentage();
        swingTimer.stop();
        long endMillis   = System.currentTimeMillis();
        Duration elapsed = Duration.ofMillis(endMillis - startMillis);
        // Hand off both score and time to your controller
        nav.showGamificationModule(pct, elapsed);
    }
}
