package edu.hw9.Task1;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;
import static java.lang.Math.round;

public class StatsCollector {

    private final static int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    public static final int PERCENT_MULTIPLIER = 100;
    public static final double PERCENT_DIVIDER = 100d;
    @Getter
    private final ConcurrentMap<String, List<Metric>> metricMap;
    private final ExecutorService threadPool;

    public StatsCollector() {
        metricMap = new ConcurrentHashMap<>();
        threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    public void push(String metricName, double[] data) {
        CompletableFuture<Void> future = aggregateDataFuture(metricName, data);
        future.join();
    }

    private CompletableFuture<Void> aggregateDataFuture(String metricName, double[] data) {
        return CompletableFuture.runAsync(() -> aggregateData(metricName, data), threadPool);
    }

    private void aggregateData(String metricName, double[] data) {
        double min = Double.MAX_VALUE;
        double max = -Double.MAX_VALUE;
        double sum = 0;

        for (double dataUnit: data) {
            if (dataUnit < min) {
                min = dataUnit;
            }
            if (dataUnit > max) {
                max = dataUnit;
            }

            sum += dataUnit;
        }

        metricMap.computeIfAbsent(metricName, name -> new ArrayList<>())
            .add(new Metric(
                round(sum * PERCENT_MULTIPLIER) / PERCENT_DIVIDER,
                round((sum / (data.length == 0 ? 1 : data.length)) * PERCENT_MULTIPLIER) / PERCENT_DIVIDER,
                min, max
            ));
    }
}
