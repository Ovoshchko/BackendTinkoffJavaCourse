package edu.project3.LogParser;

import edu.project3.Models.NginxLog;

public interface AbstractParser {

    NginxLog parseLog(String log);
}
