/**
 * Creator: Shamrin bin Al Idrus (100973)
 * Contributors: Ian Nathaniel Chew (99140)
 * Testers: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314), Ian Nathaniel Chew (99140), Abang Muhamad Fikrul Amin Abang Nazaruddin (97907)
 */

// Class: ScoreManager
// Description: Handles saving and loading scores using a text file. Implements ScoreTracker interface.

import java.io.*;
import java.util.*;

// Handles saving and loading scores using a text file
public class ScoreManager implements ScoreTracker {
    private static final String FILE_NAME = "scores.txt"; // File to store scores

    // ----------------------------------------
    // Save a new score to the file
    @Override
    public void saveScore(String name, int score, int total) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(name + " - " + score + "/" + total); // Format: Name - 18/20
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }

    // ----------------------------------------
    // Load all scores from the file and sort them (highest first)
    @Override
    public List<String> loadScores() {
        List<String> scoreList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scoreList.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading scores: " + e.getMessage());
        }

        // Sort scores based on number of correct answers (before the '/')
        scoreList.sort((a, b) -> {
            try {
                int scoreA = Integer.parseInt(a.split(" - ")[1].split("/")[0].trim());
                int scoreB = Integer.parseInt(b.split(" - ")[1].split("/")[0].trim());
                return Integer.compare(scoreB, scoreA); // Highest score first
            } catch (Exception e) {
                return 0;
            }
        });

        return scoreList;
    }
}
