package edu.project3.LogReader;

import edu.project3.LogParser.NginxLogParser;
import edu.project3.Models.DateLimits;
import edu.project3.Models.NginxLog;
import java.util.ArrayList;
import java.util.List;

public class NginxLogReader implements LogReader {

    @Override
    public List<NginxLog> readLogs(List<String> logs, DateLimits dateLimits) {
        List<NginxLog> nginxLogs = new ArrayList<>();
        NginxLogParser parser = new NginxLogParser();
        NginxLog currentLog;

        for (String log: logs) {
            currentLog = parser.parseLog(log);

            if (currentLog.dataTime().isAfter(dateLimits.from().atStartOfDay())
                && currentLog.dataTime().isBefore(dateLimits.to().atStartOfDay())) {
                nginxLogs.add(parser.parseLog(log));
            }
        }

        return nginxLogs;
    }
}
