package edu.hw8.Task3;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("RegexpSinglelineJava")
public class TimeStatistic {

    public static void compareThreads() {
        Map<String, String> passwordDB = new HashMap<>() {{
            put("893b56e3cfe153fb770a120b83bac20c", "someone");
            put("c37bf859faf392800d739a41fe5af151", "me");
            put("2b62d10f36589e0bf7cc6508afcac1df", "v.v.belov");
        }};

        Hacker hackerOneThread = new Hacker();
        Hacker hackerMultipleThreads = new Hacker();

        new Thread(() -> {
            long start = System.currentTimeMillis();
            System.out.println(hackerOneThread.hackOneThread(passwordDB));
            System.out.println("One thread: " + (System.currentTimeMillis() - start));
        }).start();

        new Thread(() -> {
            long start = System.currentTimeMillis();
            System.out.println(hackerMultipleThreads.hackMultipleThreads(passwordDB));
            System.out.println("Multiple threads: " + (System.currentTimeMillis() - start));
        }).start();
    }

    private TimeStatistic() {}
}
