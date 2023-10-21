package edu.project1.Results;

import java.util.Set;

public record RepeatResult(
    String wordToGuess, int maxAttempts, int failedAttempts, Set<Character> userAnswers) implements ResultInterface {

    public String message() {
        return "Вы уже вводили эту букву. Попытайтесь снова";
    }
}
