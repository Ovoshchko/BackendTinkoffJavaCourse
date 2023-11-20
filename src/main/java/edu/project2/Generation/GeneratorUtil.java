package edu.project2.Generation;

import edu.project2.Models.Cell;
import edu.project2.Models.CellType;
import edu.project2.Models.Maze;
import java.util.Arrays;
import java.util.stream.IntStream;

public class GeneratorUtil {

    public GeneratorUtil() {}

    public boolean cellInMazeRange(Maze maze, int x, int y) {
        return (x < maze.getWidth())
            && (x >= 0)
            && (y < maze.getHeight())
            && (y >= 0);
    }

    public void fillMazeWithWalls(Maze maze) {
        IntStream.range(0, maze.getCells().length)
            .boxed()
            .flatMap(i -> IntStream.range(0, maze.getCells()[i].length)
                .mapToObj(j -> new Cell(i, j, CellType.WALL)))
            .forEach(cell -> maze.getCells()[cell.row()][cell.column()] = cell);
    }

    public void fillMazeHalfWalls(Maze maze) {
        Cell[][] mazeCells = maze.getCells();

        for (int height = 0; height < maze.getHeight(); height++) {
            if (height % 2 == 0) {
                for (int width = 0; width < maze.getWidth(); width++) {
                    if (width % 2 == 0) {
                        mazeCells[height][width] = new Cell(height, width, CellType.PASS);
                    } else {
                        mazeCells[height][width] = new Cell(height, width, CellType.WALL);
                    }
                }
            } else {
                for (int width = 0; width < maze.getWidth(); width++) {
                    mazeCells[height][width] = new Cell(height, width, CellType.WALL);
                }
            }
        }
    }

    public void fillVisitedCellsFalse(boolean[][] visitedCells) {
        for (boolean[] row: visitedCells) {
            Arrays.fill(row, false);
        }
    }

    public boolean notProperMazeSize(int height, int width) {
        return height <= 0 || width <= 0;
    }
}
