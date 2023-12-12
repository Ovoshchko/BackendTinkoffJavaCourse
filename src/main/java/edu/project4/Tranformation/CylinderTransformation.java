package edu.project4.Tranformation;

import edu.project4.Model.Point;
import static java.lang.Math.sin;

public class CylinderTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(sin(point.x()), point.y());
    }
}
