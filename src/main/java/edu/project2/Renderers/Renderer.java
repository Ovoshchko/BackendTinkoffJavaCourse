package edu.project2.Renderers;

import edu.project2.Models.Coordinate;
import edu.project2.Models.Maze;
import java.util.List;

public interface Renderer {
    /***
     * Отрисовывает лабиринт
     *
     * @param maze Лабиринт
     * @return Строчное представление лабиринта
     */
    String render(Maze maze);

    /***
     * Отрисовывает лабиринт и данный путь по нему
     *
     * @param maze Лабиринт
     * @param path Путь в лабиринте
     * @return Строчное представление лабиринта с путем
     */
    String render(Maze maze, List<Coordinate> path);
}
