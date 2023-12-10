package edu.project4;

import edu.project4.Model.ColoredAffineTransformation;
import edu.project4.Model.FractalImage;
import edu.project4.Model.Pixel;
import edu.project4.Model.Rectangle;
import edu.project4.Render.MultipleThreadRenderer;
import edu.project4.Render.OneThreadRenderer;
import edu.project4.Render.Renderer;
import edu.project4.Tranformation.Transformation;
import edu.project4.Tranformation.TransformationList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("RegexpSinglelineJava")
public final class SpeedComparator {

    private final static int WIDTH = 1200;
    private final static int HEIGHT = 900;
    private final static int SAMPLES = 100000;
    private final static int ITERATIONS = 5000;
    private final static double RECT_COORDINATE = 1.5;
    public static final int AFFINES_AMOUNT = 20;

    public void compare() {
        Random random = ThreadLocalRandom.current();
        List<ColoredAffineTransformation> coloredAffineTransformations = new ArrayList<>();
        List<Transformation> transformations = new ArrayList<>();

        for (int i = 0; i < AFFINES_AMOUNT; i++) {
            coloredAffineTransformations.add(ColoredAffineTransformation.getNewColoredAffineTransformation());
        }

        transformations.addAll(
            List.of(
                TransformationList.Swirl.getTransformation(),
                TransformationList.Sinusoidal.getTransformation()
            )
        );

        Renderer renderer = new OneThreadRenderer();
        long seconds = System.currentTimeMillis();
        renderer.render(
            new FractalImage(new Pixel[HEIGHT][WIDTH], WIDTH, HEIGHT),
            new Rectangle(-RECT_COORDINATE, -RECT_COORDINATE, 2 * RECT_COORDINATE, 2 * RECT_COORDINATE),
            coloredAffineTransformations,
            transformations,
            SAMPLES,
            (short) ITERATIONS,
            2,
            random.nextLong()
        );
        System.out.println("Single: " + (System.currentTimeMillis() - seconds));

        renderer = new MultipleThreadRenderer();
        seconds = System.currentTimeMillis();
        renderer.render(
            new FractalImage(new Pixel[HEIGHT][WIDTH], WIDTH, HEIGHT),
            new Rectangle(-RECT_COORDINATE, -RECT_COORDINATE, 2 * RECT_COORDINATE, 2 * RECT_COORDINATE),
            coloredAffineTransformations,
            transformations,
            SAMPLES,
            (short) ITERATIONS,
            2,
            random.nextLong()
        );
        System.out.println("Multi:" + (System.currentTimeMillis() - seconds));
    }
}
