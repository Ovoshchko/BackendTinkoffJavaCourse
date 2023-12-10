package edu.project4.Tranformation;

import edu.project4.Model.Point;

public class LinearTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        return new Point(point.x(), point.y());
    }
}
