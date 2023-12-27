package edu.hw10.Task1.RandomGenerator;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class DoubleRandomGenerator implements RandomGenerator<Double> {
    @Override
    public Double generate(Parameter parameter) {

        double min = parameter.getAnnotation(Min.class) != null
            ? parameter.getAnnotation(Min.class).value() : -Double.MAX_VALUE;
        double max = parameter.getAnnotation(Max.class) != null
            ? parameter.getAnnotation(Max.class).value() : Double.MAX_VALUE;

        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
