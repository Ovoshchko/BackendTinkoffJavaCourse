package edu.hw5.Task2;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Friday13SearcherTest {

    private final Friday13Searcher searcher = new Friday13Searcher();

    @ParameterizedTest
    @DisplayName("--Next Friday 13 test")
    @MethodSource("provideNextFridays")
    void searchNextFriday13(LocalDate date, LocalDate answer) {
        assertEquals(answer, searcher.searchNextFriday13(date));
    }

    @ParameterizedTest
    @DisplayName("--All current year Friday 13 test")
    @MethodSource("provideAllFridays")
    void searchBlackFridays(LocalDate date, List<LocalDate> answer) {
        assertEquals(answer, searcher.searchBlackFridays(date));
    }

    private static Stream<Arguments> provideNextFridays() {
        return Stream.of(
            Arguments.of(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 13)),
            Arguments.of(LocalDate.of(1980, 10, 30), LocalDate.of(1981, 2, 13)),
            Arguments.of(LocalDate.of(2030, 3, 23), LocalDate.of(2030, 9, 13))
        );
    }

    private static Stream<Arguments> provideAllFridays() {
        return Stream.of(
            Arguments.of(LocalDate.of(1925, 4, 5), List.of(
                LocalDate.of(1925, 2, 13),
                LocalDate.of(1925, 3, 13),
                LocalDate.of(1925, 11, 13)
            )),
            Arguments.of(LocalDate.of(2024, 10, 30), List.of(
                LocalDate.of(2024, 9, 13),
                LocalDate.of(2024, 12, 13)
            ))
        );
    }
}
