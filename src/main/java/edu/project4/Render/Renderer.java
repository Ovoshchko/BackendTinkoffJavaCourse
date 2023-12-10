package edu.project4.Render;

import edu.project4.Model.ColoredAffineTransformation;
import edu.project4.Model.FractalImage;
import edu.project4.Model.Rectangle;
import edu.project4.Tranformation.Transformation;
import java.util.List;

@FunctionalInterface
@SuppressWarnings("ParameterNumber")
public interface Renderer {
    FractalImage render(FractalImage canvas, Rectangle world, List<ColoredAffineTransformation> affines,
        List<Transformation> transformations, int samples, short iterPerSample, int symmetry, long seed);
}
