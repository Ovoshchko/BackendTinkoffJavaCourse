package edu.project4.Tranformation;

import edu.project4.Model.Point;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class DiamondTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = getRadius(point);
        double theta = atan2(point.x(), point.y());
        return new Point(sin(theta) * cos(radius), cos(theta) * sin(radius));
    }
}
