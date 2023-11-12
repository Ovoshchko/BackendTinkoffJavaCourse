package edu.hw5.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class SubPatternCheckerTest {

    private final SubPatternChecker subPatternChecker = new SubPatternChecker();

    @ParameterizedTest
    @DisplayName("--Substring Checker Test")
    @MethodSource("provideSubstrings")
    void isSubstring(String string, String substring) {
        assertTrue(subPatternChecker.isSubstring(string, substring));
    }

    @ParameterizedTest
    @DisplayName("--Substring Checker Not Test")
    @MethodSource("provideNotSubstrings")
    void isNotSubstring(String string, String substring) {
        assertFalse(subPatternChecker.isSubstring(string, substring));
    }

    private static Stream<Arguments> provideSubstrings() {
        return Stream.of(
            Arguments.of("abcd", "ab"),
            Arguments.of("1223ff", "ff"),
            Arguments.of("fergtrgrae", ""),
            Arguments.of("efrgebdf youfvdbfs", " you")
        );
    }

    private static Stream<Arguments> provideNotSubstrings() {
        return Stream.of(
            Arguments.of("abcd", "ab1"),
            Arguments.of("1223ff345", "ff1"),
            Arguments.of("fergtrgrae", " "),
            Arguments.of("efrgebdf youfvdbfs", "+")
        );
    }
}
