/*
 * Created by Lee Hao Ming (99451)
 * Tested by Isaac Shagal Anak Tinggal (99176)
 * Description:
 * Implementation of a learning module panel that displays educational content
 * about cyberbullying awareness. Supports navigation between pages and displays
 * text, images, and videos.
 */

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LearningModulePanel extends AbstractModulePanel implements ILearningModule {
    private List<ContentPage> pages = new ArrayList<>(); // All content pages
    private int currentPageIndex = 0; // Tracks current page index
    private JButton btnFirst, btnPrev, btnNext, btnLast; // Navigation buttons

    public LearningModulePanel(NavigationController nav) {
        super(nav);
        loadContent(); // Populate the list of pages
        initModule(); // call the method from AbstractModulePanel to build the UI layout
    }

    @Override
    protected JComponent buildMainContent() {
        // 1) Create the content container
        JPanel container = new JPanel(new BorderLayout());

        // 2) Navigation buttons (First, Prev, Next, Last) at South of this module
        JPanel btnBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnFirst = new JButton("« First");
        btnPrev  = new JButton("← Previous");
        btnNext  = new JButton("Next →");
        btnLast  = new JButton("Last »");

        // Assign action listeners for button navigation
        btnFirst.addActionListener(_ -> { currentPageIndex = 0; updatePage(container); });
        btnPrev .addActionListener(_ -> { prevPage(); updatePage(container); });
        btnNext .addActionListener(_ -> { nextPage(); updatePage(container); });
        btnLast .addActionListener(_ -> { currentPageIndex = pages.size() - 1; updatePage(container); });

        btnBar.add(btnFirst);
        btnBar.add(btnPrev);
        btnBar.add(btnNext);
        btnBar.add(btnLast);

        // 3) Load the first page into center
        updatePage(container);

        // 4) Put nav‑bar at bottom of this content
        container.add(btnBar, BorderLayout.SOUTH);
        return container;
    }

    @Override
    public void loadContent() {
        pages.clear();
        // Each page is a lesson screen in the cyberbullying module
        pages.add(new ContentPage(
            "Cyberbullying Awareness 101",
            "Welcome to the Cyberbullying Awareness Module!<br><br>" +
            "This app will help you understand what cyberbullying is, the impact it has, and how you can respond effectively " +
            "whether you're a target, a bystander, or just someone who wants to promote a safer online community.<br><br>" +
            "Let's work together to make the internet a respectful and positive space for everyone.",
            "page1.png"
        ));

        pages.add(new ContentPage(
            "1. What is Cyberbullying?",
            "Cyberbullying is the act of using digital devices—like phones, computers, or tablets—to deliberately and repeatedly harm or intimidate someone. " +
            "It often occurs on social media, messaging platforms, online forums, or gaming communities.<br><br>" +
            "Unlike traditional bullying, cyberbullying can happen 24/7, is hard to escape, and spreads quickly. " +
            "Examples include sending threatening messages, sharing embarrassing photos, or spreading false information online.",
            "page2.png"
        ));

        pages.add(new ContentPage(
            "2. Forms of Cyberbullying (Part 1)",
            "- <b>Harassment</b>: Repeatedly sending hurtful or threatening messages to someone.<br>" +
            "- <b>Denigration</b>: Spreading rumors, lies, or edited photos/videos to damage someone's reputation.<br>" +
            "- <b>Flaming</b>: Engaging in online fights using hostile or offensive language.<br><br>" +
            "These actions often aim to embarrass, shame, or provoke the victim in front of others.",
            "page3.png"
        ));

        pages.add(new ContentPage(
            "3. Forms of Cyberbullying (Part 2)",
            "- <b>Outing/Trickery</b>: Sharing someone's private or personal information (like secrets or images) without their consent.<br>" +
            "- <b>Exclusion</b>: Intentionally excluding someone from an online group, chat, or activity to make them feel left out.<br>" +
            "- <b>Cyberstalking</b>: Repeatedly following, messaging, or threatening someone online, making them feel unsafe.<br><br>" +
            "Recognizing these behaviors can help you spot when cyberbullying is happening.",
            "page4.png"
        ));

        pages.add(new ContentPage(
            "4. Emotional & Psychological Impacts",
            "Cyberbullying can deeply affect a person's mental and emotional well-being. Victims may experience:<br><br>" +
            "- Anxiety, depression, or extreme sadness<br>" +
            "- Lowered self-esteem or confidence<br>" +
            "- Fear of using social media or technology<br>" +
            "- Trouble concentrating or sleeping<br>" +
            "- Thoughts of self-harm or suicide<br><br>" +
            "These effects can last long after the bullying has stopped, which is why early support and intervention are crucial.",
            "page5.png"
        ));

        pages.add(new ContentPage(
            "5. How to Recognize Signs",
            "You might not always see cyberbullying happening, but these signs could indicate that someone is being targeted:<br><br>" +
            "- Sudden withdrawal from online activity or friends<br>" +
            "- Changes in mood, like sadness, irritability, or anger after being online<br>" +
            "- Avoidance of school or social situations<br>" +
            "- Unexplained health issues like headaches or insomnia<br>" +
            "- Changes in academic performance or behavior<br><br>" +
            "If you notice these signs in yourself or others, don't ignore them.",
            "page6.png"
        ));

        pages.add(ContentPage.withVideo(
            "6. What to Do If You're a Target",
            "If you're being cyberbullied, you are not alone. Here are smart steps to protect yourself:<br><br>" +
            "1. <b>Don't respond</b> to the bully. Stay calm.<br>" +
            "2. <b>Save the evidence</b> - take screenshots or download messages.<br>" +
            "3. <b>Block or mute</b> the bully on all platforms.<br>" +
            "4. <b>Report</b> the abuse to the app or website moderators.<br>" +
            "5. <b>Talk to someone</b> - a parent, teacher, or counselor can help you get support.<br><br>" +
            "You deserve to feel safe online. Speaking up is a sign of strength.",
            "Block_report_in_Facebook.mp4"
        ));

        pages.add(new ContentPage(
            "7. If You Witness Cyberbullying",
            "Being a bystander gives you the power to help. Here's how:<br><br>" +
            "- <b>Don't ignore it</b>. Silence can make the victim feel even more alone.<br>" +
            "- <b>Support the victim</b>. A simple private message can go a long way.<br>" +
            "- <b>Report the bully</b> using the app's built-in tools.<br>" +
            "- <b>Don't participate or share</b> harmful content.<br><br>" +
            "Your actions can help stop the cycle of bullying and show others that kindness matters.",
            "page8.png"
        ));

        pages.add(new ContentPage(
            "8. Safe Ways to Intervene",
            "- <b>Reach out privately</b> to the victim to show support.<br>" +
            "- <b>Tell a trusted adult</b> or authority figure, especially if the bullying is severe.<br>" +
            "- <b>Do not fight back</b> or confront the bully online - this could escalate the situation.<br>" +
            "- <b>Use anonymous reporting</b> tools if available.<br><br>" +
            "Stepping in safely shows maturity, leadership, and empathy. Be the voice for those who can't speak up.",
            "page9.png"
        ));

        pages.add(new ContentPage(
            "9. How to Support a Victim",
            "When someone opens up about cyberbullying, your response matters. Here's how you can help:<br><br>" +
            "- <b>Listen without judgment</b>. Don't blame or shame them.<br>" +
            "- <b>Validate their feelings</b>. Let them know it's okay to be upset.<br>" +
            "- <b>Encourage positive action</b>: saving evidence, blocking the bully, and reporting.<br>" +
            "- <b>Help them find professional support</b> if needed (counselors, helplines, etc.).<br><br>" +
            "Even small acts of support can make a big difference in someone's recovery.",
            "page10.png"
        ));

        pages.add(new ContentPage(
            "10. Promoting Positive Online Behavior",
            "Creating a better online environment starts with you:<br><br>" +
            "- <b>Think before you post</b>. Ask: Is it kind? Is it true?<br>" +
            "- <b>Respect others' opinions</b>, even when you disagree.<br>" +
            "- <b>Encourage positivity</b>: compliment, support, and uplift others.<br>" +
            "- <b>Be inclusive</b> - don't tolerate hate speech or discrimination.<br><br>" +
            "Kindness is contagious. Spread it online.",
            "page11.png"
        ));

        pages.add(new ContentPage(
            "11. Digital Citizenship Wrap-Up",
            "Being a good digital citizen means being responsible, respectful, and ethical online.<br><br>" +
            "Ask yourself:<br>" +
            "- Am I contributing to a positive online space?<br>" +
            "- Would I say this to someone face-to-face?<br>" +
            "- How would I feel if this were said about me?<br><br>" +
            "Always <b>Think. Breathe. Then type</b>. Let's build a digital world where everyone feels safe and respected.",
            "page12.png"
        ));

        pages.add(new ContentPage(
            "Thank You for Learning!",
            "Thank you for completing the Cyberbullying Awareness Module!<br><br>" +
            "Remember, your actions online matter. By understanding cyberbullying and promoting kindness, " +
            "you can help create a safer and more positive digital community for everyone.<br><br>" +
            "You can try out our quiz to test your understanding of Cyberbullying Awareness."
        ));
    }

    @Override
    public JPanel getContentPanel() {
        return this;
    }

    @Override
    public void nextPage() {
        if (currentPageIndex < pages.size() - 1) {
            currentPageIndex++;
        }
    }

    @Override
    public void prevPage() {
        if (currentPageIndex > 0) {
            currentPageIndex--;
        }
    }

    /** Updates the displayed content in the container */
    private void updatePage(JPanel container) {
        // remove only whatever’s in CENTER
        BorderLayout layout = (BorderLayout) container.getLayout();
        Component oldCenter = layout.getLayoutComponent(BorderLayout.CENTER);
        if (oldCenter != null) {
            container.remove(oldCenter);
        }
        // remove NORTH (title) similarly if you swap it
        Component oldNorth = layout.getLayoutComponent(BorderLayout.NORTH);
        if (oldNorth != null) {
            container.remove(oldNorth);
        }

        // now re-add title and body
        ContentPage page = pages.get(currentPageIndex);

        // Title
        container.add(buildTitleLabel(page), BorderLayout.NORTH);

        // Body + image/video
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        // Handle IMAGE with exception handling
        if (page.getMediaType() == ContentPage.MediaType.IMAGE) {
            try {
                body.add(buildImagePanel(page));
            } catch (ResourceNotFoundException ex) {
                body.add(errorPanel(ex.getMessage()));
            }
        } else if (page.getMediaType() == ContentPage.MediaType.VIDEO) {
            body.add(buildVideoPanel(page));
        }

        body.add(buildBodyComponent(page));    // image or video or text
        body.add(buildPageNumberLabel());      // "Page x/y"
        container.add(body, BorderLayout.CENTER);

        updateNavButtons();
        container.revalidate();
        container.repaint();
    }

    /** Constructs the label displaying the current page title */
    private JLabel buildTitleLabel(ContentPage p) {
        JLabel title = new JLabel(p.getTitle(), JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }

    /** Constructs the label showing current page number */
    private JLabel buildPageNumberLabel()  {
        JLabel pageNumberLabel = new JLabel("Page " + (currentPageIndex + 1) + " / " + pages.size(), JLabel.CENTER);
        pageNumberLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        pageNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return pageNumberLabel;
    }

    /** Loads image content from resources into a component */
    private JComponent buildImagePanel(ContentPage p) throws ResourceNotFoundException {
        String name = p.getMediaName();
        URL url = getClass().getResource("/resources/images/" + name);
        if (url == null) {
            throw new ResourceNotFoundException("Image not found: " + name);
        }

        ImageIcon icon = new ImageIcon(url);
        JLabel img = new JLabel(icon);
        img.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.add(img);
        wrapper.add(Box.createRigidArea(new Dimension(0, 15)));
        return wrapper;
    }

     /** Loads video component as clickable label */
    private JComponent buildVideoPanel(ContentPage p)  {
        // Implementation for building video panel
        String name = p.getMediaName();
        JLabel videoLabel = new JLabel("[Play Video: " + name + "]", JLabel.CENTER);
        videoLabel.setFont(new Font("SansSerif", Font.ITALIC, 18));
        videoLabel.setForeground(Color.BLUE.darker());
        videoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        videoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        videoLabel.setToolTipText("Click to open video");

        videoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    URL videoUrl = getClass().getResource("/resources/videos/" + name);
                    if (videoUrl != null) {
                        Desktop.getDesktop().browse(videoUrl.toURI());
                    } else {
                        JOptionPane.showMessageDialog(LearningModulePanel.this, "Video not found: " + name);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LearningModulePanel.this, "Error opening video: " + ex.getMessage());
                }
            }
        });

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.add(videoLabel);
        wrapper.add(Box.createRigidArea(new Dimension(0, 15)));
        return wrapper;
    }

    /** Builds the main scrolling content body */
    private JScrollPane buildBodyComponent(ContentPage p) {
        JEditorPane text = new JEditorPane();
        text.setContentType("text/html");
        text.setEditable(false);
        text.setOpaque(false);
        text.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        text.setFont(new Font("SansSerif", Font.PLAIN, 18));
        // HTML-wrapped body for justification
        text.setText("<html><div style='text-align:justify;'>" +
            p.getBody().replace("\n", "<br>") +
            "</div></html>");
        JScrollPane scroll = new JScrollPane(text);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return scroll;
    }

     /** Enables or disables navigation buttons based on current position */
    private void updateNavButtons() {
        btnPrev.setEnabled(currentPageIndex > 0);
        btnFirst.setEnabled(currentPageIndex > 0);
        btnNext.setEnabled(currentPageIndex < pages.size() - 1);
        btnLast.setEnabled(currentPageIndex < pages.size() - 1);
    }

    /** Displays an error panel if resource is not found */
    private JPanel errorPanel(String msg) {
        JPanel p = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel(msg, SwingConstants.CENTER);
        lbl.setForeground(Color.RED);
        p.add(lbl, BorderLayout.CENTER);
        return p;
    }
}