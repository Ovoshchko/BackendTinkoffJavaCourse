package edu.project2.Generation.Exception;

public class NotProperMazeSizeException extends IllegalArgumentException {

    public NotProperMazeSizeException() {
        super("Your parameters should be more than zero size");
    }
}
