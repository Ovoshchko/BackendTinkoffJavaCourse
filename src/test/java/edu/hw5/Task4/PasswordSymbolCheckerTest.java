package edu.hw5.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class PasswordSymbolCheckerTest {

    private final PasswordSymbolChecker checker = new PasswordSymbolChecker();

    @ParameterizedTest
    @DisplayName("--Password Symbol Checker True Test")
    @MethodSource("providePasswords")
    void hasSpecialSymbols(String input) {
        assertTrue(checker.hasSpecialSymbols(input));
    }

    @ParameterizedTest
    @DisplayName("--Password Symbol Checker False Test")
    @MethodSource("provideNotPasswords")
    void hasNotSpecialSymbols(String input) {
        assertFalse(checker.hasSpecialSymbols(input));
    }

    @ParameterizedTest
    @DisplayName("--Password Symbol Checker Null Test")
    @NullSource
    void hasNullSpecialSymbols(String input) {
        assertFalse(checker.hasSpecialSymbols(input));
    }

    private static Stream<Arguments> providePasswords() {
        return Stream.of(
            Arguments.of("~"),
            Arguments.of("rge!"),
            Arguments.of("345654@4567"),
            Arguments.of("sbnt$$gdhh"),
            Arguments.of("######"),
            Arguments.of("       %     "),
            Arguments.of("feefseff&sffe"),
            Arguments.of("r3rwrr^"),
            Arguments.of("*****~~~~"),
            Arguments.of("|")
        );
    }

    private static Stream<Arguments> provideNotPasswords() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("esfefse"),
            Arguments.of("2ee23444"),
            Arguments.of("3333333"),
            Arguments.of("---")
        );
    }
}
