package edu.hw2.Task3;

import java.util.Random;

public final class FaultConnectionSimulation {

    private FaultConnectionSimulation() {}

    private final static Random RANDOM = new Random();
    private final static double FAULT_PROBABILITY = 1;

    public static boolean isConnectionFailed() {
        return (RANDOM.nextDouble() % 1) < FAULT_PROBABILITY;
    }
}
