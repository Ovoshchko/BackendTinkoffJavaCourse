package edu.project3.Configuration;

import edu.project3.LogReceiver.AbstractReceiver;
import edu.project3.LogReceiver.LocalLogReceiver;
import edu.project3.LogReceiver.UrlLogReceiver;
import edu.project3.Models.DateLimits;
import edu.project3.ReportMaker.AbstractReportMaker;
import edu.project3.ReportMaker.AdocReportMaker;
import edu.project3.ReportMaker.MdReportMaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @ParameterizedTest
    @DisplayName("--Configurations test")
    @MethodSource("provideArgs")
    void configure(String[] args, String path, Class<? extends AbstractReceiver> receiver, String format,
        Class<? extends AbstractReportMaker> reportMaker, DateLimits dateLimits
        ) {
        Configuration configuration = Configuration.configure(args);
        assertEquals(path, configuration.getPath());
        assertEquals(receiver, configuration.getReceiver().getClass());
        assertEquals(format, configuration.getFormat());
        assertEquals(reportMaker, configuration.getReportMaker().getClass());
        assertEquals(dateLimits, configuration.getDateLimits());
    }

    private static Stream<Arguments> provideArgs() {
        return Stream.of(
            Arguments.of(new String[]{"--path", "src", "--format", "adoc"},
                "src", LocalLogReceiver.class, "adoc", AdocReportMaker.class, new DateLimits(
                    LocalDate.of(1970, 1, 1),
                    LocalDate.now()
                )
            ),
            Arguments.of(new String[]{"--path", "https://ok/not", "--to", "2020-10-12"},
                "https://ok/not", UrlLogReceiver.class, "markdown", MdReportMaker.class, new DateLimits(
                    LocalDate.of(1970, 1, 1),
                    LocalDate.of(2020, 10, 12)
                )
            )
        );
    }
}
