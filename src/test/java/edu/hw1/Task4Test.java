package edu.hw1;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for Task 4")
public class Task4Test {

    @ParameterizedTest
    @DisplayName("--Valid even input")
    @CsvSource(value = {
        "oN, No",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "21436587, 12345678"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void validEvenInputTest(String input, String expected) {
        assertEquals(expected, Task4.fixString(input));
    }

    @ParameterizedTest
    @DisplayName("--Valid odd input")
    @CsvSource(value = {
        "eYs, Yes",
        "yMs neetcne, My sentence",
        "12345, 21435"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void validOddInputTest(String input, String expected) {
        assertEquals(expected, Task4.fixString(input));
    }

    @ParameterizedTest
    @DisplayName("Valid empty input")
    @NullAndEmptySource
    void validEmptyAndNullInputTest(String input) {
        assertEquals("", Task4.fixString(input));
    }

    @ParameterizedTest
    @DisplayName("Valid input with length = 1")
    @ValueSource(strings = {"1", "a"})
    void validSingleInputTest(String input) {
        assertEquals(input, Task4.fixString(input));
    }

}
