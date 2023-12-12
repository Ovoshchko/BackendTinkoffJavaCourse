package edu.project4.Model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public record Rectangle(double x, double y, double width, double height) {

    public boolean contains(Point point) {
        return (point.x() >= x) && (point.x() <= x + width) && (point.y() >= y) && (point.y() <= y + height);
    }

    public Point gerRandomPoint() {
        Random random = ThreadLocalRandom.current();
        return new Point(random.nextDouble(x, x + width), random.nextDouble(y, y + height));
    }
}
