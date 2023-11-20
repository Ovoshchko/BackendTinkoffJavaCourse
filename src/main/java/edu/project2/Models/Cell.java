package edu.project2.Models;

public record Cell(int row, int column, CellType type) {

    public Cell getNewCell(CellType type) {
        return new Cell(row, column, type);
    }
}
