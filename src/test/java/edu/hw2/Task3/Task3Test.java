package edu.hw2.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test for Task 3")
public class Task3Test {
    @RepeatedTest(50)
    @DisplayName("--Get FaultConnections Only")
    void faultConnectionManagerTest() {
        ConnectionManager faultyManager = new FaultyConnectionManager();
        assertTrue(faultyManager.getConnection() instanceof FaultyConnection);
    }

    @RepeatedTest(50)
    @DisplayName("--Get StableConnections")
    void stableConnectionManagerTest() {
        ConnectionManager defaultManager = new DefaultConnectionManager();
        assertTrue(defaultManager.getConnection() instanceof StableConnection);
    }

    @Nested
    @DisplayName("PopularCommandExecutor Test for Task 3")
    public class PopularCommandExecutorTest {

        @Test
        @DisplayName("--PopularCommandExecutor FaultyConnectionManager Test")
        void testWithFaultyConnectionManager() {
            PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 10);
            assertThrows(ConnectionException.class, executor::updatePackages);
        }
    }

}
