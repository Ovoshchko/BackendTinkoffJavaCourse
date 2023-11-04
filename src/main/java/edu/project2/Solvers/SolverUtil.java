package edu.project2.Solvers;

import edu.project2.Models.Maze;

public class SolverUtil {

    public SolverUtil() {}

    public boolean cellInMazeRange(Maze maze, int x, int y) {
        return (x < maze.getWidth())
            && (x >= 0)
            && (y < maze.getHeight())
            && (y >= 0);
    }

    public boolean endOfMaze(Maze maze, int x, int y) {
        return (x == maze.getWidth() - 1) && (y == maze.getHeight() - 1);
    }
}
