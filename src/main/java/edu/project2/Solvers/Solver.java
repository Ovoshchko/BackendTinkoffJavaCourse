package edu.project2.Solvers;

import edu.project2.Models.Coordinate;
import edu.project2.Models.Maze;
import java.util.List;

public interface Solver {

    List<Coordinate> solve(Maze maze);
}
