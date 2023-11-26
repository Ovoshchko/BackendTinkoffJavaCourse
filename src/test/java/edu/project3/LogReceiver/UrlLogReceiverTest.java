package edu.project3.LogReceiver;

import edu.project3.Models.DateLimits;
import edu.project3.Models.FileList;
import edu.project3.Models.HttpMethod;
import edu.project3.Models.NginxLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class UrlLogReceiverTest {

    private final String filePath = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

    @ParameterizedTest
    @DisplayName("--Receiver Test")
    @MethodSource("provideFileContent")
    void receive(Receiver receiver, FileList fileList, DateLimits dateLimits) throws IOException {
        FileList result = receiver.receive(filePath, dateLimits);
        assertEquals(51462, result.metrics().size());
        assertEquals(fileList.metrics().get(0), result.metrics().get(10));
    }

    private static Stream<Arguments> provideFileContent() {
        return Stream.of(
            Arguments.of(
                new UrlLogReceiver(),
                new FileList(List.of("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"),
                    List.of(
                        new NginxLog(
                            "217.168.17.5",
                            LocalDateTime.of(2015, 5, 17, 8, 5, 12),
                            HttpMethod.GET,
                            "/downloads/product_2",
                            "HTTP/1.1",
                            200,
                            3316
                        )
                    )
                ),
                new DateLimits(LocalDate.of(1970, 1, 1), LocalDate.now())
            )
        );
    }
}
