package edu.project4.Tranformation;

import edu.project4.Model.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = getSquaredRadius(point);
        return new Point(point.x() / (radius), point.y() / (radius));
    }
}
