/*
 * Created by Muhammad Kalil bin Sammat (99955)
 * Tested by Lee Hao Ming (99451)
 * Description:
 * Represents a multiple choice question with text, answer options,
 * and the index of the correct answer. Implements the Question interface.
 */

public class MultipleChoiceQuestion implements Question {
    private final String questionText; // The question prompt
    private final String[] options; // Array of answer options
    private final int correctAnswerIndex; // Index of the correct answer (0-based)

    /**
     * Constructor to initialize a multiple choice question.
     *
     * @param questionText The text of the question
     * @param options Array of possible answers
     * @param correctAnswerIndex Index of the correct option in the array
     */
    public MultipleChoiceQuestion(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    /**
     * Checks if the given answer matches the correct one.
     *
     * @param userAnswer The answer provided by the user
     * @return true if correct, false otherwise
     */
    @Override
    public boolean checkAnswer(String userAnswer) {
        for (int i = 0; i < options.length; i++) {
            if (options[i].equalsIgnoreCase(userAnswer.trim())) {
                return i == correctAnswerIndex;
            }
        }
        return false;
    }

    /**
     * @return The text of the question
     */
    @Override 
    public String getQuestionText() { 
        return questionText; 
    }

    /**
     * @return Array of answer options
     */
    @Override 
    public String[] getOptions() { 
        return options; 
    }
}
