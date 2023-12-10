package edu.project4.Tranformation;

import edu.project4.Model.Point;
import static java.lang.Math.PI;
import static java.lang.Math.atan2;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double theta = atan2(point.x(), point.y());
        return new Point(theta / PI, getRadius(point) - 1);
    }
}
