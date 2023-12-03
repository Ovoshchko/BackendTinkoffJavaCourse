package edu.hw7.Task4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PICounterThreads {

    public static final int SQUARE_PROPORTION = 4;

    public double countPiSeveralThreads(int threadCount, long simulationCount) {
        long passedShots = 0;
        long simPerThread = simulationCount / threadCount;
        long simPerFirstThread = simPerThread + simulationCount % threadCount;

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        MultiThreadPiCounter[] counters = new MultiThreadPiCounter[threadCount];

        counters[0] = new MultiThreadPiCounter(simPerFirstThread);
        executorService.submit(counters[0]);

        for (int i = 1; i < threadCount; i++) {
            counters[i] = new MultiThreadPiCounter(simPerThread);
            executorService.submit(counters[i]);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (MultiThreadPiCounter counter: counters) {
            passedShots += counter.getPassedShots();
        }

        return (double) SQUARE_PROPORTION * passedShots / simulationCount;
    }

}
