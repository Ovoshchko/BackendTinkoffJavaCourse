package edu.project3.LogParser;

import edu.project3.Models.HttpMethod;
import edu.project3.Models.NginxLog;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MagicNumber")
public class NginxLogParser implements AbstractParser {

    private final static String NGINX_PATTERN_STRING =
        "(\\S+) - - \\[([^]]+)] \"(\\S+) (\\S+) (\\S+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
    private final static String DATE_FORMAT = "dd/MMM/yyyy:HH:mm:ss Z";
    private final Pattern nginxPattern = Pattern.compile(NGINX_PATTERN_STRING);

    @Override
    public NginxLog parseLog(String log) {
        Matcher logMatcher = nginxPattern.matcher(log);

        if (logMatcher.find()) {
            return new NginxLog(
                logMatcher.group(1),
                LocalDateTime.parse(logMatcher.group(2), DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH)),
                HttpMethod.valueOf(logMatcher.group(3)),
                logMatcher.group(4),
                logMatcher.group(5),
                Integer.parseInt(logMatcher.group(6)),
                Integer.parseInt(logMatcher.group(7))
            );
        }

        return NginxLog.getInvalidLog();
    }
}
