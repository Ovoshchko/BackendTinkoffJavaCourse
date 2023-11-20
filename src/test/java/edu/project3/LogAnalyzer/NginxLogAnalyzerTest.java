package edu.project3.LogAnalyzer;

import edu.project3.Models.HttpMethod;
import edu.project3.Models.Metric;
import edu.project3.Models.NginxLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class NginxLogAnalyzerTest {

    private NginxLogAnalyzer logAnalyzer = new NginxLogAnalyzer();

    @ParameterizedTest
    @DisplayName("--NginxLogAnalyzer")
    @MethodSource("getLogs")
    void getLogMetrics(List<String> fileNames, List<NginxLog> logs) {
        assertEquals(3, logAnalyzer.getLogMetrics(fileNames, logs).size());
    }

    private static Stream<Arguments> getLogs() {
        return Stream.of(
            Arguments.of(
                List.of("File"),
                List.of(
                    new NginxLog(
                        "93.180.71.3",
                        LocalDateTime.of(2015, 5, 17, 8, 5, 32),
                        HttpMethod.GET,
                        "/downloads/product_1",
                        "HTTP/1.1",
                        304,
                        0
                    )
                )
            )
        );
    }
}
