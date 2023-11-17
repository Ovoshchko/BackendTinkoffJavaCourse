package edu.project2.Generation;

import edu.project2.Models.Coordinate;
import edu.project2.Models.Maze;
import edu.project2.Renderers.ConsoleRenderer;
import edu.project2.Solvers.DFSSolver;

public class Main {

    public static void main(String[] args) {
        Maze maze = new OldosGenerator().generate(20, 20);
        System.out.println(new ConsoleRenderer().render(maze, new DFSSolver().solve(maze, new Coordinate(1, 1), new Coordinate(20, 20))));
    }
}
