package edu.project2.Renderers;

import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import edu.project2.Models.Maze;
import java.util.List;

public class ConsoleRenderer implements Renderer {

    private final static int PRINT_CELL_SIZE = 3;
    private final static String MAZE_ENTER_BORDER = " ";
    private final static String MAZE_HORIZ_BORDER = "|";
    private final static String MAZE_VERTIC_BORDER = "-";
    public static final String PATH_IMAGE = " • ";

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
            currentIndex = (coordinate.y() + 1) * (PRINT_CELL_SIZE * (maze.getWidth() + 1))
                + (PRINT_CELL_SIZE * coordinate.x() + 1);
            mazePictureWithPath.replace(currentIndex, currentIndex + PRINT_CELL_SIZE, PATH_IMAGE);
        }

        return mazePictureWithPath.toString();
    }

    private String getCellsPicture(Maze maze) {
        StringBuilder picture = new StringBuilder();

        for (Cell[] cellRow: maze.getCells()) {
            picture.append(MAZE_HORIZ_BORDER);
            for (Cell cell: cellRow) {
                picture.append(cell.type().getValue());
            }
            picture.append(MAZE_HORIZ_BORDER).append(System.lineSeparator());
        }

        return picture.toString();
    }

    private String getUpperBorder(int size) {
        return MAZE_HORIZ_BORDER + MAZE_ENTER_BORDER.repeat(PRINT_CELL_SIZE)
            + MAZE_VERTIC_BORDER.repeat(PRINT_CELL_SIZE * (size - 1)) + MAZE_HORIZ_BORDER + System.lineSeparator();
    }

    private String getLowerBorder(int size) {
        return MAZE_HORIZ_BORDER + MAZE_VERTIC_BORDER.repeat(PRINT_CELL_SIZE * (size - 1))
            + MAZE_ENTER_BORDER.repeat(PRINT_CELL_SIZE) + MAZE_HORIZ_BORDER + System.lineSeparator();
    }


}
