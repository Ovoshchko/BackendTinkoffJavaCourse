package edu.project1.Renderer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@DisplayName("RendererImpl Test")
public class RendererImplTest {

    private final static RendererImpl renderer = new RendererImpl();

    @ParameterizedTest
    @DisplayName("--Check encryption")
    @MethodSource("provideForEncryption")
    void getEncryptedWordTest(String word, Set<Character> guessedLetters, String answer) {
        assertEquals(answer, renderer.getEncryptedWord(word, guessedLetters));
    }

    private static Stream<Arguments> provideForEncryption() {
        return Stream.of(
            Arguments.of("ROBOT", new HashSet<>(Arrays.asList('O', 'R')), "RO*O*"),
            Arguments.of("TTTTTTTTT", new HashSet<>(Arrays.asList('R', 'T')), "TTTTTTTTT"),
            Arguments.of("KICK", new HashSet<>(Arrays.asList('O', 'R', 'M')), "****")
        );
    }
}
