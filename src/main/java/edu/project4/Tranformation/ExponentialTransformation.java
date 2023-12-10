package edu.project4.Tranformation;

import edu.project4.Model.Point;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.sin;

public class ExponentialTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double exponent = exp(point.x() - 1);
        return new Point(exponent * cos(PI * point.y()), exponent * sin(PI * point.y()));
    }
}
