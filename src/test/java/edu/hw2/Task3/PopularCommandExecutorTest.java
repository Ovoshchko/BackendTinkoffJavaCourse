package edu.hw2.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
@DisplayName("PopularCommandExecutor Test for Task 3")
public class PopularCommandExecutorTest {

    @Test
    @DisplayName("--PopularCommandExecutor FaultyConnectionManager Test")
    void testWithFaultyConnectionManager() {
        PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 10);
        assertThrows(ConnectionException.class, executor::updatePackages);
    }
}
