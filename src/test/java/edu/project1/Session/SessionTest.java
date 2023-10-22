package edu.project1.Session;

import edu.project1.Results.FailedResult;
import edu.project1.Results.GiveUpResult;
import edu.project1.Results.RepeatResult;
import edu.project1.Results.ResultInterface;
import edu.project1.Results.SuccessfulResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Session Test")
public class SessionTest {

    @ParameterizedTest
    @DisplayName("--Guess Function Test")
    @MethodSource("provideSessions")
    void guessFunctionTest(Session session, char letter, ResultInterface result) {
        ResultInterface resultGuess = session.guess(letter);
        assertEquals(resultGuess, result);
    }

    @ParameterizedTest
    @DisplayName("--Give Up Function Test")
    @MethodSource("provideGiveUpSession")
    void giveUpFunctionTest(Session session, ResultInterface result) {
        ResultInterface resultGiveUp = session.giveUp();
        assertEquals(result, resultGiveUp);
    }

    @ParameterizedTest
    @DisplayName("--Repeat Function Test")
    @MethodSource("provideSessionRepeat")
    void repeatFunctionTest(Session session, char letter, ResultInterface result) {
        session.guess(letter);
        ResultInterface resultGuess = session.guess(letter);
        assertEquals(resultGuess, result);
    }

    private static Stream<Arguments> provideSessions() {
        return Stream.of(
            Arguments.of(new Session("OOOOOO"), 'O', new SuccessfulResult("OOOOOO", 6, 0, new HashSet<>(Arrays.asList('O')))),
            Arguments.of(new Session("IIIIII"), 'O', new FailedResult("IIIIII", 6, 1, new HashSet<>(Arrays.asList('O')))),
            Arguments.of(new Session("POTYFUHBN"), 'M', new FailedResult("POTYFUHBN", 6, 1, new HashSet<>(Arrays.asList('M'))))
        );
    }

    private static Stream<Arguments> provideGiveUpSession() {
        return Stream.of(
            Arguments.of(new Session("GHJM"), new GiveUpResult("GHJM", 6, 6, new HashSet<>())),
            Arguments.of(new Session("FRIDGE"), new GiveUpResult("FRIDGE", 6, 6, new HashSet<>()))
        );
    }

    private static Stream<Arguments> provideSessionRepeat() {
        return Stream.of(
            Arguments.of(new Session("GHJM"), 'O', new RepeatResult("GHJM", 6, 1, new HashSet<>(Arrays.asList('O')))),
            Arguments.of(new Session("FRIDGE"), 'F', new RepeatResult("FRIDGE", 6, 0, new HashSet<>(Arrays.asList('F'))))
        );
    }
}
