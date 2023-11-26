package edu.project3.LogAnalyzer;

import edu.project3.Models.HttpCodeMessages;
import edu.project3.Models.Metric;
import edu.project3.Models.NginxLog;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Properties;
import java.util.stream.Collectors;

public class NginxLogAnalyzer implements LogAnalyzer {

    private final static int TOP_COUNT = 3;
    public static final String RESOURCE_PATH = "src/main/resources/edu/project3/LogAnalyzer/properties_ru.properties";
    public static final String AMOUNT_PROPERTY = "amount";
    private Properties properties = new Properties();

    public NginxLogAnalyzer() {
        try (InputStream input = new FileInputStream(RESOURCE_PATH)) {
            properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Metric> getLogMetrics(List<String> filenames, List<NginxLog> logs) {
        List<Metric> metrics = new ArrayList<>();

        metrics.add(getAvgInformation(filenames, logs));
        metrics.add(getMostRequestedFiles(logs));
        metrics.add(getAllResponseCodes(logs));

        return metrics;
    }

    private Metric getAvgInformation(List<String> filenames, List<NginxLog> logs) {
        Metric avgInformation = new Metric(properties.getProperty("average_information"), new LinkedHashMap<>());
        Map<String, List<String>> metricList = avgInformation.metrics();

        metricList.put(properties.getProperty("metric"), List.of(properties.getProperty("value")));
        metricList.put(properties.getProperty("files"), filenames);
        metricList.put(properties.getProperty("initial_date"), getStartDate(logs));
        metricList.put(properties.getProperty("last_date"), getLastDate(logs));
        metricList.put(properties.getProperty("received_amount"), getValidLogsAmount(logs));
        metricList.put(properties.getProperty("average_bytes"), getAvgByteSize(logs));

        return avgInformation;
    }

    private Metric getMostRequestedFiles(List<NginxLog> logs) {
        return new Metric(properties.getProperty("requested_resources"), getMostRequestedFilesCount(logs));
    }

    private Metric getAllResponseCodes(List<NginxLog> logs) {
        return new Metric(properties.getProperty("response_codes"), getCountedResponseCodes(logs));
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
        filesMetric.put(properties.getProperty("resource"), List.of(properties.getProperty(AMOUNT_PROPERTY)));
        logs.stream()
            .collect(Collectors.groupingBy(NginxLog::resource, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(TOP_COUNT)
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
        responsesMetric.put(properties.getProperty("code"), List.of(
            properties.getProperty("name"), properties.getProperty(AMOUNT_PROPERTY)
        ));
        logs.stream()
            .collect(Collectors.groupingBy(NginxLog::responseCode, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(TOP_COUNT)
            .collect(Collectors.toMap(
                entry -> String.valueOf(entry.getKey()),
                entry -> List.of(HttpCodeMessages.getReasonForStatus(entry.getKey()),
                    String.valueOf(entry.getValue())),
                (e1, e2) -> e1,
                LinkedHashMap::new
        ))
            .forEach(responsesMetric::put);

        return responsesMetric;
    }

}
