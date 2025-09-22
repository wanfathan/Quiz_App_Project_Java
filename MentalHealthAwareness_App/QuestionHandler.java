/**
 * Creator: Abang Muhamad Fikrul Amin Abang Nazaruddin (97907)
 * Contributors: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314)
 * Testers: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314), Ian Nathaniel Chew (99140), Shamrin bin Al Idrus (100973)
 */

// Interface: QuestionHandler
// Description: Interface for quiz modules. Defines methods for loading questions and tracking score.


// Interface for handling quiz question logic
public interface QuestionHandler {

    // Load all quiz questions
    void loadQuestions();

    // Start the quiz flow
    void startQuiz();

    // Return current score
    int getScore();
}
