/**
 * Creator: Abang Muhamad Fikrul Amin Abang Nazaruddin (97907)
 * Contributors: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314)
 * Testers: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314), Ian Nathaniel Chew (99140), Shamrin bin Al Idrus (100973)
 */

// Class: TrueFalseQuestion
// Description: Represents a true/false question and validates boolean answers.

// True/False type question (inherits from abstract class Question)
public class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    // Constructor: set question text and correct answer (true/false)
    public TrueFalseQuestion(String questionText, boolean correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }

    // Return the answer options: True and False
    @Override
    public String[] getOptions() {
        return new String[]{"True", "False"};
    }

    // Check if selected answer matches the correct boolean value
    @Override
    public boolean isCorrect(String answer) {
        return Boolean.parseBoolean(answer.toLowerCase()) == correctAnswer;
    }
}
