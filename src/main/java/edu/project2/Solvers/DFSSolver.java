package edu.project2.Solvers;

import edu.project2.Models.CellType;
import edu.project2.Models.Coordinate;
import edu.project2.Models.DIRECTION;
import edu.project2.Models.Maze;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DFSSolver implements Solver {

    private final SolverUtil solverUtil = new SolverUtil();

    @Override
    public List<Coordinate> solve(Maze maze) {
        Stack<Coordinate> path = new Stack<>();
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
        findPath(maze, path, visited, new Coordinate(0, 0));

        return path.stream().toList();
    }

    private void findPath(Maze maze, Stack<Coordinate> path, boolean[][] visited, Coordinate coordinate) {

        DIRECTION[] dirs = DIRECTION.values();
        Collections.shuffle(Arrays.asList(dirs));
        path.push(coordinate);
        visited[coordinate.y()][coordinate.x()] = true;

        if (solverUtil.endOfMaze(maze, coordinate.x(), coordinate.y())) {
            return;
        }

        for (DIRECTION dir: dirs) {
            Coordinate nextCell = new Coordinate(coordinate.x() + dir.getWidth(), coordinate.y() + dir.getHeight());

            if ((solverUtil.cellInMazeRange(maze, nextCell.x(), nextCell.y()))
                    && (!visited[nextCell.y()][nextCell.x()])
                    && (maze.getCells()[nextCell.y()][nextCell.x()].type() == CellType.PASS)) {
                findPath(maze, path, visited, nextCell);
            }
        }

        if (!((path.isEmpty()) || (solverUtil.endOfMaze(maze, path.peek().x(), path.peek().y())))) {
            path.pop();
        }
    }
}
