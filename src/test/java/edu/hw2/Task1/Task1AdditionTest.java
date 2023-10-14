package edu.hw2.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;

@DisplayName("Task1 Addition Test")
public class Task1AdditionTest {

    @ParameterizedTest
    @DisplayName("--Task1 Addition only Const Test")
    @MethodSource("provideConstantsForAddition")
    void additionWithConstTest(Expr expression1, Expr expression2, double answer) {
        Expr addition = new Addition(expression1, expression2);
        assertEquals(answer, addition.evaluate());
    }

    @ParameterizedTest
    @DisplayName("--Task1 Addition Const and Negate Test")
    @MethodSource("provideConstNegateForAddition")
    void additionConstNegateTest(Expr expression1, Expr expression2, double answer) {
        Expr addition = new Addition(expression1, expression2);
        assertEquals(answer, addition.evaluate());
    }

    private static Stream<Arguments> provideConstantsForAddition() {
        return Stream.of(
            Arguments.of(new Constant(4), new Constant(5), 9),
            Arguments.of(new Constant(123), new Constant(0), 123),
            Arguments.of(new Constant(123), new Constant(-123), 0),
            Arguments.of(new Constant(-12), new Constant(-13), -25),
            Arguments.of(new Constant(23.4), new Constant(34.2), 57.6)
        );
    }

    private static Stream<Arguments> provideConstNegateForAddition() {
        return Stream.of(
            Arguments.of(new Constant(4), new Negate(new Constant(5)), -1),
            Arguments.of(new Constant(123), new Negate(new Constant(0)), 123),
            Arguments.of(new Negate(new Constant(123)), new Negate(new Constant(123)), -246)
        );
    }

}
