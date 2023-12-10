package edu.project4.Tranformation;

import edu.project4.Model.Point;
import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class DiscTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = getRadius(point);
        double argument = atan2(point.x(), point.y()) / PI;
        return new Point(argument * sin(PI * radius), argument * cos(PI * radius));
    }
}
