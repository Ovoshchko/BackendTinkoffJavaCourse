package edu.hw2.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Task1 Multiplication Test")
public class Task1MultiplicationTest {

    @ParameterizedTest
    @DisplayName("--Task1 Multiplication only Const Test")
    @MethodSource("provideConstantsForMultiplication")
    void additionWithConstTest(Expr expression1, Expr expression2, double answer) {
        Expr multiplication = new Multiplication(expression1, expression2);
        assertEquals(answer, multiplication.evaluate());
    }

    @ParameterizedTest
    @DisplayName("--Task1 Multiplication Const and Negate Test")
    @MethodSource("provideConstNegateForMultiplication")
    void additionConstNegateTest(Expr expression1, Expr expression2, double answer) {
        Expr multiplication = new Multiplication(expression1, expression2);
        assertEquals(answer, multiplication.evaluate());
    }

    private static Stream<Arguments> provideConstantsForMultiplication() {
        return Stream.of(
            Arguments.of(new Constant(4), new Constant(5), 20),
            Arguments.of(new Constant(123), new Constant(0), 0),
            Arguments.of(new Constant(123), new Constant(-123), -15129),
            Arguments.of(new Constant(-12), new Constant(-13), 156),
            Arguments.of(new Constant(23.4), new Constant(34.2), 800.28)
        );
    }

    private static Stream<Arguments> provideConstNegateForMultiplication() {
        return Stream.of(
            Arguments.of(new Constant(4), new Negate(new Constant(5)), -20),
            Arguments.of(new Constant(123), new Negate(new Constant(-1)), 123),
            Arguments.of(new Negate(new Constant(123)), new Negate(new Constant(123)), 15129)
        );
    }

}
