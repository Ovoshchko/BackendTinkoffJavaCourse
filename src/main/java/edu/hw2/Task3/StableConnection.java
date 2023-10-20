package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {

    private final static Logger LOGGER = LogManager.getLogger(StableConnection.class.getName());

    StableConnection() {
    }

    public void execute(String command) {
        LOGGER.info("Execute command " + command);
    }

    public void close() {}
}
