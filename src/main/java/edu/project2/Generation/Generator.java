package edu.project2.Generation;

import edu.project2.Models.Maze;

public interface Generator {
    /***
     * Создает лабиринт с заданной высотой и шириной
     *
     * @param height Высота лабиринта
     * @param width Ширина лабиринта
     * @return Созданный лабиринт
     */
    Maze generate(int height, int width);
}
