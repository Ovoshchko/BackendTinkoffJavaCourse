package edu.project4.Render;

import edu.project4.Model.ColoredAffineTransformation;
import edu.project4.Model.FractalImage;
import edu.project4.Model.Pixel;
import edu.project4.Model.Rectangle;
import edu.project4.Tranformation.Transformation;
import edu.project4.Tranformation.TransformationList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class RendererTest {

    private final static int HEIGHT = 20;
    private final static int WIDTH = 20;
    private final static int SAMPLES = 1000;
    private final static short ITERS = 20;

    public static Stream<Arguments> provideRenderer() {
        return Stream.of(
            Arguments.of(new OneThreadRenderer()),
            Arguments.of(new MultipleThreadRenderer())
        );
    }

    @ParameterizedTest
    @MethodSource("provideRenderer")
    void render(Renderer renderer) {

        FractalImage fractalImage = new FractalImage(new Pixel[HEIGHT][WIDTH], WIDTH, HEIGHT);
        Rectangle world = new Rectangle(-2, -2, 2 * WIDTH, 2 * HEIGHT);
        List<ColoredAffineTransformation> affines = List.of(ColoredAffineTransformation.getNewColoredAffineTransformation());
        List<Transformation> transformations = List.of(TransformationList.Swirl.getTransformation());

        FractalImage image = new OneThreadRenderer().render(fractalImage, world, affines, transformations,
            SAMPLES, ITERS, 1, 1);

        int hits = Arrays.stream(image.data()).flatMap(Arrays::stream).filter(Objects::nonNull).mapToInt(Pixel::hitCount).sum();

        assertTrue(hits > 0);
    }
}
