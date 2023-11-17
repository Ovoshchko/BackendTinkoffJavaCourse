package edu.project2.Solvers;

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
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
public class SolverTest {

    @ParameterizedTest
    @DisplayName("--Defined paths Test")
    @MethodSource("onlyOnePathMaze")
    void testWithOnlyOnePath(Solver solver, Cell[][] cells, List<Coordinate> coordinates,
        Coordinate start, Coordinate finish) {
        Maze maze = new Maze(cells.length / 2 + 1, cells[0].length / 2 + 1);
        maze.setCells(cells);
        List<Coordinate> cells1 = solver.solve(maze, start, finish);
        System.out.println(cells1.toString());
        assertArrayEquals(coordinates.toArray(), solver.solve(maze, start, finish).toArray());
    }

    private static Stream<Arguments> onlyOnePathMaze() {
        return Stream.of(
            Arguments.of(new DFSSolver(), new Cell[][]{
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
                new Coordinate(1, 1),
                new Coordinate(2, 2)
            ),
            Arguments.of(
                new DFSSolver(), new Cell[][]{
                    {new Cell(0, 0, CellType.PASS), new Cell(0, 1, CellType.PASS), new Cell(0, 2, CellType.WALL), new Cell(0, 3, CellType.WALL), new Cell(0, 4, CellType.WALL)},
                    {new Cell(1, 0, CellType.WALL), new Cell(1, 1, CellType.PASS), new Cell(1, 2, CellType.WALL), new Cell(1, 3, CellType.WALL), new Cell(1, 4, CellType.WALL)},
                    {new Cell(2, 0, CellType.PASS), new Cell(2, 1, CellType.PASS), new Cell(2, 2, CellType.WALL), new Cell(2, 3, CellType.WALL), new Cell(2, 4, CellType.WALL)},
                    {new Cell(3, 0, CellType.PASS), new Cell(3, 1, CellType.WALL), new Cell(3, 2, CellType.PASS), new Cell(3, 3, CellType.PASS), new Cell(3, 4, CellType.PASS)},
                    {new Cell(4, 0, CellType.PASS), new Cell(4, 1, CellType.PASS), new Cell(4, 2, CellType.PASS), new Cell(4, 3, CellType.WALL), new Cell(4, 4, CellType.PASS)}
                },
                new ArrayList<Coordinate>(List.of(
                    new Coordinate(0, 0),
                    new Coordinate(1, 0),
                    new Coordinate(1, 1),
                    new Coordinate(1, 2),
                    new Coordinate(0, 2),
                    new Coordinate(0, 3),
                    new Coordinate(0, 4),
                    new Coordinate(1, 4),
                    new Coordinate(2, 4),
                    new Coordinate(2, 3),
                    new Coordinate(3, 3),
                    new Coordinate(4, 3),
                    new Coordinate(4, 4)
                )),
                new Coordinate(1, 1),
                new Coordinate(3, 3)
            ),
            Arguments.of(new DFSSolver(), new Cell[][]{
                    {new Cell(0, 0, CellType.PASS), new Cell(0, 1, CellType.PASS), new Cell(0, 2, CellType.PASS)},
                    {new Cell(1, 0, CellType.WALL), new Cell(1, 1, CellType.WALL), new Cell(1, 2, CellType.PASS)},
                    {new Cell(2, 0, CellType.PASS), new Cell(2, 1, CellType.PASS), new Cell(2, 2, CellType.PASS)}
                },
                new ArrayList<Coordinate>(List.of(
                    new Coordinate(0, 0),
                    new Coordinate(1, 0),
                    new Coordinate(2, 0),
                    new Coordinate(2, 1),
                    new Coordinate(2, 2),
                    new Coordinate(1, 2),
                    new Coordinate(0, 2)
                )),
                new Coordinate(1, 1),
                new Coordinate(1, 2)
            ),
            Arguments.of(new DFSSolver(), new Cell[][]{
                    {new Cell(0, 0, CellType.PASS), new Cell(0, 1, CellType.PASS), new Cell(0, 2, CellType.PASS)},
                    {new Cell(1, 0, CellType.WALL), new Cell(1, 1, CellType.PASS), new Cell(1, 2, CellType.PASS)},
                    {new Cell(2, 0, CellType.PASS), new Cell(2, 1, CellType.PASS), new Cell(2, 2, CellType.WALL)}
                },
                new ArrayList<Coordinate>(),
                new Coordinate(1, 1),
                new Coordinate(2, 2)
            )
        );
    }
}
