package edu.project1.Results;

import java.util.Set;

public record SuccessfulResult(
    String wordToGuess, int maxAttempts, int failedAttempts, Set<Character> userAnswers) implements ResultInterface {

    public String message() {
        return "Откройте букву!";
    }
}
