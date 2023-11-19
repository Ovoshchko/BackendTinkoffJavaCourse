package edu.project3.LogAnalyzer;

import edu.project3.Models.HttpCodeMessages;
import edu.project3.Models.Metric;
import edu.project3.Models.NginxLog;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class NginxLogAnalyzer implements AbstractLogAnalyzer {
    @Override
    public List<Metric> getLogMetrics(List<String> filenames, List<NginxLog> logs) {
        List<Metric> metrics = new ArrayList<>();

        metrics.add(getAvgInformation(filenames, logs));
        metrics.add(getMostRequestedFiles(logs));
        metrics.add(getAllResponseCodes(logs));

        return metrics;
    }

    private Metric getAvgInformation(List<String> filenames, List<NginxLog> logs) {
        Metric avgInformation = new Metric("####Общая информация", new HashMap<>());
        Map<String, List<String>> metricList = avgInformation.metrics();

        metricList.put("Файл(-ы)", filenames);
        metricList.put("Начальная дата" , getStartDate(logs));
        metricList.put("Конечная дата", getLastDate(logs));
        metricList.put("Количество запросов", getValidLogsAmount(logs));
        metricList.put("Среднее количество байт", getAvgByteSize(logs));

        return avgInformation;
    }

    public Metric getMostRequestedFiles(List<NginxLog> logs) {
        return new Metric("#### Запрашиваемые ресурсы", getMostRequestedFilesCount(logs));
    }

    public Metric getAllResponseCodes(List<NginxLog> logs) {
        return new Metric("#### Коды ответа" , getCountedResponseCodes(logs));
    }

    private List<String> getStartDate(List<NginxLog> logs) {
        LocalDateTime time = logs.stream().map(NginxLog::dataTime)
            .min(LocalDateTime::compareTo).orElse(LocalDateTime.now());
        return List.of(time.toString());
    }

    private List<String> getLastDate(List<NginxLog> logs) {
        LocalDateTime time = logs.stream().map(NginxLog::dataTime)
            .max(LocalDateTime::compareTo).orElse(LocalDateTime.now());
        return List.of(time.toString());
    }

    private List<String> getValidLogsAmount(List<NginxLog> logs) {
        long size = logs.stream().filter(log -> !log.equals(NginxLog.getInvalidLog())).count();
        return List.of(Long.toString(size));
    }

    private List<String> getAvgByteSize(List<NginxLog> logs) {
        OptionalDouble avg = logs.stream().mapToInt(NginxLog::bytes).average();
        return avg.isEmpty() ? List.of(Double.toString(0)) : List.of(Double.toString(avg.getAsDouble()));
    }

    private Map<String, List<String>> getMostRequestedFilesCount(List<NginxLog> logs) {
        Map<String, List<String>> filesMetric = new LinkedHashMap<>();
        filesMetric.put("Ресурс", List.of("Количество"));
        logs.stream()
            .collect(Collectors.groupingBy(NginxLog::resource, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(3)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> Collections.singletonList(String.valueOf(entry.getValue())),
                (e1, e2) -> e1,
                LinkedHashMap::new
            ))
            .forEach(filesMetric::put);

        return filesMetric;
    }

    private Map<String, List<String>> getCountedResponseCodes(List<NginxLog> logs) {
        Map<String, List<String>> responsesMetric = new LinkedHashMap<>();
        responsesMetric.put("Код", List.of("Имя", "Количество"));
        logs.stream()
            .collect(Collectors.groupingBy(NginxLog::responseCode, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(3)
            .collect(Collectors.toMap(
                entry -> String.valueOf(entry.getKey()),
                entry -> List.of(HttpCodeMessages.getReasonForStatus(entry.getKey())
                    , String.valueOf(entry.getValue())),
                (e1, e2) -> e1,
                LinkedHashMap::new
        ))
            .forEach(responsesMetric::put);

        return responsesMetric;
    }

}
