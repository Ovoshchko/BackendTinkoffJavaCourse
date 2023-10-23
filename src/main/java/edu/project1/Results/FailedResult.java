package edu.project1.Results;

import java.util.Set;

public record FailedResult(
    String wordToGuess, int maxAttempts, int failedAttempts, Set<Character> userAnswers) implements ResultInterface {

    public String message() {
        return String.format("Нет такой буквы. Вы потратили %2d из %2d попыток", failedAttempts(), maxAttempts());
    }
}
