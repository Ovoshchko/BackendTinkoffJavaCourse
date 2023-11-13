package edu.hw5.Task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Friday13Searcher {

    private final static int THIRTEENS_DAY = 13;

    public LocalDate searchNextFriday13(LocalDate currentDate) {
        TemporalAdjuster nextFriday13thAdjuster = TemporalAdjusters.ofDateAdjuster(date -> {
            LocalDate nextFriday13 = (date.getDayOfMonth() >= THIRTEENS_DAY)
                ? currentDate.plusMonths(1).withDayOfMonth(THIRTEENS_DAY)
                : currentDate.withDayOfMonth(THIRTEENS_DAY);

            while (nextFriday13.getDayOfWeek() != DayOfWeek.FRIDAY) {
                nextFriday13 = nextFriday13.plusMonths(1);
            }

            return nextFriday13;
        });

        return currentDate.with(nextFriday13thAdjuster);
    }

    public List<LocalDate> searchBlackFridays(LocalDate date) {
        int year = date.getYear();
        List<LocalDate> fridays = new ArrayList<>();
        YearMonth start = YearMonth.of(year, 1);
        YearMonth end = YearMonth.of(year + 1, 1);

        Stream.iterate(start, month -> month.isBefore(end), month -> month.plusMonths(1))
            .map(m -> m.atDay(THIRTEENS_DAY))
            .filter(Friday13Searcher::isFriday)
            .forEach(fridays::add);

        return fridays;
    }

    private static boolean isFriday(LocalDate date) {
        return DayOfWeek.FRIDAY.equals(date.getDayOfWeek());
    }

}
