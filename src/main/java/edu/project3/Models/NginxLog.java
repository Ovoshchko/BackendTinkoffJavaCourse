package edu.project3.Models;

import java.time.LocalDateTime;

public record NginxLog(
    String ip,
    LocalDateTime dataTime,
    HttpMethod method,
    String resource,
    String protocol,
    int responseCode,
    int bytes
) {

    public static NginxLog getInvalidLog() {
        return new NginxLog(" ", LocalDateTime.now(), HttpMethod.NONE, " ", " ", 0, 0);
    }
}
