package edu.hw7.Task2;

import java.util.stream.IntStream;

public class ParallelFactorial {

    private final static String ARGUMENT_ERROR = "Range must be non-negative";

    public int countFactorial(int range) {

        if (range < 0) {
            throw new IllegalArgumentException(ARGUMENT_ERROR);
        }

        return IntStream.range(1, range + 1).parallel().reduce(1, Math::multiplyExact);
    }

}
