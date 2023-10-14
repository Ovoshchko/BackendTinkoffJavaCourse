package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Test for task 3")
public class Task3Test {

    @Test
    @DisplayName("--True valid input test")
    void trueValidInputTest() {

        int[] nestedArray = {1, 2, 3, 4};
        int[] investedArray = {0, 7, 9};

        assertTrue(Task3.isNestable(nestedArray, investedArray));
    }

    @Test
    @DisplayName("--True valid empty first array input")
    void trueValidEmptyInputTest() {

        int[] nestedArray = {};
        int[] investedArray = {1, 2};

        assertTrue(Task3.isNestable(nestedArray, investedArray));
    }

    @Test
    @DisplayName("--False valid input data")
    void falseValidInputTest() {

        int[] nestedArray = {-1, 3, 4 ,9};
        int[] investedArray = {0, 4, 6};

        assertFalse(Task3.isNestable(nestedArray, investedArray));
    }

    @Test
    @DisplayName("--False failed equal data")
    void falseValidEqualInputTest() {

        int[] nestedArray = {1, 2, 3};
        int[] investedArray = {1, 2, 3};

        assertFalse(Task3.isNestable(nestedArray, investedArray));
    }

    @Test
    @DisplayName("--False invalid input")
    void falseInvalidInputTest() {

        int[] nestedArray = null;
        int[] investedArray = {1};

        assertFalse(Task3.isNestable(nestedArray, investedArray));
    }

    @Test
    @DisplayName("--False valid empty second array input")
    void falseValidEmptyInputTest() {

        int[] nestedArray = {1, 2};
        int[] investedArray = {};

        assertFalse(Task3.isNestable(nestedArray, investedArray));
    }
}
