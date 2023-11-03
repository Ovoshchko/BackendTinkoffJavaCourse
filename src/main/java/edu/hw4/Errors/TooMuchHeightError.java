package edu.hw4.Errors;

public final class TooMuchHeightError extends ValidationError {
    private final static String MESSAGE = "Height is to big";
    private final static TooMuchHeightError ERROR = new TooMuchHeightError();

    private TooMuchHeightError() {
        super(MESSAGE);
    }

    public static TooMuchHeightError getError() {
        return ERROR;
    }
}
