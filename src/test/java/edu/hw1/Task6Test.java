package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for Task 6")
public class Task6Test {

    @Test
    @DisplayName("--Valid inputs test")
    void validInputTest() {

        int[] inputNumbers = {1001, 1345, 3524, 7456, 6174};
        int[] answers = {4, 7, 3, 3, 0};

        for (int i = 0; i < inputNumbers.length; i++) {
            assertEquals(answers[i], Task6.countK(inputNumbers[i]));
        }
    }

    @Test
    @DisplayName("--Invalid input")
    void invalidInputTest() {

        int[] inputNumbers = {1000, 1, -4, 10000};

        for (int input: inputNumbers) {
            assertEquals(Task6.countK(input), -1);
        }
    }
}
