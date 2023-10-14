package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test for Task 5")
public class Task5Test {

    @ParameterizedTest
    @DisplayName("--True valid straight inputs")
    @ValueSource(ints = {101, 234565432, 22})
    void trueValidStraightInputTest(Integer input) {
        assertTrue(Task5.isPalindromeDescendant(input));
    }

    @ParameterizedTest
    @DisplayName("--True valid descendant inputs")
    @ValueSource(ints = {1203, 600321, 73434})
    void trueValidDescendantInputTest(Integer input) {
        assertTrue(Task5.isPalindromeDescendant(input));
    }

    @ParameterizedTest
    @DisplayName("--False valid inputs")
    @ValueSource(ints = {123456, 342, 12219})
    void falseValidInputs(Integer input) {
        assertFalse(Task5.isPalindromeDescendant(input));
    }

    @ParameterizedTest
    @DisplayName("--False invalid data")
    @ValueSource(ints = {1, 0})
    void falseInvalidInputTest(Integer input) {
        assertFalse(Task5.isPalindromeDescendant(input));
    }

    @ParameterizedTest
    @DisplayName("--False null input")
    @NullSource
    void nullInvalidInputTest(Integer input) {
        assertFalse(Task5.isPalindromeDescendant(input));
    }
}
