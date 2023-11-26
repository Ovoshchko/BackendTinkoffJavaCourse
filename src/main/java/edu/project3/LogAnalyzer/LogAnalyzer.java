package edu.project3.LogAnalyzer;

import edu.project3.Models.Metric;
import edu.project3.Models.NginxLog;
import java.util.List;

public interface LogAnalyzer {

    List<Metric> getLogMetrics(List<String> filenames, List<NginxLog> logs);
}
