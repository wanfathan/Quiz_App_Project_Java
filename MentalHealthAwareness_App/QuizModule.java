/**
 * Creator: Abang Muhamad Fikrul Amin Abang Nazaruddin (97907)
 * Contributors: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314)
 * Testers: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314), Ian Nathaniel Chew (99140), Shamrin bin Al Idrus (100973)
 */

// Class: QuizModule
// Description: Handles quiz logic including question display, answer checking, score tracking, and timer.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Quiz screen with MCQ + True/False questions, score tracking, timer, and final feedback
public class QuizModule extends JPanel implements QuestionHandler {
    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;
    private StartApp appRef;

    private Timer timer;
    private int timeLeft = 20;
    private JLabel timerLabel;

    // ----------------------------------------
    // Constructor
    public QuizModule(StartApp appRef) {
        this.appRef = appRef;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(360, 640));
        setName("Quiz");

        loadQuestions();
        startQuiz();
    }

    // ----------------------------------------
    // Load quiz questions (10 MCQ + 10 T/F)
    @Override
    public void loadQuestions() {
        questions = new ArrayList<>();

        // MCQ questions
        questions.add(new MCQQuestion("Which of these is a symptom of depression?",
            new String[]{"Fever", "Loss of interest", "Increased appetite", "Headache"}, "Loss of interest"));

        questions.add(new MCQQuestion("Mental health affects your...",
            new String[]{"Emotions", "Physical health", "Both", "Neither"}, "Both"));

        questions.add(new MCQQuestion("Which professional can help with mental health?",
            new String[]{"Mechanic", "Psychologist", "Plumber", "Chef"}, "Psychologist"));

        questions.add(new MCQQuestion("Which of these activities can reduce stress?",
            new String[]{"Watching horror movies", "Exercising", "Skipping meals", "Arguing"}, "Exercising"));

        questions.add(new MCQQuestion("Anxiety is a type of...",
            new String[]{"Mental disorder", "Physical injury", "Food allergy", "Fashion trend"}, "Mental disorder"));

        questions.add(new MCQQuestion("Which age group can experience mental illness?",
            new String[]{"Only adults", "Only teenagers", "Anyone", "Only elderly"}, "Anyone"));

        questions.add(new MCQQuestion("How much sleep is generally recommended for good mental health?",
            new String[]{"3–4 hours", "7–9 hours", "10–12 hours", "5–6 hours"}, "7–9 hours"));

        questions.add(new MCQQuestion("What does mindfulness practice help with?",
            new String[]{"Multitasking", "Staying present", "Getting rich", "Forgetting things"}, "Staying present"));

        questions.add(new MCQQuestion("Burnout is usually caused by...",
            new String[]{"Too much rest", "Prolonged stress", "Eating too much", "Boredom"}, "Prolonged stress"));

        questions.add(new MCQQuestion("What can you do if you feel overwhelmed?",
            new String[]{"Ignore it", "Talk to someone", "Work more", "Stay silent"}, "Talk to someone"));

        // True/False questions
        questions.add(new TrueFalseQuestion("Mental illness is uncommon.", false));
        questions.add(new TrueFalseQuestion("Talking to a friend can improve your mental health.", true));
        questions.add(new TrueFalseQuestion("Exercise has no effect on mental health.", false));
        questions.add(new TrueFalseQuestion("Only women suffer from anxiety.", false));
        questions.add(new TrueFalseQuestion("Suicide is never related to mental illness.", false));
        questions.add(new TrueFalseQuestion("Therapy can help improve mental health.", true));
        questions.add(new TrueFalseQuestion("Mental health is just about emotions.", false));
        questions.add(new TrueFalseQuestion("It's okay to ask for help when feeling low.", true));
        questions.add(new TrueFalseQuestion("Social media can sometimes negatively affect mental health.", true));
        questions.add(new TrueFalseQuestion("Mental health recovery is impossible.", false));

        Collections.shuffle(questions); // Shuffle question order
    }

    // ----------------------------------------
    // Display next question or show final score
    @Override
    public void startQuiz() {
        removeAll();

        if (currentIndex < questions.size()) {
            Question currentQuestion = questions.get(currentIndex);

            // === Top Panel (question number + timer) ===
            JPanel topPanel = new JPanel(new GridLayout(1, 2));
            topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            topPanel.setBackground(new Color(240, 255, 255));

            JLabel questionNum = new JLabel("Question " + (currentIndex + 1) + "/" + questions.size());
            questionNum.setFont(new Font("SansSerif", Font.BOLD, 14));
            questionNum.setHorizontalAlignment(SwingConstants.LEFT);

            timerLabel = new JLabel("⏱ 20s");
            timerLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            timerLabel.setForeground(Color.RED);
            timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            topPanel.add(questionNum);
            topPanel.add(timerLabel);

            // === Question Panel ===
            JPanel questionPanel = new JPanel(new BorderLayout());
            questionPanel.setBackground(new Color(250, 250, 250));
            questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            JTextArea questionText = new JTextArea(currentQuestion.getText());
            questionText.setWrapStyleWord(true);
            questionText.setLineWrap(true);
            questionText.setEditable(false);
            questionText.setFocusable(false);
            questionText.setBackground(Color.WHITE);
            questionText.setFont(new Font("Arial", Font.PLAIN, 15));
            questionText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            questionPanel.add(questionText, BorderLayout.CENTER);

            // === Answer Panel (option buttons) ===
            JPanel answerPanel = new JPanel();
            answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
            answerPanel.setOpaque(false);
            answerPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

            String[] options = currentQuestion.getOptions();
            for (String option : options) {
                JButton btn = createOptionButton(option);
                answerPanel.add(btn);
                answerPanel.add(Box.createVerticalStrut(8));
            }

            // === Start timer ===
            timeLeft = 20;
            updateTimer();
            if (timer != null && timer.isRunning()) timer.stop();
            timer = new Timer(1000, e -> {
                timeLeft--;
                updateTimer();
                if (timeLeft <= 0) {
                    timer.stop();
                    nextQuestion();
                }
            });
            timer.start();

            add(topPanel, BorderLayout.NORTH);
            add(questionPanel, BorderLayout.CENTER);
            add(answerPanel, BorderLayout.SOUTH);
        } else {
            showFinalScore();
        }

        revalidate();
        repaint();
    }

    // ----------------------------------------
    // Create styled answer button
    private JButton createOptionButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btn.setBackground(new Color(204, 255, 204));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        btn.setMaximumSize(new Dimension(220, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(new QuizSubmitHandler());
        return btn;
    }

    // ----------------------------------------
    // Update timer label text
    private void updateTimer() {
        timerLabel.setText("⏱ " + timeLeft + "s");
    }

    // ----------------------------------------
    // Go to next question
    private void nextQuestion() {
        currentIndex++;
        startQuiz();
    }

    // ----------------------------------------
    // Handle answer submission
    private class QuizSubmitHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timer != null) timer.stop();
            String userAnswer = e.getActionCommand();
            Question currentQuestion = questions.get(currentIndex);
            if (currentQuestion.isCorrect(userAnswer)) {
                score++;
            }
            nextQuestion();
        }
    }

    // ----------------------------------------
    // Show final score screen
    private void showFinalScore() {
        removeAll();

        int percentage = (int) (((double) score / questions.size()) * 100);
        String feedback = getMotivationalMessage(percentage);

        String playerName = JOptionPane.showInputDialog(this, "Enter your name:", "Save Score", JOptionPane.PLAIN_MESSAGE);
        if (playerName == null || playerName.trim().isEmpty()) playerName = "Anonymous";

        ScoreManager scoreManager = new ScoreManager();
        scoreManager.saveScore(playerName.trim(), score, questions.size());

        // Final screen panel with background image
        JPanel panel = new JPanel(new GridBagLayout()) {
            private Image bg = new ImageIcon("images/quiz_complete_bg.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel resultLabel = new JLabel("Quiz Complete!");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel scoreLabel = new JLabel("Score: " + score + "/" + questions.size() + " (" + percentage + "%)");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel feedbackLabel = new JLabel(feedback);
        feedbackLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        feedbackLabel.setForeground(Color.BLUE);

        // Main menu button (styled)
        JButton backBtn = new JButton("Back to Menu") {
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
        backBtn.setContentAreaFilled(false);
        backBtn.setOpaque(false);
        backBtn.setBackground(new Color(204, 255, 204));
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        backBtn.setPreferredSize(new Dimension(200, 40));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> appRef.switchPanel("Dashboard"));

        // Layout placement
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(resultLabel, gbc);
        gbc.gridy++;
        panel.add(scoreLabel, gbc);
        gbc.gridy++;
        panel.add(feedbackLabel, gbc);
        gbc.gridy++;
        panel.add(backBtn, gbc);

        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // ----------------------------------------
    // Return motivational message based on score %
    private String getMotivationalMessage(int percent) {
        if (percent >= 80) return "Outstanding!";
        else if (percent >= 60) return "That's good!";
        else if (percent >= 40) return "Good try!";
        else if (percent >= 20) return "You can do better!";
        else return "Don't give up!";
    }

    // ----------------------------------------
    // Return current score
    @Override
    public int getScore() {
        return score;
    }
}
