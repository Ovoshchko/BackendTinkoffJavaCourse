package edu.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Tests for task 1")
public class Task1Test {

    @ParameterizedTest
    @DisplayName("--Correct input test")
    @CsvSource(value = {
        "34:34, 2074",
        "20:00, 1200",
        "00:00, 0",
        "656:32, 39392"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void correctTimeTest(String input, int answer) {
        assertEquals(answer, Task1.minutesToSeconds(input));
    }

    @ParameterizedTest
    @DisplayName("--Incorrect input test")
    @ValueSource( strings = {"34:334", "20:500", "-1:00", "65632", "aaa", "aa:59"})
    void incorrectTimeTest(String input) {
        assertEquals(-1, Task1.minutesToSeconds(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void nullAndEmptyTimeTest(String input) {
        assertEquals(-1, Task1.minutesToSeconds(input));
    }
}
