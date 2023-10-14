package edu.hw1;

import java.util.regex.Pattern;

public final class Task1 {

    private static final int SECONDS_IN_MINUTE = 60;
    private static final int TIME_ARRAY_LENGTH = 2;
    private static final Pattern PATTERN = Pattern.compile("^\\d+:(?:[0-5]\\d|[0-9])$");

    private Task1() {}

    public static int minutesToSeconds(String time) {

        if (isValid(time)) {

            String[] dividedTime = time.split(":");

            return Integer.parseInt(dividedTime[0]) * SECONDS_IN_MINUTE + Integer.parseInt(dividedTime[1]);
        }

        return -1;
    }

    private static boolean isValid(String time) {

        if (time == null) {
            return false;
        }

        return PATTERN.matcher(time).matches();

    }

}
