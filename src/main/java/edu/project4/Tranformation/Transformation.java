package edu.project4.Tranformation;

import edu.project4.Model.Point;
import java.util.function.Function;
import static java.lang.Math.sqrt;

public interface Transformation extends Function<Point, Point> {

    default double getRadius(Point point) {
        return sqrt(point.x() * point.x() + point.y() * point.y());
    }

    default double getSquaredRadius(Point point) {
        return point.x() * point.x() + point.y() * point.y();
    }
}
