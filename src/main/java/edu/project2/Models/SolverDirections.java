package edu.project2.Models;

import lombok.Getter;

@Getter
public enum SolverDirections {
    NORTH(-1, 0),
    SOUTH(1, 0),
    WEST(0, -1),
    EAST(0, 1);

    private final int height;
    private final int width;

    SolverDirections(int height, int width) {
        this.height = height;
        this.width = width;
    }
}
