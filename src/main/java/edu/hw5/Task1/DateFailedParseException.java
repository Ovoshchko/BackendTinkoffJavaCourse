package edu.hw5.Task1;

import java.time.format.DateTimeParseException;

public class DateFailedParseException extends IllegalArgumentException {

    private final static String exceptionMessage = "Неверный формат дат, необходима корректировка";

    public DateFailedParseException() {
        super(exceptionMessage);
    }
}
