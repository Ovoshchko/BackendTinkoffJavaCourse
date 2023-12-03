package edu.hw8.Task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FixedThreadPoolTest {

    private final static int THREAD_AMOUNT = Runtime.getRuntime().availableProcessors();
    private final static long[] FIBONACCI_NUMBERS = {
        1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L, 610L, 987L, 1597L, 2584L, 4181L, 6765L
    };
    private final Map<Integer, Long> memoryNumbers = new ConcurrentHashMap<>();
    private ThreadPool threadPool;

    @BeforeEach
    void initStart() {
        threadPool = FixedThreadPool.create(THREAD_AMOUNT);
        threadPool.start();
    }

    @ParameterizedTest
    @DisplayName("--Fibonacci compute test")
    @ValueSource(ints = {1, 5 ,10})
    void Fibonacci(int limit) throws InterruptedException {
        long[] answers = new long[limit];
        for (int i = 0; i < limit; i++) {
            final int currentCycle = i + 1;
            threadPool.execute(() -> answers[currentCycle - 1] = calculateFibonacci(currentCycle));
        }

        Thread.sleep(1000);

        for (int i = 0; i < limit; i++) {
            assertEquals(FIBONACCI_NUMBERS[i], answers[i]);
        }
    }

    private long calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        return memoryNumbers.computeIfAbsent(n, key -> calculateFibonacci(key - 1) + calculateFibonacci(key - 2));
    }

}
