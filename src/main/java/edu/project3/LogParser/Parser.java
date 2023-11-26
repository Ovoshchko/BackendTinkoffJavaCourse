package edu.project3.LogParser;

import edu.project3.Models.NginxLog;

public interface Parser {

    NginxLog parseLog(String log);
}
