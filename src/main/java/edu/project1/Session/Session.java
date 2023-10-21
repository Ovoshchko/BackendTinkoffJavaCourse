package edu.project1.Session;

import edu.project1.Dictionary.WordBank;
import edu.project1.Results.FailedResult;
import edu.project1.Results.GiveUpResult;
import edu.project1.Results.RepeatResult;
import edu.project1.Results.ResultInterface;
import edu.project1.Results.SuccessfulResult;
import java.util.HashSet;
import java.util.Set;

public class Session {

    private final String word;
    private final int maxAttempts = 6;
    private final Set<Character> userAnswers;
    private int failedAttempts = 0;

    public Session(String word) {
        this.word = word;
        userAnswers = new HashSet<>();
    }

    public ResultInterface guess(char userLetter) {

        if (userAnswers.contains(userLetter)) {
            return new RepeatResult(word, maxAttempts, failedAttempts, userAnswers);
        }

        userAnswers.add(userLetter);

        if (word.indexOf(userLetter) < 0) {
            failedAttempts += 1;
            return new FailedResult(word, maxAttempts, failedAttempts, userAnswers);
        }

        return new SuccessfulResult(word, maxAttempts, failedAttempts, userAnswers);
    }

    public ResultInterface giveUp() {
        failedAttempts = maxAttempts;
        return new GiveUpResult(word, maxAttempts, failedAttempts, userAnswers);
    }

    public boolean wordIsNotValid() {
        return WordBank.notValidLength(word);
    }

    public boolean isWordGuessed() {

        for (char letter: word.toCharArray()) {
            if (!userAnswers.contains(letter)) {
                return false;
            }
        }

        return true;
    }

    public boolean haveAttempts() {
        return (maxAttempts - failedAttempts) > 0;
    }
}
