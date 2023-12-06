package edu.hw9.Task3;

import edu.project2.Models.CellType;
import edu.project2.Models.Coordinate;
import edu.project2.Models.Maze;
import edu.project2.Models.SolverDirections;
import edu.project2.Solvers.Solver;
import edu.project2.Solvers.SolverUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class DFSSolverMultiThreading implements Solver {
    private final static String WRONG_COORDINATES = "Координаты вне лабиринта, попробуйте поискать в лабиринте";
    private final SolverUtil solverUtil = new SolverUtil();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate startPoint, Coordinate finishPoint) {

        if (solverUtil.coordIsNotValid(maze, startPoint)
            && (solverUtil.coordIsNotValid(maze, finishPoint))) {
            throw new IllegalArgumentException(WRONG_COORDINATES);
        }

        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
        solverUtil.fillVisitedCellsFalse(visited);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        List<Coordinate> path = pool.invoke(new PathSearcher(maze, visited,
            new Coordinate((startPoint.x() - 1) * 2, (startPoint.y() - 1) * 2),
            new Coordinate((finishPoint.x() -  1) * 2, (finishPoint.y() - 1) * 2)));

        return path.stream().toList();
    }

    private class PathSearcher extends RecursiveTask<List<Coordinate>> {

        private final Maze maze;
        private final boolean[][] visited;
        private final Coordinate coordinate;
        private final Coordinate finishPoint;

        private PathSearcher(Maze maze, boolean[][] visited, Coordinate coordinate, Coordinate finishPoint) {
            this.maze = maze;
            this.visited = visited;
            this.coordinate = coordinate;
            this.finishPoint = finishPoint;
        }

        @Override
        protected List<Coordinate> compute() {
            List<Coordinate> path = new ArrayList<>();

            if (solverUtil.endOfMaze(coordinate, finishPoint)) {
                path.add(coordinate);
            } else {
                findPath(path);
            }

            return path;
        }

        private void findPath(List<Coordinate> path) {
            SolverDirections[] dirs = SolverDirections.values();
            Collections.shuffle(Arrays.asList(dirs));
            visited[coordinate.y()][coordinate.x()] = true;
            List<PathSearcher> pathSearchers = new ArrayList<>();

            for (SolverDirections dir: dirs) {
                Coordinate nextCell = new Coordinate(coordinate.x() + dir.getWidth(), coordinate.y() + dir.getHeight());

                if ((solverUtil.cellInMazeRange(maze, nextCell.x(), nextCell.y()))
                    && (!visited[nextCell.y()][nextCell.x()])
                    && (maze.getCells()[nextCell.y()][nextCell.x()].type() == CellType.PASS)) {

                    PathSearcher task = new PathSearcher(maze, visited, nextCell, finishPoint);
                    task.fork();
                    pathSearchers.add(task);
                }
            }

            for (var taskResult: pathSearchers) {
                if (!taskResult.join().isEmpty()) {
                    path.addAll(taskResult.join());
                    break;
                }
            }

            if (!path.isEmpty()) {
                path.addFirst(coordinate);
            }
        }
    }
}
