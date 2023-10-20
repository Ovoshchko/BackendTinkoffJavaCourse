package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {

    private final ConnectionManager manager;
    private final int maxAttempts;
    private final static Logger LOGGER = LogManager.getLogger(PopularCommandExecutor.class.getName());

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() throws Exception {
        tryExecute("apt update && apt update -y");
    }

    private void tryExecute(String command) throws Exception {

        int attempts = 0;
        ConnectionException connectionException = new ConnectionException();

        while (attempts < maxAttempts) {
            attempts += 1;
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (ConnectionException exception) {
                LOGGER.info(exception.getMessage());
            } catch (Exception exception) {
                LOGGER.info(exception.getMessage());
                connectionException.initCause(exception.getCause());
            }
        }

        throw connectionException;
    }

}
