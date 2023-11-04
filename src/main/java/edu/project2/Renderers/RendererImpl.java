package edu.project2.Renderers;

import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import edu.project2.Models.Maze;
import java.util.List;

public class RendererImpl implements Renderer {

    private final static int PRINT_CELL_SIZE = 3;

    public String render(Maze maze) {
        StringBuilder mazePicture = new StringBuilder();

        mazePicture.append(getUpperBorder(maze.getWidth()));
        mazePicture.append(getCellsPicture(maze));
        mazePicture.append(getLowerBorder(maze.getWidth()));

        return mazePicture.toString();
    }

    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder mazePictureWithPath = new StringBuilder(render(maze));
        int currentIndex = 0;

        for (Coordinate coordinate: path) {
            currentIndex = (coordinate.y() + 1) * (PRINT_CELL_SIZE * maze.getWidth() + 3)
                + (PRINT_CELL_SIZE * coordinate.x() + 1);
            mazePictureWithPath.replace(currentIndex, currentIndex + PRINT_CELL_SIZE, " • ");
        }

        return mazePictureWithPath.toString();
    }

    private String getCellsPicture(Maze maze) {
        StringBuilder picture = new StringBuilder();

        for (Cell[] cellRow: maze.getCells()) {
            picture.append("|");
            for (Cell cell: cellRow) {
                picture.append(cell.type().getValue());
            }
            picture.append("|\n");
        }

        return picture.toString();
    }

    private String getUpperBorder(int size) {
        return "|" + " ".repeat(PRINT_CELL_SIZE) + "-".repeat(PRINT_CELL_SIZE * (size - 1)) + "|" + "\n";
    }

    private String getLowerBorder(int size) {
        return "|" + "-".repeat(PRINT_CELL_SIZE * (size - 1)) + " ".repeat(PRINT_CELL_SIZE) + "|" + "\n";
    }


}
