package edu.hw5.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class AvgTimeCounterTest {

    private AvgTimeCounter avgTimeCounter = new AvgTimeCounter();

    @ParameterizedTest
    @DisplayName("--Task1 valid input test")
    @MethodSource("provideValidInput")
    void getAvgMinutesSpentTest(List<String> dates, String answer) {
        assertEquals(answer, avgTimeCounter.getAvgMinutesSpent(dates));
    }

    @ParameterizedTest
    @DisplayName("--Task1 null inout test")
    @NullSource
    void getAvgMinutesSpentNullTest(List<String> dates) {
        assertThrows(NullPointerException.class, () -> avgTimeCounter.getAvgMinutesSpent(dates));
    }

    @ParameterizedTest
    @DisplayName("--Task 1 invalid input test")
    @MethodSource("provideInvalidInput")
    void getAvgMinutesSpentInvalidTest(List<String> dates) {
        assertThrows(DateFailedParseException.class, () -> avgTimeCounter.getAvgMinutesSpent(dates));
    }

    private static Stream<Arguments> provideValidInput() {
        return Stream.of(
            Arguments.of(new ArrayList<>(List.of(
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"
            )), "3ч 40м"),
            Arguments.of(new ArrayList<>(List.of()), "0ч 0м"),
            Arguments.of(new ArrayList<>(List.of(
                "2022-03-12, 20:20 - 2022-03-13, 01:50",
                "2022-04-01, 12:30 - 2022-04-02, 01:30",
                "2023-04-23, 12:30 - 2023-04-29, 12:30"
            )), "54ч 10м")
        );
    }

    private static Stream<Arguments> provideInvalidInput() {
        return Stream.of(
            Arguments.of(new ArrayList<>(List.of(
                "2022-03--------12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"
            ))),
            Arguments.of(new ArrayList<>(List.of("")), "0ч 0м"),
            Arguments.of(new ArrayList<>(List.of(
                "2022-03-12, 20:20 - 2022-03-12, 48:50",
                "2022-04-01, 12:30 - 2022-04-02, 01:30",
                "2023-04-23, 12:30 - 2023-04-29, 12:30"
            )))
        );
    }
}
