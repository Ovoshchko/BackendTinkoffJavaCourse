package edu.hw5.Task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Additional01CheckerTest {

    private final Additional01Checker checker = new Additional01Checker();

    public static Stream<Arguments> provideOddStrings() {
        return Stream.of(
            Arguments.of("1001", false),
            Arguments.of("101", true),
            Arguments.of("", false),
            Arguments.of("10010101010000011010110", true)
        );
    }

    public static Stream<Arguments> provideNonCoherentOnes() {
        return Stream.of(
            Arguments.of("00000", true),
            Arguments.of("10101", true),
            Arguments.of("11", false),
            Arguments.of("201", false),
            Arguments.of("0010100101100001", false)
        );
    }

    public static Stream<Arguments> provideOddOnes() {
        return Stream.of(
            Arguments.of("1010101", true),
            Arguments.of("0101", false),
            Arguments.of("1210", false),
            Arguments.of("11111010100011", false),
            Arguments.of(" ", false)
        );
    }

    public static Stream<Arguments> provideOddZeroEvenOne() {
        return Stream.of(
            Arguments.of("100100", true),
            Arguments.of("0101010", true),
            Arguments.of("1", false),
            Arguments.of("00", false),
            Arguments.of("", false),
            Arguments.of("12", false)
        );
    }

    public static Stream<Arguments> provideMultipliedZero() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("010101", true),
            Arguments.of("0101110100101001101", true),
            Arguments.of("001110101", false),
            Arguments.of("00", false),
            Arguments.of("111", false),
            Arguments.of("", true)
        );
    }

    @ParameterizedTest
    @DisplayName("--String has odd length Test")
    @MethodSource("provideOddStrings")
    void isOdd(String input, boolean answer) {
        assertEquals(answer, checker.isOdd(input));
    }

    @ParameterizedTest
    @DisplayName("--String has no coherent ones Test")
    @MethodSource("provideNonCoherentOnes")
    void noConnectedOnes(String input, boolean answer) {
        assertEquals(answer ,checker.noConnectedOnes(input));
    }

    @ParameterizedTest
    @DisplayName("--String has every odd char one Test")
    @MethodSource("provideOddOnes")
    void oddOne(String input, boolean answer) {
        assertEquals(answer, checker.oddOne(input));
    }

    @ParameterizedTest
    @DisplayName("--String has either 0 and odd length either 1 and even Test")
    @MethodSource("provideOddZeroEvenOne")
    void oddZeroEvenOne(String input, boolean answer) {
        assertEquals(answer, checker.oddZeroEvenOne(input));
    }

    @ParameterizedTest
    @DisplayName("--String has multiplied 3 zero Test")
    @MethodSource("provideMultipliedZero")
    void zeroMultiplyThree(String input, boolean answer) {
        assertEquals(answer, checker.zeroMultiplyThree(input));
    }
}
