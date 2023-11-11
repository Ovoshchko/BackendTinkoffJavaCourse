package edu.hw5.Task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AvgTimeCounter {


    private final static String REGEX = "^\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2} - \\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}$";
    private final static long SECONDS_IN_MINUTE = 60;
    private final static long MINUTE_IN_HOUR = 60;
    private final static String SPLIT_REGEX = " - ";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
    private final String answerTemplate = "%dч %dм";


    public String getAvgMinutesSpent(List<String> dates) {
        if (dates == null) {
            throw new NullPointerException();
        }

        long seconds = getAvgMinutes(dates);
        return convertSecondsToString(seconds);
    }

    private Long getAvgMinutes(List<String> dates) {
        List<Duration> durations = getListWithMinutesSpent(dates);
        return countAvgDuration(durations);
    }

    private String convertSecondsToString(Long seconds) {
        return answerTemplate.formatted(seconds / (MINUTE_IN_HOUR * SECONDS_IN_MINUTE),
                                            seconds / SECONDS_IN_MINUTE % MINUTE_IN_HOUR);
    }

    private Long countAvgDuration(List<Duration> durations) {
        Duration sumDuration = Duration.ZERO;

        for (Duration duration: durations) {
            sumDuration = sumDuration.plus(duration);
        }

        return sumDuration.dividedBy(durations.isEmpty() ? 1 : durations.size()).getSeconds();
    }

    private List<Duration> getListWithMinutesSpent(List<String> dates) {
        List<Duration> durations = new ArrayList<>();
        LocalDateTime start;
        LocalDateTime end;
        String[] inputDates;

        for (String date: dates) {
            if (isDateValid(date)) {
                try{
                    inputDates = date.split(SPLIT_REGEX);
                    start = LocalDateTime.parse(inputDates[0], dateTimeFormatter);
                    end = LocalDateTime.parse(inputDates[1], dateTimeFormatter);
                    durations.add(Duration.between(start, end));
                } catch(DateTimeParseException exception) {
                    throw new DateFailedParseException();
                }
            } else {
                throw new DateFailedParseException();
            }
        }

        return durations;
    }
    private boolean isDateValid(String input) {
        return (input != null) && (input.matches(REGEX));
    }

}
