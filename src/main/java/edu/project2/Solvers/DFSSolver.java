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

    private final static String WRONG_COORDINATES = "Координаты вне лабиринта, попробуйте поискать в лабиринте";
    private final SolverUtil solverUtil = new SolverUtil();

    /*
    Поскольку лабиринт создается с представлением стен в качестве доп клеток, получаем, что координаты тоже необходимо
    преобразовать
    */
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate startPoint, Coordinate finishPoint) {

        if (solverUtil.coordIsNotValid(maze, startPoint)
                && (solverUtil.coordIsNotValid(maze, finishPoint))) {
            throw new IllegalArgumentException(WRONG_COORDINATES);
        }

        Stack<Coordinate> path = new Stack<>();
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
        solverUtil.fillVisitedCellsFalse(visited);

        findPath(maze, path, visited, new Coordinate((startPoint.x() - 1) * 2, (startPoint.y() - 1) * 2),
            new Coordinate((finishPoint.x() -  1) * 2, (finishPoint.y() - 1) * 2));

        return path.stream().toList();
    }

    private void findPath(Maze maze, Stack<Coordinate> path, boolean[][] visited, Coordinate coordinate,
        Coordinate finishPoint) {

        DIRECTION[] dirs = DIRECTION.values();
        Collections.shuffle(Arrays.asList(dirs));
        path.push(coordinate);
        visited[coordinate.y()][coordinate.x()] = true;

        if (solverUtil.endOfMaze(maze, coordinate, finishPoint)) {
            return;
        }

        for (DIRECTION dir: dirs) {
            Coordinate nextCell = new Coordinate(coordinate.x() + dir.getWidth(), coordinate.y() + dir.getHeight());

            if ((solverUtil.cellInMazeRange(maze, nextCell.x(), nextCell.y()))
                    && (!visited[nextCell.y()][nextCell.x()])
                    && (maze.getCells()[nextCell.y()][nextCell.x()].type() == CellType.PASS)) {
                findPath(maze, path, visited, nextCell, finishPoint);
            }
        }

        if (!((path.isEmpty()) || (solverUtil.endOfMaze(maze, path.peek(), finishPoint)))) {
            path.pop();
        }
    }
}
