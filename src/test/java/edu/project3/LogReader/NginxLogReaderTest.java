package edu.project3.LogReader;

import edu.project3.Models.DateLimits;
import edu.project3.Models.HttpMethod;
import edu.project3.Models.NginxLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class NginxLogReaderTest {

    private final AbstractLogReader reader = new NginxLogReader();

    @ParameterizedTest
    @DisplayName("--LogReader Test")
    @MethodSource("provideLogAnswer")
    void readLogs(List<String> logs, DateLimits dateLimits,  List<NginxLog> answer) {
        assertEquals(answer, reader.readLogs(logs, dateLimits));
    }

    private static Stream<Arguments> provideLogAnswer() {
        return Stream.of(
            Arguments.of(
                List.of("93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""),
                new DateLimits(LocalDate.of(1970, 1, 1), LocalDate.now()),
                List.of(
                    new NginxLog(
                        "93.180.71.3",
                        LocalDateTime.of(2015, 5, 17, 8, 5, 32),
                        HttpMethod.GET,
                        "/downloads/product_1",
                        "HTTP/1.1",
                        304,
                        0
                    ))
            )
        );
    }
}
