package edu.hw7.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class PICounterThreadsTest {

    @ParameterizedTest
    @DisplayName("--Single thread PI Counter Test")
    @ValueSource(longs = {1, 50, 100000, 100000000})
    void countPiSeveralThreads(long shoots) {
        PICounterThreads counter = new PICounterThreads();

        double result = counter.countPiSeveralThreads(Runtime.getRuntime().availableProcessors(), shoots);
        boolean valid = (result <= 4) && (result >= 0);

        assertTrue(valid);
    }
}
