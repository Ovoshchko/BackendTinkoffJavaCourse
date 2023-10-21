package edu.project1.Dictionary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@DisplayName("WordBank Class Test")
public class WordBankTest {

    @RepeatedTest(50)
    @DisplayName("--WordBank NotNull getRandomWord test")
    void getRandomWordNotNullTest() {
        assertNotEquals(null, WordBank.getRandomWord());
    }

    @ParameterizedTest
    @DisplayName("--WordBank notValidLength function Test")
    @MethodSource("provideWordsWithDifferentLength")
    void notValidLengthTest(String word, boolean answer) {
        assertEquals(answer, WordBank.notValidLength(word));
    }

    private static Stream<Arguments> provideWordsWithDifferentLength() {
        return Stream.of(
            Arguments.of("SSSSSS", false),
            Arguments.of("PROBABILITY", false),
            Arguments.of("", true),
            Arguments.of("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT", true),
            Arguments.of("frdv", true),
            Arguments.of("FEEDffsdF", true),
            Arguments.of("GED^&(*&^678", true)
        );
    }
}
