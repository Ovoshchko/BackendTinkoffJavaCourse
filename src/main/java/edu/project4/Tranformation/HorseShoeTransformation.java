package edu.project4.Tranformation;

import edu.project4.Model.Point;

public class HorseShoeTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point((point.x() - point.y()) * (point.x() + point.y()) / getRadius(point),
            2.0 * point.x() * point.y() / getRadius(point));
    }
}
