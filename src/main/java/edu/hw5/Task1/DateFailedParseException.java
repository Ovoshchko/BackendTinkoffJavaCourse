package edu.hw5.Task1;

public class DateFailedParseException extends IllegalArgumentException {

    private final static String EXCEPTION_METHOD = "Неверный формат дат, необходима корректировка";

    public DateFailedParseException() {
        super(EXCEPTION_METHOD);
    }
}
