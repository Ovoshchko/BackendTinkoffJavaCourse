package edu.hw1;

@SuppressWarnings("ConstantName")
public final class Task1 {

    private final static int secondsInMinute = 60;

    private Task1() {}

    public static int minutesToSeconds(String time) {
        String[] dividedTime = time.split(":");
        String regex = "[0-9]+";

        if (dividedTime.length != 2) {
            return -1;
        }

        if (!((dividedTime[0].matches(regex))) && (dividedTime[1].matches(regex))) {
            return -1;
        }

        if (Integer.parseInt(dividedTime[1]) >= secondsInMinute) {
            return -1;
        }

        return Integer.parseInt(dividedTime[0]) * secondsInMinute + Integer.parseInt(dividedTime[1]);
    }

}
