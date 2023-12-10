package edu.project4.Tranformation;

import edu.project4.Model.Point;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class HandkerchiefTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double theta = atan2(point.x(), point.y());
        double radius = getRadius(point);
        return new Point(radius * sin(theta + radius), radius * cos(theta - radius));
    }
}
