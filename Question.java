/*
 * Created by Muhammad Kalil bin Sammat (99955)
 * Tested by Lee Hao Ming (99451)
 * Description:
 * Defines the contract for a quiz question, including checking answers,
 * retrieving the prompt, and listing available options.
 */

public interface Question {
    /**
     * Checks whether the provided answer matches the correct one.
     *
     * @param userAnswer The answer input by the user (case-insensitive).
     * @return true if the answer is correct; false otherwise.
     */
    boolean checkAnswer(String userAnswer);

    /**
     * Retrieves the question prompt.
     * This may include plain text or simple HTML formatting.
     *
     * @return The text of the question.
     */
    String getQuestionText();

    /**
     * Gets the list of possible answer options for this question.
     *
     * @return An array of strings representing each selectable option.
     */
    String[] getOptions();
}
