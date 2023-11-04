package edu.project2.Generation;

import edu.project2.Models.Cell;
import edu.project2.Models.CellType;
import edu.project2.Models.Maze;
import java.util.Arrays;

public class GeneratorUtil {

    public GeneratorUtil() {}

    public boolean cellInMazeRange(Maze maze, int x, int y) {
        return (x < maze.getWidth())
            && (x >= 0)
            && (y < maze.getHeight())
            && (y >= 0);
    }

    public void fillVisitedCellsFalse(boolean[][] visitedCells) {
        for (boolean[] row: visitedCells) {
            Arrays.fill(row, false);
        }
    }

    public void fillMaze(Maze maze) {
        Cell[][] mazeCells = maze.getCells();

        for (int height = 0; height < maze.getWidth(); height++) {
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
}
