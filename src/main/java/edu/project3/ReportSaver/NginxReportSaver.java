package edu.project3.ReportSaver;

import edu.project3.ReportSaver.Utils.FileFormats;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NginxReportSaver implements ReportSaver {

    private final static String PATH_TO_LOGS = "src/main/resources/edu/project3/";
    private final static String DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss_SSSSSS";

    @Override
    public Path save(String format, List<String> reports) throws IOException {
        String reportName = PATH_TO_LOGS + "report-"
            + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT))
            + FileFormats.valueOf(format.toUpperCase()).getExtension();

        try {
            Path pathToReport = Paths.get(reportName);

            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(pathToReport)) {
                for (String report: reports) {
                    bufferedWriter.write(report);
                }
            }

            return pathToReport;
        } catch (IOException exception) {
            throw new IOException();
        }

    }
}
