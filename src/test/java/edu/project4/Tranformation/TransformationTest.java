package edu.project4.Tranformation;

import edu.project4.Model.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class TransformationTest {

    private final static Point POINT = new Point(0.5, 1.2);
    private final static double RADIUS = 1.3;
    private final static double RADIUS_SQUARED = 1.69;

    @Test
    void getRadius() {
        double radius = new CylinderTransformation().getRadius(POINT);
        assertEquals(RADIUS, radius);
    }

    @Test
    void getSquaredRadius() {
        double radiusSquared = new CylinderTransformation().getSquaredRadius(POINT);
        assertEquals(RADIUS_SQUARED, radiusSquared);
    }

    @ParameterizedTest
    @MethodSource("provideTransformations")
    void apply(Point in, Transformation transformation, Point answer) {
        Point result = transformation.apply(in);
        if (abs(result.x()) < 1.0E-12) {
            result = new Point(0, result.y());
        }
        if (abs(result.y()) < 1.0E-12) {
            result = new Point(result.x(), 0);
        }
        assertEquals(answer, result);
    }

    private static Stream<Arguments> provideTransformations() {
        return Stream.of(
            Arguments.of(new Point(0, 0.4), new CylinderTransformation(), new Point(0, 0.4)),
            Arguments.of(new Point(0, PI), new DiamondTransformation(), new Point(-0, 0)),
            Arguments.of(new Point(0, 1), new DiscTransformation(), new Point(0, 0)),
            Arguments.of(new Point(1, 1), new ExponentialTransformation(), new Point(-1, 0)),
            Arguments.of(new Point(PI, 0), new HandkerchiefTransformation(), new Point(-PI, 0)),
            Arguments.of(new Point(0, PI), new HeartTransformation(), new Point(0, -PI)),
            Arguments.of(new Point(1, 0), new HorseShoeTransformation(), new Point(1, 0)),
            Arguments.of(new Point(1, 1), new LinearTransformation(), new Point(1, 1)),
            Arguments.of(new Point(1, 0), new PolarTransformation(), new Point(0.5, 0)),
            Arguments.of(new Point(PI, PI), new SinusoidalTransformation(), new Point(0, 0)),
            Arguments.of(new Point(PI, 0), new SphericalTransformation(), new Point(1 / PI, 0)),
            Arguments.of(new Point(sqrt(PI), 0), new SwirlTransformation(), new Point(0, -sqrt(PI)))
        );
    }

}
