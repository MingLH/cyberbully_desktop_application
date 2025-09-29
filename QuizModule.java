/*
 * Created by Muhammad Kalil bin Sammat (99955)
 * Tested by Lee Hao Ming (99451)
 * Description:
 * Factory class that constructs and returns a list of all quiz questions,
 * including both multiple-choice and true/false types.
 */

import java.util.List;
import java.util.ArrayList;

public class QuizModule {
     /**
     * Retrieves the full set of quiz questions.
     *
     * @return a mutable List containing Question objects for the quiz
     *         (10 multiple-choice questions followed by 10 true/false questions).
     */
    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        // --- Multiple-Choice Question (10 questions) --- 
        // Question 1
        questions.add(new MultipleChoiceQuestion(
            "What is cyberbullying?",
            new String[] {
                "Making fun of someone in person", 
                "Using the internet or technology to harass or embarrass someone", 
                "Posting fun pictures online", 
                "Playing games online with strangers"
            }, 
            1
        ));
        // Question 2
        questions.add(new MultipleChoiceQuestion(
            "Which of the following is an example of cyberbullying?",
            new String[] {
                "Sharing memes",
                "Posting someone's private photos without permission",
                "Sending birthday wishes",
                "Commenting nice on a post"
            },
            1
        ));
        // Question 3
        questions.add(new MultipleChoiceQuestion(
            "How might someone feel if they are cyberbullied?",
            new String[] {
                "Happy and relaxed",
                "Encouraged and motivated",
                "Sad, anxious, or scared",
                "Curious and excited"
            },
            2
        ));
        // Question 4
        questions.add(new MultipleChoiceQuestion(
            "What should you do if you're being cyberbullied?",
            new String[] {
                "Keep replying to the bully",
                "Share it with all your followers",
                "Ignore it completely",
                "Save the evidence and tell a trusted adult"
            },
            3
        ));
        // Question 5
        questions.add(new MultipleChoiceQuestion(
            "Which platform can cyberbullying occur on?",
            new String[] {
                "Social media",
                "Messaging apps",
                "Online games",
                "All of the above"
            },
            3
        ));
        // Question 6
        questions.add(new MultipleChoiceQuestion(
            "What role does a bystander play in cyberbullying?",
            new String[] {
                "Starts the bullying",
                "Ignores everything",
                "Sees the bullying and can choose to help",
                "Encourages the bully secretly"
            },
            2
        ));
        // Question 7
        questions.add(new MultipleChoiceQuestion(
            "What is a good way to promote positive online behavior?",
            new String[] {
                "Share gossip",
                "Post kind comments and support others",
                "Join in on bullying",
                "Laugh at others' mistakes"
            },
            1
        ));
        // Question 8
        questions.add(new MultipleChoiceQuestion(
            "Why is it important to report cyberbullying?",
            new String[] {
                "To Get Revenge",
                "To stop the bullying and protect others",
                "To block the internet",
                "To become popular"
            },
            1
        ));
        // Question 9
        questions.add(new MultipleChoiceQuestion(
            "What is digital citizenship?",
            new String[] {
                "Playing video games online",
                "Knowing how to use apps",
                "Using technology responsibly and respectfully",
                "Following influencers"
            },
            2
        ));
        // Question 10
        questions.add(new MultipleChoiceQuestion(
            "Which action is NOT recommended if you witness cyberbullying?",
            new String[] {
                "Join in with the bully",
                "Report the post or message",
                "Support the victim",
                "Talk to a trusted adult"
            },
            0
        ));

        // --- TRUE/FALSE Question (10 questions) ---
        // Question 11
        questions.add(new TrueFalseQuestion(
            "Sharing someone's private messages without permission is a form of cyberbullying.", 
            true
        ));
        // Question 12
        questions.add(new TrueFalseQuestion(
            "Cyberbullying can only happen to celebrities.", 
            false
        ));
        // Question 13
        questions.add(new TrueFalseQuestion(
            "Keeping cyberbullying a secret and not telling anyone is the best option.", 
            false
        ));
        // Question 14
        questions.add(new TrueFalseQuestion(
            "It is okay to joke online even if it hurts someone's feelings.", 
            false
        ));
        // Question 15
        questions.add(new TrueFalseQuestion(
            "Cyberbullying affect a person's mental health.", 
            true
        ));
        // Question 16
        questions.add(new TrueFalseQuestion(
            "Reporting harmful content on social media is a way to help stop cyberbullying.", 
            true
        ));
        // Question 17
        questions.add(new TrueFalseQuestion(
            "Being a silent bystander might make the Cyberbullying worse.", 
            true
        ));
        // Question 18
        questions.add(new TrueFalseQuestion(
            "Promoting kindness online is a part of digital citizenship.", 
            true
        ));
        // Question 19
        questions.add(new TrueFalseQuestion(
            "Blocking someone can help stop cyberbullying.", 
            true
        ));
        // Question 20
        questions.add(new TrueFalseQuestion(
            "You should always double-check before posting or sharing something online.", 
            true
        ));

        return questions;
    }
}