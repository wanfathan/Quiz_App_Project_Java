/**
 * Creator: Abang Muhamad Fikrul Amin Abang Nazaruddin (97907)
 * Contributors:Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314)
 * Testers: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314), Ian Nathaniel Chew (99140), Shamrin bin Al Idrus (100973)
 */

// Abstract Class: Question
// Description: Base class for all questions. Defines common attributes and abstract methods for answer checking.


// Abstract base class for all question types (MCQ, True/False, etc.)
public abstract class Question {
    protected String questionText; // The question text shown to user

    // Constructor: set the question text
    public Question(String questionText) {
        this.questionText = questionText;
    }

    // Return the question text
    public String getText() {
        return questionText;
    }

    // Must be implemented by child classes: return answer options
    public abstract String[] getOptions();

    // Must be implemented by child classes: check if answer is correct
    public abstract boolean isCorrect(String answer);
}
