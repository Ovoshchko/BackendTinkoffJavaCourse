package edu.project1;

import edu.project1.Exception.InvalidLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("HangmanGame Test")
public class HangmanGameTest {

    @ParameterizedTest
    @DisplayName("--Not valid words Test")
    @MethodSource("provideInvalidWordsForHangman")
    void inValidWordsTest(HangmanGame hangmanGame)  {
        assertThrows(InvalidLengthException.class, hangmanGame::run);
    }

    private static Stream<Arguments> provideInvalidWordsForHangman() {
        return Stream.of(
            Arguments.of(new HangmanGame("")),
            Arguments.of(new HangmanGame("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT")),
            Arguments.of(new HangmanGame("fdssdff"))
        );
    }
}
