package edu.project2.Generation;

import edu.project2.Generation.Exception.NotProperMazeSizeException;
import edu.project2.Models.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeneratorTest {

    @ParameterizedTest
    @DisplayName("--Valid sizes maze generation")
    @MethodSource("validMazeSizeGeneration")
    void validSizeMazeGenerationTest(Generator generator, int height, int width) {
        Maze maze = generator.generate(height, width);

        assertEquals(width * 2 - 1, maze.getWidth());
        assertEquals(height * 2 - 1, maze.getHeight());
        assertEquals(height * 2 - 1, maze.getCells().length);
    }

    @ParameterizedTest
    @DisplayName("--Invalid sizes maze generation")
    @MethodSource("invalidMazeSizeGeneration")
    void invalidSizeMazeGenerationTest(Generator generator, int height, int width) {
        assertThrows(NotProperMazeSizeException.class, () -> generator.generate(height, width));
    }

    private static Stream<Arguments> validMazeSizeGeneration() {
        return Stream.of(
            Arguments.of(new OldosGenerator(), 10, 10),
            Arguments.of(new OldosGenerator(), 1, 2),
            Arguments.of(new PrimGenerator(), 1, 2),
            Arguments.of(new PrimGenerator(), 1, 1),
            Arguments.of(new OldosGenerator(), 1, 10)
        );
    }

    private static Stream<Arguments> invalidMazeSizeGeneration() {
        return Stream.of(
            Arguments.of(new OldosGenerator(), -10, 10),
            Arguments.of(new OldosGenerator(), 0, 0),
            Arguments.of(new PrimGenerator(), -28, 34),
            Arguments.of(new PrimGenerator(), -1, -1),
            Arguments.of(new OldosGenerator(), 0, 23)
        );
    }
}
