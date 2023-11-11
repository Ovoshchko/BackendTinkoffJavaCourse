package edu.hw5.Task3;

public enum PatternVariant {
    SLASH_DATE("\\d{1,2}/\\d{1,2}/\\d{2,4}"),
    DASH_DATE("\\d{4}-\\d{1,2}-\\d{1,2}"),
    TOMORROW("tomorrow"),
    TODAY("today"),
    YESTERDAY("yesterday"),
    DAY_AGO("^\\d+ day(s?) ago$");

    private final String pattern;

    PatternVariant(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
