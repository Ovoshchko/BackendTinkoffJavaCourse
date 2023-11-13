package edu.hw5.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class SignCheckerTest {

    private final SignChecker signChecker = new SignChecker();

    @ParameterizedTest
    @DisplayName("--Sign Checker True Test")
    @MethodSource("provideSigns")
    void checkSign(String sign) {
        assertTrue(signChecker.checkSign(sign));
    }

    @ParameterizedTest
    @DisplayName("--Sign Checker False Test")
    @MethodSource("provideNotSigns")
    void checkNotSign(String sign) {
        assertFalse(signChecker.checkSign(sign));
    }

    @ParameterizedTest
    @DisplayName("--Sign Checker Null Test")
    @NullSource
    void checkNullSign(String sign) {
        assertFalse(signChecker.checkSign(sign));
    }

    private static Stream<Arguments> provideSigns() {
        return Stream.of(
            Arguments.of("А777АА777"),
            Arguments.of("А000АА000"),
            Arguments.of("К456ВА178"),
            Arguments.of("Д761ЕК039"),
            Arguments.of("Д333ДД777")
        );
    }

    private static Stream<Arguments> provideNotSigns() {
        return Stream.of(
            Arguments.of("А777АА77"),
            Arguments.of("А000А000"),
            Arguments.of(""),
            Arguments.of("Д761ЕК0390"),
            Arguments.of("Д333ДFД777")
        );
    }
}
