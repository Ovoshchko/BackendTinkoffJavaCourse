package edu.project3.LogParser;

import edu.project3.Models.HttpMethod;
import edu.project3.Models.NginxLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class NginxLogParserTest {

    private final NginxLogParser parser = new NginxLogParser();

    @ParameterizedTest
    @DisplayName("--LogParser test")
    @MethodSource("getRawLogs")
    void readLogs(String rawLog, NginxLog answer) {
        NginxLog log = parser.parseLog(rawLog);
        assertEquals(answer, log);
    }

    private static Stream<Arguments> getRawLogs() {
        return Stream.of(
            Arguments.of(
                "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
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
        );
    }
}
