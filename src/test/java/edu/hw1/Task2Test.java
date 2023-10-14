package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for Task 2")
public class Task2Test {

    @ParameterizedTest
    @DisplayName("--Correct input test")
    @ValueSource(strings = {"1", "234567", "3214", "-1098", "-9", "0"})
    void countDigitsTest(String input) {
        assertEquals(input.replace("-", "").length(), Task2.countDigits(Integer.parseInt(input)));
    }

}
