/*
 * Created by Muhammad Kalil bin Sammat (99955)
 * Tested by Lee Hao Ming (99451)
 * Description:
 * Interface for managing and interacting with a quiz module.
 * Responsible for rendering quiz UI, capturing user answers, and calculating the score.
 */

import javax.swing.JPanel;

public interface IQuizModule {
    /** 
     * @return a Swing panel that runs the quiz: 
     *         shows one question at a time, collects answers, 
     *         and on completion shows the score & message.
     */
    JPanel getQuizPanel();

    /** @return the user’s final score percentage (0–100). */
    int getScorePercentage();
}