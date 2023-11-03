package edu.hw4.Errors;

public abstract class ValidationError extends Throwable {
    public ValidationError(String message) {
        super(message);
    }
}
