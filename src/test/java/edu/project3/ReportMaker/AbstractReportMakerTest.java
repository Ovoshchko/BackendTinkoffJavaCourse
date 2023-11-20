package edu.project3.ReportMaker;

import edu.project3.LogAnalyzer.NginxLogAnalyzer;
import edu.project3.Models.HttpMethod;
import edu.project3.Models.Metric;
import edu.project3.Models.NginxLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class AbstractReportMakerTest {

    @ParameterizedTest
    @DisplayName("--Reporters test")
    @MethodSource("provideReports")
    void makeReport(AbstractReportMaker reportMaker, List<NginxLog> logs, List<String> fileNames)
        throws IOException {
        List<Metric> metrics = new NginxLogAnalyzer().getLogMetrics(fileNames, logs);

        List<String> report = reportMaker.makeReport(metrics);
        assertEquals(3, report.size());
        assertTrue(Pattern.compile(".*Коды ответа.*").matcher(report.get(2)).find());
    }

    private static Stream<Arguments> provideReports() {
        return Stream.of(
            Arguments.of(
                new MdReportMaker(),
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
                ),
                List.of("log3.txt")
            ),
            Arguments.of(
                new AdocReportMaker(),
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
                ),
                List.of("log3.txt")
            )
        );
    }
}
