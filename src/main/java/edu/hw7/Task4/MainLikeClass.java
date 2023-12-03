package edu.hw7.Task4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainLikeClass {

    private final static long[] COMPARE_ARRAY = new long[]{1000000, 100000000, 1000000000};
    private final static Path REPORT_PATH = Paths.get("src/main/resources/edu/hw7/Task4");
    private final static String REPORT_NAME = "Report.txt";
    private final Path reportPath = Paths.get(REPORT_PATH.toString() + REPORT_NAME);

    public void runComparator() throws IOException {
        PIComparator comparator = new PIComparator();
        StringBuilder report = new StringBuilder();

        Files.deleteIfExists(reportPath);

        for (long comparable: COMPARE_ARRAY) {
            report.append(comparator.compare(comparable));
        }

        Files.createFile(reportPath);

        try (FileOutputStream outputStream = new FileOutputStream(reportPath.toFile())) {
            outputStream.write(report.toString().getBytes());
        }
    }

    public Path getReportPath() {
        return reportPath;
    }

}
