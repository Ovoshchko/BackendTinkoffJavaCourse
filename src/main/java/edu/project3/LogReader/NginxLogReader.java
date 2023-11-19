package edu.project3.LogReader;

import edu.project3.LogParser.NginxLogParser;
import edu.project3.Models.NginxLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NginxLogReader implements AbstractLogReader {

    @Override
    public List<NginxLog> readLogs(List<String> logs) {
        List<NginxLog> nginxLogs = new ArrayList<>();
        NginxLogParser parser = new NginxLogParser();

        for (String log: logs) {
            nginxLogs.add(parser.parseLog(log));
        }

        return nginxLogs;
    }
}
