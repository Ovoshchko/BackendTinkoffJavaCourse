package edu.project1.Results;

import java.util.Set;

public interface ResultInterface {

    String wordToGuess();

    int maxAttempts();

    int failedAttempts();

    Set<Character> userAnswers();

    String message();

}
