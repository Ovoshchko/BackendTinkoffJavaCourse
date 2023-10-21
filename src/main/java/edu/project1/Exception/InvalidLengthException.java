package edu.project1.Exception;

public class InvalidLengthException extends Exception {

    public InvalidLengthException() {
        super("Length of the word is invalid. Please try to restart the game");
    }
}
