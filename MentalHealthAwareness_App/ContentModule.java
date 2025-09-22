/**
 * Creator: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314)
 * Testers: Ian Nathaniel Chew (99140), Abang Muhamad Fikrul Amin Abang Nazaruddin (97907), Shamrin bin Al Idrus (100973)
 */

// Class: ContentModule
// Description: Displays mental health learning tips with images. With next/back navigation.

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Learning Module that displays mental health tips with images
public class ContentModule extends JPanel implements ContentDisplayable {
    private List<String> texts;
    private List<String> imagePaths;
    private int currentIndex = 0;
    private StartApp appRef;
    private Image backgroundImage;

    // ----------------------------------------
    // Constructor
    public ContentModule(StartApp appRef) {
        this.appRef = appRef;
        setName("Learning");
        setPreferredSize(new Dimension(360, 640));

        // Load background image
        try {
            backgroundImage = new ImageIcon("images/tips_bg.jpg").getImage();
        } catch (Exception e) {
            System.out.println("Background image not found.");
        }

        setLayout(new BorderLayout());
        loadContent(); // Load text + image paths
        updateContentPanel(); // Show current page
    }

    // ----------------------------------------
    // Paint background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // ----------------------------------------
    // Load tips and image file paths
    private void loadContent() {
        texts = new ArrayList<>();
        imagePaths = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            texts.add("Mental Health Tip " + i + ":\n\n" + switch (i) {
                case 1 -> "✨ Take breaks during your day to rest and recharge.\n\nThese short pauses help lower stress, reset your brain, and improve your focus throughout the day.";
                case 2 -> "😴 Get 7–9 hours of sleep each night to support emotional regulation and focus.\n\nSleep isn’t just rest — it’s mental recovery time that improves your mood, memory, and resilience.";
                case 3 -> "🧘 Practice deep breathing or mindfulness for just 5 minutes a day to reduce anxiety.\n\nBeing present helps calm racing thoughts and makes you feel more in control and grounded.";
                case 4 -> "🗣️ Talk to someone you trust when you're feeling overwhelmed. You're not alone.\n\nOpening up allows emotions to flow instead of building up, and reminds you that you’re supported.";
                case 5 -> "🚶 Stay active! Even a short walk can boost mood and mental clarity.\n\nMovement releases endorphins — your brain’s natural happiness chemicals. Try a walk, stretch, or dance!";
                case 6 -> "📵 Limit screen time and take breaks from social media.\n\nToo much screen time can drain focus and increase anxiety. Stepping back helps you reconnect with yourself.";
                case 7 -> "💧 Stay hydrated and eat nutritious meals — your body and brain are connected.\n\nFood is fuel. A healthy body supports a healthier, more stable mood and clearer thoughts.";
                case 8 -> "🎨 Do something creative — drawing, writing, or music can be great emotional outlets.\n\nCreative expression helps you process emotions in a healthy, empowering way.";
                case 9 -> "🌞 Practice gratitude daily. Noticing small positives can shift your mindset.\n\nTry writing 3 good things at the end of the day. Gratitude rewires the brain toward positivity.";
                default -> "💌 Don't be afraid to seek help. Therapy and support groups can make a big difference.\n\nYou don’t have to do it all alone. Asking for help is a strength — never a weakness.";
            });
            imagePaths.add("images/img" + i + ".jpg");
        }
    }

    // ----------------------------------------
    // Update display with current tip content
    private void updateContentPanel() {
        removeAll();

        // === Text + Image Panel ===
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        contentPanel.setOpaque(false);

        // Image
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        try {
            ImageIcon icon = new ImageIcon(imagePaths.get(currentIndex));
            Image scaled = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        } catch (Exception e) {
            imageLabel.setText("[Image not found]");
        }

        // Tip Text
        JTextArea tipText = new JTextArea(texts.get(currentIndex));
        tipText.setLineWrap(true);
        tipText.setWrapStyleWord(true);
        tipText.setEditable(false);
        tipText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tipText.setBackground(Color.WHITE);
        tipText.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scrollPane = new JScrollPane(tipText);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Page Counter
        JLabel pageLabel = new JLabel("Page " + (currentIndex + 1) + " of " + texts.size(), SwingConstants.CENTER);
        pageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(imageLabel);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(scrollPane);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(pageLabel);

        // === Buttons ===
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setOpaque(false);

        JButton backBtn = createRoundedButton("⬅ Back", new Color(255, 240, 200));
        JButton menuBtn = createRoundedButton("🏠 Main Menu", new Color(204, 255, 204));
        JButton nextBtn = createRoundedButton("Next ➡", new Color(204, 229, 255));

        buttonPanel.add(backBtn);
        buttonPanel.add(menuBtn);
        buttonPanel.add(nextBtn);

        backBtn.setEnabled(currentIndex > 0);
        nextBtn.setEnabled(currentIndex < texts.size() - 1);

        // Button Actions
        backBtn.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateContentPanel();
            }
        });

        nextBtn.addActionListener(e -> {
            if (currentIndex < texts.size() - 1) {
                currentIndex++;
                updateContentPanel();
            }
        });

        menuBtn.addActionListener(e -> appRef.switchPanel("Dashboard"));

        // Final layout
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    // ----------------------------------------
    // Create rounded styled buttons
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
        button.setPreferredSize(new Dimension(100, 36));
        return button;
    }

    // ----------------------------------------
    // Interface method: go to next page
    @Override
    public void nextPage() {
        if (currentIndex < texts.size() - 1) {
            currentIndex++;
            updateContentPanel();
        }
    }

    // ----------------------------------------
    // Interface method: go to previous page
    @Override
    public void prevPage() {
        if (currentIndex > 0) {
            currentIndex--;
            updateContentPanel();
        }
    }

    // ----------------------------------------
    // Interface method: return this panel
    @Override
    public JPanel getCurrentPagePanel() {
        return this;
    }
}
