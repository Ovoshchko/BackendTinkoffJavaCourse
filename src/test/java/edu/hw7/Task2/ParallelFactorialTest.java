package edu.hw7.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ParallelFactorialTest {

    private final ParallelFactorial parallelFactorial = new ParallelFactorial();

    @ParameterizedTest
    @DisplayName("--Normal Value Test")
    @CsvSource({"0, 1", "1, 1", "5, 120", "10, 3628800"})
    void testFactorial(int input, int expected) {
        int result = parallelFactorial.countFactorial(input);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @DisplayName("--Negative number Test")
    @CsvSource({"-1", "-5", "-10"})
    void testFactorialNegative(int input) {
        assertThrows(IllegalArgumentException.class, () -> parallelFactorial.countFactorial(input));
    }
}
