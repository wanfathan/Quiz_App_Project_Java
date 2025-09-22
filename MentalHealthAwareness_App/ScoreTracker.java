/**
 * Creator: Shamrin bin Al Idrus (100973)
 * Contributors: Ian Nathaniel Chew (99140)
 * Testers: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314), Ian Nathaniel Chew (99140), Abang Muhamad Fikrul Amin Abang Nazaruddin (97907)
 */

// Interface: ScoreTracker
// Description: Interface for saving and loading scores. Implemented by ScoreManager.

import java.util.List;

// Interface for managing score data
public interface ScoreTracker {

    // Save a score with player name and total
    void saveScore(String name, int score, int total);

    // Load list of scores from storage
    List<String> loadScores();
}
