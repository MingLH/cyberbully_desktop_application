/*
 * Created by Ahmad Izzat Immil bin Ahmad Rushdan (101490)
 * Tested by Muhammad Kalil bin Sammat (99955)
 * Description
 * Represents a true/false quiz question, storing the prompt and correct boolean answer.
 */

public class TrueFalseQuestion implements Question {
    private final String questionText; // The question prompt
    private final boolean correctAnswer; // The correct answer: true or false

    /**
     * Constructs a TrueFalseQuestion with given text and correct answer.
     *
     * @param questionText The text prompt for the true/false question
     * @param correctAnswer The correct boolean value (true or false)
     */
    public TrueFalseQuestion(String questionText, boolean correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Checks if the user's answer matches the correct boolean answer.
     * Accepts "true" or "false" (case-insensitive).
     *
     * @param userAnswer The user's answer string
     * @return true if the answer is correct; false otherwise
     */
    @Override
    public boolean checkAnswer(String userAnswer) {
        return (userAnswer.equalsIgnoreCase("true")  && correctAnswer)
            || (userAnswer.equalsIgnoreCase("false") && !correctAnswer);
    }

    /**
     * @return The question prompt text
     */
    @Override 
    public String getQuestionText() { 
        return questionText; 
    }

    /**
     * @return The fixed options array: ["True", "False"]
     */
    @Override 
    public String[] getOptions() { 
        return new String[]{"True", "False"}; 
    }
}
