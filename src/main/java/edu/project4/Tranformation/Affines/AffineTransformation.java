package edu.project4.Tranformation.Affines;

import edu.project4.Model.Point;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import static java.lang.Math.sqrt;

@Getter
public class AffineTransformation implements PreTransformation {

    private final static double BORDER = 1;
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    public AffineTransformation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public static AffineTransformation getRandomTransformation() {
        Random random = ThreadLocalRandom.current();
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;

        do {
            a = random.nextDouble(-1, 1);
            d = random.nextDouble(-(sqrt(1 - a * a)), (sqrt(1 - a * a)));
            b = random.nextDouble(-1, 1);
            e = random.nextDouble(-(sqrt(1 - b * b)), (sqrt(1 - b * b)));
            c = random.nextDouble(-BORDER, BORDER);
            f = random.nextDouble(-BORDER, BORDER);
        } while (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));

        return new AffineTransformation(a, b, c, d, e, f);
    }

    @Override
    public Point apply(Point point) {
        return new Point(a * point.x() + b * point.y() + c, d * point.x() + e * point.y() + f);
    }

}
