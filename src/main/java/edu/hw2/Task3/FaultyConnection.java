package edu.hw2.Task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    private final static Logger LOGGER = LogManager.getLogger(FaultyConnection.class.getName());
    private final static Random RANDOM = new Random();
    private final static double FAULT_PROBABILITY = 1;

    FaultyConnection() {}

    public void execute(String command) {
        if ((RANDOM.nextDouble() % 1) < FAULT_PROBABILITY) {
            throw new ConnectionException();
        } else {
            LOGGER.info("Execute command " + command);
        }
    }

    public void close() {}
}
