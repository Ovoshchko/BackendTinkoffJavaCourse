package edu.hw2.Task4;

public final class StackCallInfo {

    private StackCallInfo() {}

    public static CallingInfo getLastStackInfo() {

        StackTraceElement lastStackTraceElement = Thread.currentThread().getStackTrace()[2];

        return new CallingInfo(lastStackTraceElement.getClassName(), lastStackTraceElement.getMethodName());
    }

}
