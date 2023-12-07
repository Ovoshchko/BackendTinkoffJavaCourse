package edu.hw9.Task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class StatsCollectorTest {

    private StatsCollector statsCollector;

    @BeforeEach
    void init() {
            statsCollector = new StatsCollector();
    }

    @ParameterizedTest
    @DisplayName("--StatsCollector push method test")
    @MethodSource("provideMetrics")
    void push(List<InputMetric> metrics, Map<String, List<Metric>> answer) throws InterruptedException {
        Thread[] threads = new Thread[metrics.size()];
        int i = 0;

        for (InputMetric metric: metrics) {
            threads[i] = new Thread(() -> statsCollector.push(metric.name(), metric.data()));
            threads[i++].start();
        }

        for (Thread thread: threads) {
            thread.join();
        }

        Map<String, List<Metric>> result = statsCollector.getMetricMap();

        for (String pair: answer.keySet()) {
            assertTrue(result.containsKey(pair));
            assertEquals(new HashSet<>(answer.get(pair)), new HashSet<>(result.get(pair)));
        }
    }

    public static Stream<Arguments> provideMetrics() {
        return Stream.of(
            Arguments.of(
                new ArrayList<>(List.of(
                    new InputMetric("name1", new double[]{0.1, 0.2, 0.3, 0.4, 0.5}),
                    new InputMetric("name2", new double[]{0.4, 0.4, 0.4}),
                    new InputMetric("name1", new double[]{0.0})
                )),
                new HashMap<>(){{
                    put("name1", List.of(new Metric(1.5, 0.3, 0.1, 0.5), new Metric(0.0, 0.0, 0.0, 0.0)));
                    put("name2", List.of(new Metric(1.2, 0.4, 0.4, 0.4)));
                }}
            ),
            Arguments.of(
                new ArrayList<>(List.of(
                    new InputMetric("name1", new double[]{}),
                    new InputMetric("name2", new double[]{-0.1, 0.0, 0.1}),
                    new InputMetric("name3", new double[]{23.0, 45.0, 90.0, 856.0})
                )),
                new HashMap<>(){{
                    put("name1", List.of(new Metric(0.0, 0.0, Double.MAX_VALUE, -Double.MAX_VALUE)));
                    put("name2", List.of(new Metric(0.0, 0.0, -0.1, 0.1)));
                    put("name3", List.of(new Metric(1014.0, 253.5, 23.0, 856.0)));
                }}
            )
        );
    }
}
