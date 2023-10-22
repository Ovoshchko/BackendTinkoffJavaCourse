package edu.hw2.Task3;

import java.util.Random;

public final class DefaultConnectionManagerSimulation {

    private DefaultConnectionManagerSimulation() {}

    private final static Random RANDOM = new Random();
    private final static double FAULTY_CONNECTION_PROBABILITY = 0;

    public static boolean isFaultyConnection() {
        return (RANDOM.nextDouble() % 1) < FAULTY_CONNECTION_PROBABILITY;
    }
}
