package edu.project2;

import edu.project2.Generation.OldosGenerator;
import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import edu.project2.Models.Maze;
import edu.project2.Renderers.RendererImpl;
import edu.project2.Solvers.DFSSolver;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        OldosGenerator oldosGenerator = new OldosGenerator();
        Maze maze = oldosGenerator.generate(17, 17);
        RendererImpl render = new RendererImpl();
        System.out.println(render.render(maze));
        List<Coordinate> path = (new DFSSolver()).solve(maze);
        System.out.println(render.render(maze, path));
    }
}
