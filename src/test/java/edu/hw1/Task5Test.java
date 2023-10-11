package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test for Task 5")
public class Task5Test {

    @Test
    @DisplayName("--True valid straight inputs")
    void trueValidStraightInputTest() {

        Integer[] inputNumbers = {101, 234565432, 22};

        for (Integer current: inputNumbers) {
            assertTrue(Task5.isPalindromeDescendant(current));
        }
    }

    @Test
    @DisplayName("--True valid descendant inputs")
    void trueValidDescendantInputTest() {

        Integer[] inputNumbers = {1203, 600321, 73434};

        for (Integer current: inputNumbers) {
            assertTrue(Task5.isPalindromeDescendant(current));
        }
    }

    @Test
    @DisplayName("--False valid inputs")
    void falseValidInputs() {

        Integer[] inputNumbers = {123456, 342, 12219};

        for (Integer current: inputNumbers) {
            assertFalse(Task5.isPalindromeDescendant(current));
        }
    }

    @Test
    @DisplayName("--False invalid data")
    void falseInvalidData() {

        Integer[] inputNumbers = {1, 0, null};

        for (Integer current: inputNumbers) {
            assertFalse(Task5.isPalindromeDescendant(current));
        }
    }
}
