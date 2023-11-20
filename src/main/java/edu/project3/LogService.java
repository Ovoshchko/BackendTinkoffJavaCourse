package edu.project3;

import edu.project3.Configuration.Configuration;
import edu.project3.LogAnalyzer.NginxLogAnalyzer;
import edu.project3.Models.FileList;
import edu.project3.Models.Metric;
import edu.project3.ReportSaver.NginxReportSaver;
import java.io.IOException;
import java.util.List;

public class LogService {

    public void analyze(String[] args) throws IOException {
        Configuration configuration = Configuration.configure(args);
        FileList filesLogs = configuration.getReceiver().receive(
            configuration.getPath(), configuration.getDateLimits()
        );
        List<Metric> metrics = new NginxLogAnalyzer().getLogMetrics(filesLogs.files(), filesLogs.metrics());
        List<String> report = configuration.getReportMaker().makeReport(metrics);
        new NginxReportSaver().save(configuration.getFormat(), report);
    }

}
