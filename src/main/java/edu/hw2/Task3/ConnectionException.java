package edu.hw2.Task3;

public class ConnectionException extends RuntimeException {

    ConnectionException() {
        super("Failed to execute your command");
    }
}
