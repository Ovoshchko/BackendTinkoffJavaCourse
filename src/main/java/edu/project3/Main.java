package edu.project3;

import edu.project3.LogAnalyzer.NginxLogAnalyzer;
import edu.project3.LogReceiver.LocalLogReceiver;
import edu.project3.LogReceiver.UrlLogReceiver;
import edu.project3.Models.Metric;
import edu.project3.Models.NginxLog;
import edu.project3.ReportMaker.AdocReportMaker;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<NginxLog> logs = new LocalLogReceiver().receive("src/main/resources/edu/project3/");
        List<Metric> metrics = new NginxLogAnalyzer().getLogMetrics(List.of("nginx_logs"), logs);
        List<String> m = new AdocReportMaker().makeReport(metrics);
        for (String sr: m) {
            System.out.println(sr);
        }
    }
}
