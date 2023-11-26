package edu.project3.LogParser;

import edu.project3.Models.HttpMethod;
import edu.project3.Models.NginxLog;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MagicNumber")
public class NginxLogParser implements Parser {

    private final static String NGINX_PATTERN_STRING =
        "(\\S+) - - \\[([^]]+)] \"(\\S+) (\\S+) (\\S+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
    private final static String DATE_FORMAT = "dd/MMM/yyyy:HH:mm:ss Z";
    public static final int IP_POSITION = 1;
    public static final int DATE_POSITION = 2;
    public static final int HTTP_METHOD_POSITION = 3;
    public static final int RESOURCE_POSITION = 4;
    public static final int PROTOCOL_POSITION = 5;
    public static final int RESPONSE_CODE_POSITION = 6;
    public static final int BYTES_POSITION = 7;
    private final Pattern nginxPattern = Pattern.compile(NGINX_PATTERN_STRING);

    @Override
    public NginxLog parseLog(String log) {
        Matcher logMatcher = nginxPattern.matcher(log);

        if (logMatcher.find()) {
            return new NginxLog(
                logMatcher.group(IP_POSITION),
                LocalDateTime.parse(logMatcher.group(DATE_POSITION),
                    DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH)),
                HttpMethod.valueOf(logMatcher.group(HTTP_METHOD_POSITION)),
                logMatcher.group(RESOURCE_POSITION),
                logMatcher.group(PROTOCOL_POSITION),
                Integer.parseInt(logMatcher.group(RESPONSE_CODE_POSITION)),
                Integer.parseInt(logMatcher.group(BYTES_POSITION))
            );
        }

        return NginxLog.getInvalidLog();
    }
}
