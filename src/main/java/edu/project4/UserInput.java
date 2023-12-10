package edu.project4;

import edu.project4.Model.ColoredAffineTransformation;
import edu.project4.Model.FractalImage;
import edu.project4.Model.Pixel;
import edu.project4.Model.Rectangle;
import edu.project4.Render.Renderer;
import edu.project4.Tranformation.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ParameterNumber")
public class UserInput {

    public static final int AFFINES_NUMBER = 20;

    public FractalImage getFlame(Renderer renderer, List<Transformation> transformations, int width, int height,
        double worldWidth, double worldHeight, int samples, int iterations, int symmetry) {

        Random random = ThreadLocalRandom.current();
        List<ColoredAffineTransformation> coloredAffineTransformations = new ArrayList<>();

        for (int i = 0; i < AFFINES_NUMBER; i++) {
            coloredAffineTransformations.add(ColoredAffineTransformation.getNewColoredAffineTransformation());
        }

        FractalImage image = renderer.render(
            new FractalImage(new Pixel[height][width], width, height),
            new Rectangle(-worldWidth / 2, -worldHeight / 2, worldWidth, worldHeight),
            coloredAffineTransformations,
            transformations,
            samples,
            (short) iterations,
            symmetry,
            random.nextLong()
        );

        return image;
    }

}
