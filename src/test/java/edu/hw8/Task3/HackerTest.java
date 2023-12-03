package edu.hw8.Task3;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class HackerTest {

    private final Map<String, String> passwordDB = new HashMap<>(){{
        put("893b56e3cfe153fb770a120b83bac20c", "someone");
        put("c37bf859faf392800d739a41fe5af151", "me");
        put("d3eb9a9233e52948740d7eb8c3062d14", "k.p.maslov");
    }};

    private final Map<String, String> answers = new HashMap<>(){{
        put("someone", "bear");
        put("me", "98765");
        put("k.p.maslov", "99999");
    }};

    @Test
    void hackOneThread() {
        Hacker oneThread = new Hacker();

        assertAll(() -> answers.forEach((key, value) ->
                assertEquals(value, oneThread.hackOneThread(passwordDB).get(key))),
            () -> assertEquals(answers.size(), oneThread.hackOneThread(passwordDB).size())
        );
    }

    @Test
    void hackMultipleThreads() {
        Hacker multipleThreads = new Hacker();

        assertAll(() -> answers.forEach((key, value) ->
                assertEquals(value, multipleThreads.hackMultipleThreads(passwordDB).get(key))),
            () -> assertEquals(answers.size(), multipleThreads.hackMultipleThreads(passwordDB).size())
        );
    }
}
