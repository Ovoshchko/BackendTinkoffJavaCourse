package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {

    public DefaultConnectionManager() {}

    public Connection getConnection() {

        if (DefaultConnectionManagerSimulation.isFaultyConnection()) {
            return new FaultyConnection();
        }

        return new StableConnection();
    }
}
