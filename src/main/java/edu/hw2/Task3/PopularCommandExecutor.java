package edu.hw2.Task3;

public final class PopularCommandExecutor {

    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() throws Exception {
        RetryExecutor retryExecutor = new RetryExecutor(manager, maxAttempts);
        retryExecutor.executeWithRetry("apt update && apt update -y");
    }


}
