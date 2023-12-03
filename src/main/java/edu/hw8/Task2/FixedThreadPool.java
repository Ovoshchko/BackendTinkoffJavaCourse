package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class FixedThreadPool implements ThreadPool {

    private final int threadCount;
    private final BlockingQueue<Thread> threadQueue;
    private final Thread[] threadPool;

    private FixedThreadPool(int threadCount) {
        this.threadCount = threadCount;
        threadQueue = new LinkedBlockingQueue<>();
        threadPool = new Thread[threadCount];
    }

    public static FixedThreadPool create(int threadCount) {
        return new FixedThreadPool(threadCount);
    }

    @Override
    public void start() {
        for (int i = 0; i < (threadCount); i++) {
            threadPool[i] = new Thread(() -> {
                while (true) {
                    try {
                        Runnable task = threadQueue.take();
                        task.run();
                    } catch (InterruptedException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            });
            threadPool[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            threadQueue.put(new Thread(runnable));
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void close() throws Exception {
        for (Thread thread: threadPool) {
            thread.interrupt();
        }
    }
}
