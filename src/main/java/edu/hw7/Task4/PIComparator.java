package edu.hw7.Task4;

import java.util.concurrent.Callable;

public class PIComparator {

    private final static double PI = 3.1415926536;
    private final static String SEPARATOR = "-".repeat(50);
    public static final int PERCENTS_TRANSFER = 100;

    public String compare(long simulationCounter) {
        Stats[] stats = new Stats[]{getOneThreadStats(simulationCounter), getSeveralThreadsStats(simulationCounter)};
        StringBuilder report = new StringBuilder();

        report.append("Количество симуляций: ").append(simulationCounter)
            .append(System.lineSeparator()).append(SEPARATOR).append(System.lineSeparator())
            .append(renderOneThreadStat(stats[0]))
            .append(renderMultiThreadStat(stats[1]))
            .append(renderDifference(stats[0], stats[1]))
            .append(System.lineSeparator());

        return report.toString();
    }

    private Stats getOneThreadStats(long simulationCounter) {
        return getPIStats(() -> new PICounter().countPiOneThread(simulationCounter));
    }

    private Stats getSeveralThreadsStats(long simulationCounter) {
        return getPIStats(() ->
            new PICounterThreads().countPiSeveralThreads(Runtime.getRuntime().availableProcessors(), simulationCounter)
        );
    }

    private Stats getPIStats(Callable<Double> function) {
        long start = System.currentTimeMillis();
        try {
            double result = function.call();
            return new Stats(System.currentTimeMillis() - start,
                Math.abs(PI - result) / PI * PERCENTS_TRANSFER
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String renderOneThreadStat(Stats stats) {
        return "Однопоточное приложение - " + renderStat(stats);
    }

    private String renderMultiThreadStat(Stats stats) {
        return "Многопоточное приложение - " + renderStat(stats);
    }

    private String renderStat(Stats stats) {
        return "Время: " + stats.time() + "мс  Ошибка: " + String.format("%.5f", stats.error())
            + System.lineSeparator();
    }

    private String renderDifference(Stats oneThreadStat, Stats multiThreadStat) {
        return "Многопоточное приложение быстрее однопоточного в "
            + (double) oneThreadStat.time() / multiThreadStat.time() + " раз" + System.lineSeparator();
    }
}
