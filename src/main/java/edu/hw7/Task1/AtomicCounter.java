package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    private final AtomicInteger counter = new AtomicInteger(0);

    public AtomicCounter() {}

    public int increaseCounterTwoThreads(int count) {

        Thread firstThread = getIncrementThread(0, count / 2);
        Thread secondThread = getIncrementThread(count / 2, count);

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return counter.get();
    }

    private Thread getIncrementThread(int start, int end) {
        return new Thread(() -> {
           for (int i = start; i < end; i++) {
               increment();
           }
        });
    }

    private void increment() {
        counter.getAndIncrement();
    }
}
