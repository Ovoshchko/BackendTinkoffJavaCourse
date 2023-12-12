package edu.project4.Render;

import edu.project4.Model.Color;
import edu.project4.Model.ColoredAffineTransformation;
import edu.project4.Model.FractalImage;
import edu.project4.Model.Pixel;
import edu.project4.Model.Point;
import edu.project4.Model.Rectangle;
import edu.project4.Tranformation.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

@SuppressWarnings("ParameterNumber")
public class OneThreadRenderer implements Renderer {

    private final static int STEPS_BEFORE = -20;

    @Override
    public FractalImage render(FractalImage canvas, Rectangle world, List<ColoredAffineTransformation> affines,
        List<Transformation> transformations, int samples, short iterPerSample, int symmetry, long seed) {
        Random random = ThreadLocalRandom.current();
        ColoredAffineTransformation affineTransformation;
        Transformation transformation;

        for (int sample = 0; sample < samples; sample++) {
            Point currentPoint = world.gerRandomPoint();

            for (int iter = STEPS_BEFORE; iter < iterPerSample; iter++) {
                transformation = transformations.get(random.nextInt(transformations.size()));
                affineTransformation = affines.get(random.nextInt(affines.size()));

                currentPoint = transformation.apply(
                    affineTransformation.transformation().apply(currentPoint)
                );

                if (iter >= 0) {

                    double theta = 0.0;
                    Point pointRotated;
                    for (int s = 0; s < symmetry; theta += PI * 2 / symmetry, s++) {
                        pointRotated = rotate(currentPoint, theta);
                        handlePointToPixel(pointRotated, world, canvas, affineTransformation);
                    }
                }
            }

        }

        return canvas;
    }

    private Point rotate(Point currentPoint, double theta) {
        return new Point(
            currentPoint.x() * cos(theta) - currentPoint.y() * sin(theta),
            currentPoint.x() * sin(theta) + currentPoint.y() * cos(theta)
        );
    }

    private void handlePointToPixel(Point point, Rectangle world, FractalImage canvas,
        ColoredAffineTransformation affine) {

        if (!world.contains(point)) {
            return;
        }

        int x = getPixelPosition(point.x(), world.x(), world.width(), canvas.width());
        int y = getPixelPosition(point.y(), world.y(), world.height(), canvas.height());

        if (canvas.data()[y][x] == null) {
            canvas.data()[y][x] = new Pixel(affine.color(), 1);
        } else {
            synchronized (canvas.data()[y][x]) {
                Pixel previous = canvas.data()[y][x];
                canvas.data()[y][x] = new Pixel(
                    mixColors(previous.color(), affine.color()), previous.hitCount() + 1
                );
            }
        }
    }

    private Color mixColors(Color first, Color second) {
        return new Color(
            (first.r() + second.r()) / 2,
            (first.g() + second.g()) / 2,
            (first.b() + second.b()) / 2
        );
    }

    private int getPixelPosition(double coordinate, double min, double worldSize, int imageSize) {
        return (int) ((coordinate - min) / (worldSize) * imageSize) % imageSize;
    }
}
