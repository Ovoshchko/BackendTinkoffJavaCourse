package edu.hw10.Task1.RandomGenerator;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class IntRandomGenerator implements RandomGenerator<Integer> {

    @Override
    public Integer generate(Parameter parameter) {

        int min = parameter.getAnnotation(Min.class) != null
            ? parameter.getAnnotation(Min.class).value() : Integer.MIN_VALUE;
        int max = parameter.getAnnotation(Max.class) != null
            ? parameter.getAnnotation(Max.class).value() : Integer.MAX_VALUE;


        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
