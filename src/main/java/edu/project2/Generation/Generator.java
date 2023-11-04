package edu.project2.Generation;

import edu.project2.Models.Maze;

public interface Generator {

    Maze generate(int height, int width);
}
