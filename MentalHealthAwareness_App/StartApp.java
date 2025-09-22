/**
 * Creator: Ian Nathaniel Chew (99140)
 * Testers: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314), Abang Muhamad Fikrul Amin Abang Nazaruddin (97907), Shamrin bin Al Idrus (100973)
 */

// Class: StartApp
// Description: Main application window. Uses CardLayout to switch between dashboard, quiz, learning, and scoreboard panels.

import javax.swing.*;
import java.awt.*;

// Main application window using JFrame
public class StartApp extends JFrame implements GUIManager {
    private CardLayout cardLayout;     // Handles panel switching
    private JPanel mainPanel;          // Main container panel

    // ----------------------------------------
    // Constructor: set frame settings
    public StartApp() {
        setTitle("Mental Health Awareness");
        setSize(360, 640); // Smartphone-like resolution
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        initUI(); // Set up GUI
    }

    // ----------------------------------------
    // Initialize user interface
    @Override
    public void initUI() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel dashboardPanel = createDashboardPanel();
        mainPanel.add(dashboardPanel, "Dashboard");

        add(mainPanel);
        setVisible(true);
    }

    // ----------------------------------------
    // Creates the main dashboard screen
    private JPanel createDashboardPanel() {
        // Background panel with custom image
        JPanel panel = new JPanel() {
            private Image background;

            {
                try {
                    background = new ImageIcon("images/background.jpg").getImage();
                } catch (Exception e) {
                    System.out.println("Background image not found.");
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (background != null) {
                    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panel.setLayout(new BorderLayout());

        // Title label
        JLabel title = new JLabel("Mental Health App", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        // Button section
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 90, 20, 90));

        // Create buttons
        JButton quizButton = createDashboardButton("🤖 Start Quiz", new Color(186, 220, 255));
        JButton learnButton = createDashboardButton("📘 Learn Tips", new Color(204, 255, 204));
        JButton scoreButton = createDashboardButton("🏆 Scoreboard", new Color(255, 223, 186));

        // Add click actions
        quizButton.addActionListener(e -> switchTo("Quiz"));
        learnButton.addActionListener(e -> switchTo("Learning"));
        scoreButton.addActionListener(e -> switchTo("Scoreboard"));

        // Add buttons to panel
        buttonPanel.add(quizButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(learnButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(scoreButton);

        // Add to layout
        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    // ----------------------------------------
    // Creates a styled dashboard button
    private JButton createDashboardButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Rounded fill
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                // Black border
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // Border is custom painted above
            }
        };

        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.DARK_GRAY);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 48));

        return button;
    }

    // ----------------------------------------
    // Panel switching logic for "Quiz", "Learning", "Scoreboard"
    private void switchTo(String name) {
        // Remove old panel if it already exists
        Component[] components = mainPanel.getComponents();
        for (Component comp : components) {
            if (name.equals(comp.getName())) {
                mainPanel.remove(comp);
                break;
            }
        }

        // Create the new panel based on selection
        switch (name) {
            case "Learning" -> {
                ContentModule learningPanel = new ContentModule(this);
                learningPanel.setName("Learning");
                mainPanel.add(learningPanel, "Learning");
            }
            case "Quiz" -> {
                QuizModule quizPanel = new QuizModule(this);
                quizPanel.setName("Quiz");
                mainPanel.add(quizPanel, "Quiz");
            }
            case "Scoreboard" -> {
                ScoreboardPanel scoreboardPanel = new ScoreboardPanel();
                scoreboardPanel.setName("Scoreboard");
                mainPanel.add(scoreboardPanel, "Scoreboard");
            }
        }

        cardLayout.show(mainPanel, name);
    }

    // ----------------------------------------
    // Required by GUIManager interface
    @Override
    public void switchPanel(String name) {
        switchTo(name);
    }

    // ----------------------------------------
    // Launch the app
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartApp::new);
    }
}
