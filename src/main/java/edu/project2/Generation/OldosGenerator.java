package edu.project2.Generation;

import edu.project2.Generation.Exception.NotProperMazeSizeException;
import edu.project2.Models.CellType;
import edu.project2.Models.GeneratorDirections;
import edu.project2.Models.Maze;
import java.util.Arrays;
import java.util.Collections;

public class OldosGenerator implements Generator {

    private final GeneratorUtil generatorUtil = new GeneratorUtil();

    @Override
    public Maze generate(int height, int width) {

        if (generatorUtil.notProperMazeSize(height, width)) {
            throw new NotProperMazeSizeException();
        }

        Maze maze = new Maze(height, width);
        boolean[][] visitedCells =
            new boolean[maze.getHeight() / 2 + maze.getHeight() % 2][maze.getWidth() / 2 + maze.getWidth() % 2];

        generatorUtil.fillMazeHalfWalls(maze);
        generatorUtil.fillVisitedCellsFalse(visitedCells);

        generateMaze(maze, visitedCells, 0, 0);

        return maze;
    }

    private void generateMaze(Maze maze, boolean[][] visited, int x, int y) {
        int nextX;
        int nextY;
        GeneratorDirections[] generatorDirections = GeneratorDirections.values();

        Collections.shuffle(Arrays.asList(generatorDirections));
        visited[y / 2][x / 2] = true;

        for (GeneratorDirections direction : generatorDirections) {
            nextX = x + direction.getWidth();
            nextY = y + direction.getHeight();

            if (generatorUtil.cellInMazeRange(maze, nextX, nextY) && (!visited[nextY / 2][nextX / 2])) {
                maze.getCells()[nextY - direction.getHeight() / 2][nextX - direction.getWidth() / 2]
                    = maze.getCells()[nextY - direction.getHeight() % 2][nextX - direction.getWidth() % 2]
                    .getNewCell(CellType.PASS);
                generateMaze(maze, visited, nextX, nextY);
            }
        }

    }

}
