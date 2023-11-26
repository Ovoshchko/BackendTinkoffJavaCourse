package edu.project3.LogReader;

import edu.project3.Models.DateLimits;
import edu.project3.Models.NginxLog;
import java.util.List;

public interface LogReader {

    List<NginxLog> readLogs(List<String> logs, DateLimits dateLimits);
}
