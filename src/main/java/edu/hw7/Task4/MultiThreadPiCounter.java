package edu.hw7.Task4;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MultiThreadPiCounter implements Runnable {

    private final static int R = 1;
    private final static int DOUBLE_R = R * 2;
    private final long simulationCount;
    private long passedShots;

    public MultiThreadPiCounter(long simulationCount) {
        this.simulationCount = simulationCount;
    }

    @Override
    public void run() {
        Random random = ThreadLocalRandom.current();
        double x;
        double y;

        for (long i = 0; i < simulationCount; i++) {
            x = random.nextDouble() * DOUBLE_R - R;
            y = random.nextDouble() * DOUBLE_R - R;
            if (x * x + y * y < R) {
                passedShots++;
            }
        }
    }

    public long getPassedShots() {
        return passedShots;
    }
}
