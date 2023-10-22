package edu.hw2.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExprTest {

    @Nested
    @DisplayName("Task1 Addition Test")
    public class AdditionTest {

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

    @Nested
    @DisplayName("Task1 Constant Number Test")
    public class ConstantTest {

        @ParameterizedTest
        @ValueSource(doubles = {1, 345, 234.4, -4})
        void constantTest(double input) {
            Constant constant = new Constant(input);
            assertEquals(input, constant.evaluate());
        }
    }

    @Nested
    @DisplayName("Task1 Negate Number Test")
    public class ExponentTest {

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

    @Nested
    @DisplayName("Task1 Multiplication Test")
    public class MultiplicationTest {

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

    @Nested
    @DisplayName("Task1 Negate Number Test")
    public class NegateTest {

        @ParameterizedTest
        @ValueSource(doubles = {1, 345, 234.4, -4})
        void negateTest(double input) {
            Negate negate = new Negate(new Constant(input));
            assertEquals(-input, negate.evaluate());
        }
    }

}
