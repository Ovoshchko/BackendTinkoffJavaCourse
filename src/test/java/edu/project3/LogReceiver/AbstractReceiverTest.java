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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class AbstractReceiverTest {

    private final String filePath = "src/test/resources/edu/project3/LogReader/log3.txt";

    @ParameterizedTest
    @DisplayName("--Receiver Test")
    @MethodSource("provideFileContent")
    void receive(AbstractReceiver receiver, FileList fileList, DateLimits dateLimits) throws IOException {
        FileList result = receiver.receive(filePath, dateLimits);
        assertEquals(fileList.files(), result.files());
        assertEquals(fileList.metrics(), result.metrics());
    }

    private static Stream<Arguments> provideFileContent() {
        return Stream.of(
            Arguments.of(
                new LocalLogReceiver(),
                new FileList(List.of("log3.txt"), List.of(
                    new NginxLog(
                        "93.180.71.3",
                        LocalDateTime.of(2015, 5, 17, 8, 5, 32),
                        HttpMethod.GET,
                        "/downloads/product_1",
                        "HTTP/1.1",
                        304,
                        0
                    )
                )),
                new DateLimits(LocalDate.of(1970, 1, 1), LocalDate.now())
            )
        );
    }
}
