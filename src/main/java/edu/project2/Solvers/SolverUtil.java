package edu.project2.Solvers;

import edu.project2.Models.Coordinate;
import edu.project2.Models.Maze;
import java.util.Arrays;

public class SolverUtil {

    public SolverUtil() {}

    public boolean cellInMazeRange(Maze maze, int x, int y) {
        return (x < maze.getWidth())
            && (x >= 0)
            && (y < maze.getHeight())
            && (y >= 0);
    }

    public boolean coordIsNotValid(Maze maze, Coordinate coordinate) {
        return !cellInMazeRange(maze, (coordinate.x() - 1) * 2, (coordinate.y() - 1) * 2);
    }

    public boolean endOfMaze(Maze maze, Coordinate coordinate, Coordinate finishPoint) {
        return (coordinate.x() == finishPoint.x()) && (coordinate.y() == finishPoint.y());
    }

    public void fillVisitedCellsFalse(boolean[][] visitedCells) {
        for (boolean[] row: visitedCells) {
            Arrays.fill(row, false);
        }
    }
}
