package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryExecutor {

    private final ConnectionManager manager;
    private final int maxAttempts;
    private final static Logger LOGGER = LogManager.getLogger(RetryExecutor.class);

    public RetryExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void executeWithRetry(String command) throws Exception {

        int attempts = 0;
        ConnectionException connectionException = new ConnectionException();

        while (attempts < maxAttempts) {
            attempts += 1;
            try (Connection connection = manager.getConnection()) {
                tryExecute(command);
                return;
            } catch (ConnectionException exception) {
                LOGGER.error(exception.getMessage());
            } catch (Exception exception) {
                LOGGER.error(exception.getMessage());
                connectionException.initCause(exception.getCause());
            }
        }

        throw connectionException;
    }

    private void tryExecute(String command) throws Exception {
        try (Connection connection = manager.getConnection()) {
            connection.execute(command);
        }
    }
}
