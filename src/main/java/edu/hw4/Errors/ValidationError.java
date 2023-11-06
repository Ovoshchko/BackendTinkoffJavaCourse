package edu.hw4.Errors;

public record ValidationError<T>(String fieldName, T value) {
}
