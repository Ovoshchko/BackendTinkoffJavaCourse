package edu.hw1;

import java.util.regex.Pattern;

@SuppressWarnings("ConstantName")
public final class Task1 {

    private static final int SECONDSINMINUTE = 60;
    private static final Pattern PATTERN = Pattern.compile("[0-9]+");

    private Task1() {}

    public static int minutesToSeconds(String time) {

        String[] dividedTime = time.split(":");

        if (dividedTime.length != 2) {
            return -1;
        }

        if (!(PATTERN.matcher(dividedTime[0]).matches()) && (PATTERN.matcher(dividedTime[1]).matches())) {
            return -1;
        }

        if (Integer.parseInt(dividedTime[1]) >= SECONDSINMINUTE) {
            return -1;
        }

        return Integer.parseInt(dividedTime[0]) * SECONDSINMINUTE + Integer.parseInt(dividedTime[1]);
    }

}
