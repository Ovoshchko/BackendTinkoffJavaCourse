package edu.project2.Renderers;

import edu.project2.Models.Cell;
import edu.project2.Models.CellType;
import edu.project2.Models.Coordinate;
import edu.project2.Models.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenderTest {

    @ParameterizedTest
    @DisplayName("--Render maze return Test")
    @MethodSource("getMazeAndString")
    void renderMazeTest(Renderer renderer, Cell[][] cells, String answer) {
        Maze maze = new Maze(2, 2);
        maze.setCells(cells);

        assertEquals(answer, renderer.render(maze));
    }

    @ParameterizedTest
    @DisplayName("--Render maze and path return Test")
    @MethodSource("getMazePathAndString")
    void renderMazeAndPathTest(Renderer renderer, Cell[][] cells,List<Coordinate> path, String answer) {
        Maze maze = new Maze(2, 2);
        maze.setCells(cells);

        assertEquals(answer, renderer.render(maze, path));
    }

    private static Stream<Arguments> getMazeAndString() {
        return Stream.of(
            Arguments.of(new ConsoleRenderer(), new Cell[][]{
                {new Cell(0, 0, CellType.PASS), new Cell(0, 1, CellType.PASS), new Cell(0, 2, CellType.PASS)},
                {new Cell(1, 0, CellType.WALL), new Cell(1, 1, CellType.WALL), new Cell(1, 2, CellType.PASS)},
                {new Cell(2, 0, CellType.PASS), new Cell(2, 1, CellType.PASS), new Cell(2, 2, CellType.PASS)}
            },
                "|   ------|" + System.lineSeparator() + "|         |" + System.lineSeparator()
                    + "|XXXXXX   |" + System.lineSeparator() + "|         |" + System.lineSeparator() + "|------   |"
                    + System.lineSeparator()
            )
        );
    }

    private static Stream<Arguments> getMazePathAndString() {
        return Stream.of(
            Arguments.of(new ConsoleRenderer(), new Cell[][]{
                    {new Cell(0, 0, CellType.PASS), new Cell(0, 1, CellType.PASS), new Cell(0, 2, CellType.PASS)},
                    {new Cell(1, 0, CellType.WALL), new Cell(1, 1, CellType.WALL), new Cell(1, 2, CellType.PASS)},
                    {new Cell(2, 0, CellType.PASS), new Cell(2, 1, CellType.PASS), new Cell(2, 2, CellType.PASS)}
                },
                new ArrayList<Coordinate>(List.of(
                    new Coordinate(0, 0),
                    new Coordinate(1, 0),
                    new Coordinate(2, 0),
                    new Coordinate(2, 1),
                    new Coordinate(2, 2)
                )),
                "|   ------|" + System.lineSeparator() +
                    "| •  •  • |" + System.lineSeparator() + "|XXXXXX • |" + System.lineSeparator() +
                    "|       • |" + System.lineSeparator() + "|------   |" + System.lineSeparator()
            )
        );
    }
}
