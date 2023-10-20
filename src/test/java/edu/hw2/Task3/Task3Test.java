package edu.hw2.Task3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test for Task 3")
public class Task3Test {
    @RepeatedTest(50)
    @DisplayName("--Get FaultConnections Only")
    void faultConnectionManagerTest() {
        ConnectionManager faultyManager = new FaultyConnectionManager();
        assertTrue(faultyManager.getConnection() instanceof FaultyConnection);
    }

    @Test
    @DisplayName("--Get StableConnections")
    void stableConnectionManagerTest() {
        ConnectionManager defaultManager = new DefaultConnectionManager();
        for (int i = 1; i <= 30; i++) {
            if (i % 5 == 0) {
                assertTrue(defaultManager.getConnection() instanceof FaultyConnection);
            } else {
                assertTrue(defaultManager.getConnection() instanceof StableConnection);
            }
        }
    }

}
