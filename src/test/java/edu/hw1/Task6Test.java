package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for Task 6")
public class Task6Test {

    @ParameterizedTest
    @DisplayName("--Valid inputs test")
    @CsvSource(value = {
        "1001, 4",
        "1345, 7",
        "3524, 3",
        "7456, 3",
        "6174, 0"
    })
    void validInputTest(int input, int expected) {
        assertEquals(expected, Task6.countK(input));
    }

    @ParameterizedTest
    @DisplayName("--Invalid input")
    @ValueSource(ints = {1000, 1, -4, 10000})
    void invalidInputTest(int input) {
        assertEquals(-1, Task6.countK(input));
    }
}
