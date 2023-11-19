package edu.project3.LogReader;

import edu.project3.Models.NginxLog;
import java.net.URL;
import java.util.List;

public interface AbstractLogReader {

    List<NginxLog> readLogs(List<String> logs);
}
