package edu.hw3.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtbashCipherTest {

    private final AtbashCipher atbashCipher = new AtbashCipher();

    @ParameterizedTest
    @DisplayName("--Valid Input Test")
    @MethodSource("getValidStrings")
    void validStringsTest(String input, String answer) {
        assertEquals(answer, atbashCipher.getEncrypted(input));
    }

    @ParameterizedTest
    @DisplayName("--Invalid Input Test")
    @NullAndEmptySource
    void invlaidStringsTest(String input) {
        assertEquals("", atbashCipher.getEncrypted(input));
    }

    private static Stream<Arguments> getValidStrings() {
        return Stream.of(
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of("ABCD", "ZYXW"),
            Arguments.of("./././././", "./././././"),
            Arguments.of("a0a0a0", "z0z0z0"),
            Arguments.of("dWa", "wDz")
        );
    }
}
