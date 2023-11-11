package edu.hw5.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class PatternVariantChainTest {

    private final PatternChain patternChain = PatternChain.init();

    @ParameterizedTest
    @DisplayName("--Parse different dates test")
    @MethodSource("provideDates")
    void parseDate(String date, Optional<LocalDate> answer) {
        assertEquals(answer, patternChain.parseDate(date));
    }

    private static Stream<Arguments> provideDates() {
        return Stream.of(
            Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10))),
            Arguments.of("2020-10-8", Optional.of(LocalDate.of(2020, 10, 8))),
            Arguments.of("10/10/2020", Optional.of(LocalDate.of(2020, 10, 10))),
            Arguments.of("10/10/20", Optional.of(LocalDate.of(2020, 10, 10))),
            Arguments.of("yesterday", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("today", Optional.of(LocalDate.now())),
            Arguments.of("tomorrow", Optional.of(LocalDate.now().plusDays(1))),
            Arguments.of("1 day ago", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("1400 day ago", Optional.of(LocalDate.now().minusDays(1400))),
            Arguments.of("2020 10 10", Optional.empty())
        );
    }
}
