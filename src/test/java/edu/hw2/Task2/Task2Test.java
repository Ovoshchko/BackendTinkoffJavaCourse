package edu.hw2.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;

@DisplayName("Task 2 Rectangle-Square Test")
public class Task2Test {

    @ParameterizedTest
    @DisplayName("--Regular Tests")
    @MethodSource("regularRectangles")
    void regularRectangleTest(Rectangle rectangle, double answer) {
        assertEquals(answer, rectangle.area());
    }

    private static Stream<Arguments> regularRectangles() {
        return Stream.of(
            Arguments.of(new Rectangle(30, 20), 600),
            Arguments.of(new Rectangle(-100, 45.5), 4550),
            Arguments.of(new Square(100), 10000),
            Arguments.of(new Rectangle(0, 100), 0),
            Arguments.of(new Rectangle(34.22, 33.98), 1162.7956)
        );
    }

    @ParameterizedTest
    @DisplayName("--Changed square Tests")
    @MethodSource("changeSquares")
    void changedSquareSizeTest(Rectangle square, double size) {
        assertEquals(size * size, square.area());
        Rectangle newRectangle = square.setWidth(10);
        assertEquals(size * 10, newRectangle.area());
    }

    private static Stream<Arguments> changeSquares() {
        return Stream.of(
            Arguments.of(new Square(30), 30),
            Arguments.of(new Square(45.5), 45.5),
            Arguments.of(new Square(100), 100)
        );
    }
}
