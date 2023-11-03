package edu.hw4.Errors;

public final class WeightIsToSmallError extends ValidationError {
    private final static String MESSAGE = "Weight Is To Small";

    private final static WeightIsToSmallError ERROR = new WeightIsToSmallError();

    private WeightIsToSmallError() {
        super(MESSAGE);
    }

    public static WeightIsToSmallError getError() {
        return ERROR;
    }

}
