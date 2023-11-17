package edu.project2.Generation;

import edu.project2.Generation.Exception.NotProperMazeSizeException;
import edu.project2.Models.Cell;
import edu.project2.Models.CellType;
import edu.project2.Models.GeneratorDirections;
import edu.project2.Models.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimGenerator implements Generator {

    private final GeneratorUtil generatorUtil = new GeneratorUtil();
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {

        if (generatorUtil.notProperMazeSize(height, width)) {
            throw new NotProperMazeSizeException();
        }

        Maze maze = new Maze(height, width);

        generatorUtil.fillMazeWithWalls(maze);

        generateMaze(maze);

        return maze;
    }

    private void generateMaze(Maze maze) {
        GeneratorDirections[] directions = GeneratorDirections.values();
        List<Cell> walls = new ArrayList<>();
        int currentX = 0;
        int currentY = 0;

        walls.add(maze.getCells()[currentY][currentX]);
        maze.getCells()[currentY][currentX] = maze.getCells()[currentY][currentX].getNewCell(CellType.PASS);

        while (!walls.isEmpty()) {
            int randomIndex = random.nextInt(walls.size());
            Cell wall = walls.get(randomIndex);
            currentX = wall.column();
            currentY = wall.row();

            if (maze.getCells()[currentY][currentX].type() == CellType.PASS) {
                maze.getCells()[currentY][currentX] = new Cell(currentY, currentX, CellType.PASS);

                int newX;
                int newY;

                for (GeneratorDirections direction : directions) {
                    newX = currentX + direction.getWidth();
                    newY = currentY + direction.getHeight();
                    if (generatorUtil.cellInMazeRange(maze, newX, newY)
                            && maze.getCells()[newY][newX].type() == CellType.WALL) {

                        maze.getCells()[newY - direction.getHeight() / 2][newX - direction.getWidth() / 2]
                            = maze.getCells()[newY - direction.getHeight() / 2][newX - direction.getWidth() / 2]
                                .getNewCell(CellType.PASS);
                        maze.getCells()[newY][newX] = maze.getCells()[newY][newX].getNewCell(CellType.PASS);
                        walls.add(maze.getCells()[newY][newX]);
                    }
                }
            }

            walls.remove(randomIndex);
        }
    }
}
