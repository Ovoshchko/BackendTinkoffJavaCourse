package edu.project2.Models;

import lombok.Getter;

@Getter
public enum GeneratorDirections {
    NORTH(-2, 0),
    SOUTH(2, 0),
    WEST(0, -2),
    EAST(0, 2);

    private final int height;
    private final int width;

    GeneratorDirections(int height, int width) {
        this.height = height;
        this.width = width;
    }
}
