package edu.hw3.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClusterizationTest {

    private final static Clusterization clusterizator = new Clusterization();

    @ParameterizedTest
    @DisplayName("--Valid inputs Test")
    @MethodSource("getValidBrackets")
    void validInputBracketsTest(String input, String[] answer) {
        assertArrayEquals(answer, clusterizator.clusterize(input));
    }

    @ParameterizedTest
    @DisplayName("--Invalid inputs Test")
    @NullAndEmptySource
    void invalidInputBracketsTest(String input) {
        assertArrayEquals(new String[]{}, clusterizator.clusterize(input));
    }

    @ParameterizedTest
    @DisplayName("--Unbalanced brackets and unrecognised characters test")
    @MethodSource("getUnbalancedBrackets")
    void unbalancedBracketTest(String input) {
        assertThrows(IllegalArgumentException.class, () -> clusterizator.clusterize(input));
    }

    private static Stream<Arguments> getValidBrackets() {
        return Stream.of(
            Arguments.of("(<>)()()((()))", new String[]{"(<>)", "()", "()", "((()))"}),
            Arguments.of("[]", new String[]{"[]"}),
            Arguments.of("(((({<>[]}))))", new String[]{"(((({<>[]}))))"})
        );
    }

    private static Stream<Arguments> getUnbalancedBrackets() {
        return Stream.of(
            Arguments.of("((<))()()((()))))"),
            Arguments.of("(([])"),
            Arguments.of("(((((((3444)))))))"),
            Arguments.of("345tgfcd"),
            Arguments.of("{)")
        );
    }
}
