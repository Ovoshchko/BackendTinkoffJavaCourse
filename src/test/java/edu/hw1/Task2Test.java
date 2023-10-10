package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for Task 2")
public class Task2Test {

    @Test
    @DisplayName("--Correct input test")
    void countDigitsTest() {

        String[] inputNumbers = {"1", "234567", "3214", "-1098", "-9", "0"};

        for(String input: inputNumbers) {
            assertEquals(input.replace("-", "").length(), Task2.countDigits(Integer.parseInt(input)));
        }

    }

}
