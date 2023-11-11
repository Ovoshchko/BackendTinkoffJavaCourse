package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;

public class DataParser {
    private final String space = " ";

    public Optional<LocalDate> parseDate(PatternVariant patternVariant, String date) {
        return switch(patternVariant) {
            case SLASH_DATE -> parseSlashData(date);
            case DASH_DATE -> parseDashData(date);
            case TOMORROW ->  parseNeighborDate(DayChange.TOMORROW);
            case TODAY -> parseNeighborDate(DayChange.TODAY);
            case YESTERDAY -> parseNeighborDate(DayChange.YESTERDAY);
            case DAY_AGO -> parseDayAgo(date);
        };
    }

    private Optional<LocalDate> parseDayAgo(String date) {
        String[] dateDivided = date.split(space);

        return Optional.of(LocalDate.now().minusDays(Integer.parseInt(dateDivided[0])));
    }

    private Optional<LocalDate> parseDashData(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

        return Optional.of(LocalDate.parse(date, formatter));
    }

    private Optional<LocalDate> parseNeighborDate(DayChange dayChange) {
        return Optional.of(LocalDate.now().plusDays(dayChange.dayChange));
    }

    private Optional<LocalDate> parseSlashData(String date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("d/MM/")
            .optionalStart()
            .appendPattern("yyyy")
            .optionalEnd()
            .optionalStart()
            .appendPattern("yy")
            .optionalEnd()
            .toFormatter();

        Optional<LocalDate> newDate = Optional.of(LocalDate.parse(date, formatter));

        return newDate;
    }

    private enum DayChange {
        TOMORROW(1),
        TODAY(0),
        YESTERDAY(-1);

        private final int dayChange;

        DayChange(int dayChange) {
            this.dayChange = dayChange;
        }
    }
}
