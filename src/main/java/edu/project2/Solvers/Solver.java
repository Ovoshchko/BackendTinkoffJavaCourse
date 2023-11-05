package edu.project2.Solvers;

import edu.project2.Models.Coordinate;
import edu.project2.Models.Maze;
import java.util.List;

public interface Solver {

    /***
     * Ищет путь по лабиринту между двумя точками
     *
     * @param maze Лабиринт
     * @param startPoint Координата начальной точки
     * @param finishPoint  Координата конечной точки
     * @return Лист координат пути между точками
     */
    List<Coordinate> solve(Maze maze, Coordinate startPoint, Coordinate finishPoint);
}
