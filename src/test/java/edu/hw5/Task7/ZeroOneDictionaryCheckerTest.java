package edu.hw5.Task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class ZeroOneDictionaryCheckerTest {

    private final ZeroOneDictionaryChecker zeroOneDictionaryChecker = new ZeroOneDictionaryChecker();

    @ParameterizedTest
    @DisplayName("--String has between 1 and 3 chars Test")
    @MethodSource("provideStringsBetweenOneAndThree")
    void matchesBetweenOneThree(String input, boolean answer) {
        assertEquals(answer, zeroOneDictionaryChecker.matchesBetweenOneThree(input));
    }

    @ParameterizedTest
    @DisplayName("--String has same beginning and ending Test")
    @MethodSource("provideStringsSameEdge")
    void matchesSameEndRegex(String input, boolean answer) {
        assertEquals(answer, zeroOneDictionaryChecker.matchesSameEndRegex(input));
    }

    @ParameterizedTest
    @DisplayName("--String has 3+ chars 3-rd 0 Test")
    @MethodSource("provideStringsMoreThirdZero")
    void matchesThreeMoreThirdZero(String input, boolean answer) {
        assertEquals(answer, zeroOneDictionaryChecker.matchesThreeMoreThirdZero(input));
    }

    private static Stream<Arguments> provideStringsBetweenOneAndThree() {
        return Stream.of(
            Arguments.of("101", true),
            Arguments.of("00", true),
            Arguments.of("", false),
            Arguments.of("102", false),
            Arguments.of("1111", false)
        );
    }

    private static Stream<Arguments> provideStringsSameEdge() {
        return Stream.of(
            Arguments.of("101", true),
            Arguments.of("00", true),
            Arguments.of("0", true),
            Arguments.of("", false),
            Arguments.of("1110", false),
            Arguments.of("14f0", false)
        );
    }

    public static Stream<Arguments> provideStringsMoreThirdZero() {
        return Stream.of(
            Arguments.of("100", true),
            Arguments.of("000", true),
            Arguments.of("01011111", true),
            Arguments.of("", false),
            Arguments.of("10", false),
            Arguments.of("01101010", false)
        );
    }
}
