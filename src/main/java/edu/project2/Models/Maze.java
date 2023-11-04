package edu.project2.Models;

import lombok.Getter;


/*
    Поскольку я взял модель, где стена также является клеткой, то размеры лабиринта берутся как двойной размер - 1
    (Между n клетками есть n-1 стена)
 */
@Getter
public class Maze {

    private final int height;

    private final int width;

    private Cell[][] cells;

    public Maze(int height, int width) {
        this.height = 2 * height - 1;
        this.width = 2 * width - 1;
        cells = new Cell[this.height][this.width];
    }
}
