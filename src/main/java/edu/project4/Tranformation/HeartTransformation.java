package edu.project4.Tranformation;

import edu.project4.Model.Point;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = getRadius(point);
        return new Point(radius * sin(atan2(point.x(), point.y()) * radius),
            -radius * cos(atan2(point.x(), point.y()) * radius));
    }
}
