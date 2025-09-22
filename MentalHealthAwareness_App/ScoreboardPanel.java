/**
 * Creator: Shamrin bin Al Idrus (100973)
 * Contributors: Ian Nathaniel Chew (99140)
 * Testers: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314), Ian Nathaniel Chew (99140), Abang Muhamad Fikrul Amin Abang Nazaruddin (97907)
 */

// Class: ScoreboardPanel
// Description: Displays saved scores from file, sorted by score, with medal icons and a background.

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Scoreboard screen that displays top scores with medals
public class ScoreboardPanel extends JPanel {
    private ScoreManager scoreManager;
    private Image backgroundImage;

    // ----------------------------------------
    // Constructor: Sets up layout, title, score list, and back button
    public ScoreboardPanel() {
        setName("Scoreboard");
        setPreferredSize(new Dimension(360, 640));
        setLayout(new BorderLayout());

        // Load background image
        try {
            backgroundImage = new ImageIcon("images/scoreboard_bg.jpg").getImage();
        } catch (Exception e) {
            System.out.println("Background image not found.");
        }

        scoreManager = new ScoreManager();

        // Title
        JLabel titleLabel = new JLabel("Hall of Fame", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK); 
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Score list panel
        JPanel scoreListPanel = new JPanel();
        scoreListPanel.setLayout(new BoxLayout(scoreListPanel, BoxLayout.Y_AXIS));
        scoreListPanel.setOpaque(false);
        scoreListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<String> scores = scoreManager.loadScores();

        if (scores.isEmpty()) {
            JLabel noScore = new JLabel("No scores yet!", SwingConstants.CENTER);
            noScore.setFont(new Font("SansSerif", Font.ITALIC, 16));
            noScore.setForeground(Color.DARK_GRAY);
            noScore.setAlignmentX(Component.CENTER_ALIGNMENT);
            scoreListPanel.add(noScore);
        } else {
            int rank = 1;
            for (String line : scores) {
                JPanel card = createScoreCard(line, rank);
                scoreListPanel.add(card);
                scoreListPanel.add(Box.createVerticalStrut(8)); // space between cards
                rank++;
            }
        }

        // Scroll pane for score list
        JScrollPane scrollPane = new JScrollPane(scoreListPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Back to Menu button
        JButton backBtn = createRoundedButton("🏠 Main Menu", new Color(204, 255, 204));
        backBtn.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (topFrame instanceof StartApp) {
                ((StartApp) topFrame).switchPanel("Dashboard");
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        bottomPanel.add(backBtn);

        // Add components to layout
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // ----------------------------------------
    // Creates a card panel to display a single score with rank/medal
    private JPanel createScoreCard(String line, int rank) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 50));
        panel.setBackground(new Color(255, 255, 255, 220)); // soft white
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        String medal = getMedal(rank);

        JLabel medalLabel = new JLabel(medal, SwingConstants.CENTER);
        medalLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        medalLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JLabel scoreLabel = new JLabel(line);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        scoreLabel.setForeground(Color.DARK_GRAY);

        panel.add(medalLabel, BorderLayout.WEST);
        panel.add(scoreLabel, BorderLayout.CENTER);

        return panel;
    }

    // ----------------------------------------
    // Returns medal emoji based on rank
    private String getMedal(int rank) {
        return switch (rank) {
            case 1 -> "🥇";
            case 2 -> "🥈";
            case 3 -> "🥉";
            default -> "";
        };
    }

    // ----------------------------------------
    // Creates a rounded button with custom color
    private JButton createRoundedButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(Color.GRAY);
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
        };
        button.setBackground(bgColor);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setPreferredSize(new Dimension(140, 36));
        return button;
    }

    // ----------------------------------------
    // Paints the background image if available
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
