/**
 * Creator: Abang Muhamad Fikrul Amin Abang Nazaruddin (97907)
 * Contributors: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314)
 * Testers: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314), Ian Nathaniel Chew (99140), Shamrin bin Al Idrus (100973)
 */

// Class: MCQQuestion
// Description: Represents a multiple-choice question with 4 answer options.


// Multiple Choice Question class (inherits from Question)
public class MCQQuestion extends Question {
    private String[] options;
    private String correctAnswer;

    // Constructor: sets question text, answer options, and correct answer
    public MCQQuestion(String questionText, String[] options, String correctAnswer) {
        super(questionText); // calls the constructor of the abstract base class
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Return all  options (A, B, C, D)
    @Override
    public String[] getOptions() {
        return options;
    }

    // Check if user's answer matches the correct one
    @Override
    public boolean isCorrect(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}
