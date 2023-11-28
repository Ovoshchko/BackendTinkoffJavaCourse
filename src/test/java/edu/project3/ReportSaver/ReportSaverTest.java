package edu.project3.ReportSaver;

import edu.project3.LogAnalyzer.NginxLogAnalyzer;
import edu.project3.Models.HttpMethod;
import edu.project3.Models.Metric;
import edu.project3.Models.NginxLog;
import edu.project3.ReportMaker.ReportMaker;
import edu.project3.ReportMaker.MdReportMaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class ReportSaverTest {

    @ParameterizedTest
    @DisplayName("--ReportSaver")
    @MethodSource("provideReport")
    void save(ReportMaker maker, List<NginxLog> logs, String format, Path file, Path answer)
        throws IOException {
        List<Metric> metrics = new NginxLogAnalyzer().getLogMetrics(List.of(file.getFileName().toString()),logs);
        Path reportPath = new NginxReportSaver().save(format, maker.makeReport(metrics));
        assertEquals(Files.readAllLines(answer), Files.readAllLines(reportPath));
    }

    private static Stream<Arguments> provideReport() {
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
                "markdown",
                Paths.get("src/test/resources/edu/project3/LogReader/log3.txt"),
                Paths.get("src/test/resources/edu/project3/ReportMaker/reportMd.md")
            )
        );
    }
}
