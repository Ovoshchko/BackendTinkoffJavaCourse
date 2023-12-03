package edu.hw7.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AtomicCounterTest {

    @ParameterizedTest
    @DisplayName("--Atomic value Test")
    @CsvSource({"100, 100", "1000, 1000", "5000, 5000"})
    void testIncreaseCounterTwoThreads(int count, int expected) throws InterruptedException {
        AtomicCounter atomicCounter = new AtomicCounter();
        int result = atomicCounter.increaseCounterTwoThreads(count);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @DisplayName("--Thread safety Test")
    @CsvSource({"5, 10", "10, 20", "20, 40"})
    void testThreadSafety(int threadCount, int countPerThread) throws InterruptedException {
        AtomicCounter atomicCounter = new AtomicCounter();
        int[] results = new int[threadCount];
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            final int start = index * countPerThread;
            final int end = start + countPerThread;
            threads[i] = new Thread(() -> results[index] = atomicCounter.increaseCounterTwoThreads(end - start));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int totalResult = Arrays.stream(results).max().getAsInt();

        assertEquals(threadCount * countPerThread, totalResult);
    }
}
