package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {

    private static int cycleCounter = 0;
    private final static int FAULTY_CYCLE = 5;

    public DefaultConnectionManager() {}

    public Connection getConnection() {
        cycleCounter = (cycleCounter + 1) % FAULTY_CYCLE;

        if (cycleCounter == 0) {
            return new FaultyConnection();
        }

        return new StableConnection();
    }
}
