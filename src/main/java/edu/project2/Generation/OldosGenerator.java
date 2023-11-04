package edu.project2.Generation;

import edu.project2.Models.CellType;
import edu.project2.Models.DIR;
import edu.project2.Models.Maze;
import java.util.Arrays;
import java.util.Collections;

public class OldosGenerator implements Generator {

    private final GeneratorUtil generatorUtil = new GeneratorUtil();

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        boolean[][] visitedCells =
            new boolean[maze.getHeight() / 2 + maze.getHeight() % 2][maze.getWidth() / 2 + maze.getWidth() % 2];

        generatorUtil.fillMaze(maze);
        generatorUtil.fillVisitedCellsFalse(visitedCells);
        generateMaze(maze, visitedCells, 0, 0);

        return maze;
    }

    private void generateMaze(Maze maze, boolean[][] visited, int x, int y) {
        int nextX;
        int nextY;

        DIR[] dirs = DIR.values();
        Collections.shuffle(Arrays.asList(dirs));
        visited[y / 2][x / 2] = true;

        for (DIR dir: dirs) {
            nextX = x + dir.getWidth();
            nextY = y + dir.getHeight();

            if (generatorUtil.cellInMazeRange(maze, nextX, nextY) && (!visited[nextY / 2][nextX / 2])) {
                maze.getCells()[nextY - dir.getHeight() / 2][nextX - dir.getWidth() / 2]
                    = maze.getCells()[nextY - dir.getHeight() % 2][nextX - dir.getWidth() % 2].getNewCell(CellType.PASS);
                generateMaze(maze, visited, nextX, nextY);
            }
        }

    }

}
