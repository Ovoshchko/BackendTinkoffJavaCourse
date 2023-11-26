package edu.hw7.Task4;

import java.util.Random;

public class PICounter {

    private final static int R = 1;
    private final static int DOUBLE_R = R * 2;
    public static final int SQUARE_PROPORTION = 4;
    private final Random random = new Random();

    public double countPiOneThread(long simulationCount) {
        long count = simulateShots(simulationCount);
        return (double) SQUARE_PROPORTION * count / simulationCount;
    }

    private long simulateShots(long simulationCount) {
        long passedShots = 0;
        double x;
        double y;

        for (long i = 0; i < simulationCount; i++) {
            x = generateRandomCoordinate();
            y = generateRandomCoordinate();
            if (x * x + y * y < R) {
                passedShots++;
            }
        }

        return passedShots;
    }

    private double generateRandomCoordinate() {
        return random.nextDouble() * DOUBLE_R - R;
    }
}
