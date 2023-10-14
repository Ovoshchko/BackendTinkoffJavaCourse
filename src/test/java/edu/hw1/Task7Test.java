package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for Task 7")
public class Task7Test {

    @ParameterizedTest
    @DisplayName("--Valid input for rotateLeft")
    @CsvSource(value = {
        "16, 1, 1",
        "17, 2, 6",
        "234, 3, 87"
    })
    void validInputRotateLeftTest(int input, int shift, int expected) {
        assertEquals(expected, Task7.rotateLeft(input, shift));
    }

    @ParameterizedTest
    @DisplayName("--Valid input for rotateRight")
    @CsvSource(value = {
        "8, 1, 4",
        "123, 1, 125",
        "128, 39, 1"
    })
    void validInputRotateRightTest(int input, int shift, int expected) {
        assertEquals(expected, Task7.rotateRight(input, shift));
    }

    @ParameterizedTest
    @DisplayName("--Invalid input for both functions")
    @ValueSource(ints = {-1, 0, -1899})
    void invalidInputTest(int input) {
        assertEquals(0, Task7.rotateLeft(input, 3));
    }

}
