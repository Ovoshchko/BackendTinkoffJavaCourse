package edu.project4.Tranformation;

import edu.project4.Model.Point;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SwirlTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = getSquaredRadius(point);
        return new Point(point.x() * sin(radius) - point.y() * cos(radius),
            point.x() * cos(radius) + point.y() * sin(radius));
    }
}
