package edu.project3.ReportSaver;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface AbstractReportSaver {

    Path save(String format, List<String> reports) throws IOException;
}
