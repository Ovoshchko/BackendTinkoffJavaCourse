package edu.project3.Configuration;

import edu.project3.Configuration.Utils.ReceiverType;
import edu.project3.Configuration.Utils.ReportType;
import edu.project3.LogReceiver.Receiver;
import edu.project3.Models.DateLimits;
import edu.project3.ReportMaker.ReportMaker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class Configuration {

    private final static String URL_PATTERN = "https://.*";
    private final static String DATE_FORMAT = "yyyy-MM-dd";
    private final static int START_YEAR = 1970;
    private final String path;
    private final Receiver receiver;
    private final String format;
    private final ReportMaker reportMaker;
    private final DateLimits dateLimits;

    private Configuration(
        String path, Receiver receiver, String format, ReportMaker reportMaker, DateLimits dateLimits) {
        this.path = Objects.requireNonNull(path);
        this.receiver = receiver;
        this.format = format;
        this.reportMaker = reportMaker;
        this.dateLimits = dateLimits;
    }

    public static Configuration configure(String[] args) {
        String path = "";
        LocalDate from = LocalDate.of(START_YEAR, 1, 1);
        LocalDate to = LocalDate.now();
        String format = "markdown";

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "--path":
                    path = args[i + 1];
                    break;
                case "--from":
                    from = LocalDate.parse(args[i + 1], DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH));
                    break;
                case "--to":
                    to = LocalDate.parse(args[i + 1], DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH));
                    break;
                case "--format":
                    format = args[i + 1];
                    break;
                default:
                    throw new RuntimeException("Введены неверные аргументы");
            }
        }

        return new Configuration(
            path,
            Pattern.compile(URL_PATTERN).matcher(path).matches() ? ReceiverType.URL.createReceiver()
                : ReceiverType.LOCAL.createReceiver(),
            format,
            ReportType.getByFormat(format).createReportMaker(),
            new DateLimits(from, to)
        );
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public ReportMaker getReportMaker() {
        return reportMaker;
    }

    public DateLimits getDateLimits() {
        return dateLimits;
    }

    public String getPath() {
        return path;
    }

    public String getFormat() {
        return format;
    }
}
