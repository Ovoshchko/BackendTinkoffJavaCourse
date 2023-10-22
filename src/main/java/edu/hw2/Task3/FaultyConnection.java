package edu.hw2.Task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    private final static Logger LOGGER = LogManager.getLogger(FaultyConnection.class.getName());

    FaultyConnection() {}

    public void execute(String command) {
        if (FaultConnectionSimulation.isConnectionFailed()) {
            throw new ConnectionException();
        } else {
            LOGGER.info("Execute command " + command);
        }
    }

    public void close() {}
}
