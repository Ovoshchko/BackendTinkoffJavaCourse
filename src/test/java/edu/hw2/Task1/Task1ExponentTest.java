package edu.hw2.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Task1 Negate Number Test")
public class Task1ExponentTest {

    @ParameterizedTest
    @MethodSource("provideConstantsForExponent")
    void exponentConstTest(Constant input, double pow, double answer) {
        Exponent exponent = new Exponent(input, pow);
        assertEquals(answer, exponent.evaluate());
    }

    @ParameterizedTest
    @MethodSource("provideExprForExponent")
    void exponentExprTest(Expr expression1, double pow, double answer) {
        Exponent exponent = new Exponent(expression1, pow);
        assertEquals(answer, exponent.evaluate());
    }

    private static Stream<Arguments> provideConstantsForExponent() {
        return Stream.of(
            Arguments.of(new Constant(-1), 2, 1),
            Arguments.of(new Constant(34), 3, 39304),
            Arguments.of(new Constant(3.5), 3, 42.875)
        );
    }

    private static Stream<Arguments> provideExprForExponent() {
        return Stream.of(
            Arguments.of(new Negate(new Constant(10)), 3, -1000),
            Arguments.of(new Addition(new Constant(9), new Constant(3)), 3, 1728),
            Arguments.of(new Multiplication(new Constant(32), new Constant(0.2)), 4, 1677.7216000000003)
        );
    }

}
