package edu.hw3.Task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RomanConverterTest {

    private final RomanConverter romanConverter = new RomanConverter();

    @ParameterizedTest
    @DisplayName("--Valid number inputs test")
    @CsvSource(value = {
        "2, II",
        "16, XVI",
        "1001, MI",
        "2876, MMDCCCLXXVI"
    })
    void validInputTest(int arabic, String answer) {
        Assertions.assertEquals(answer, romanConverter.convertToRoman(arabic));
    }

    @ParameterizedTest
    @DisplayName("--Invalid numbers input test")
    @ValueSource(ints = {-1, 0, 5000, -30})
    void invalidInputTest(int arabic) {
        Assertions.assertEquals("", romanConverter.convertToRoman(arabic));
    }
}
