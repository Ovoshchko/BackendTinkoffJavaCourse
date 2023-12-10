package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FixedThreadPool implements ThreadPool {

    private final Logger logger = LogManager.getLogger(FixedThreadPool.class);
    private final int threadCount;
    private final BlockingQueue<Thread> threadQueue;
    private final Thread[] threadPool;
    private boolean isEditable;

    private FixedThreadPool(int threadCount) {
        this.threadCount = threadCount;
        threadQueue = new LinkedBlockingQueue<>();
        threadPool = new Thread[threadCount];
        isEditable = true;
    }

    public static FixedThreadPool create(int threadCount) {
        return new FixedThreadPool(threadCount);
    }

    @Override
    public void start() {
        for (int i = 0; i < (threadCount); i++) {
            threadPool[i] = new Thread(() -> {
                while (!threadQueue.isEmpty() || isEditable) {
                    runTasks();
                }
            });
            threadPool[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isEditable) {
            try {
                threadQueue.put(new Thread(runnable));
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(exception);
            }
        } else {
            logger.info("Sorry, service was shutdown");
        }
    }

    @Override
    public void close() throws Exception {
        isEditable = false;

        for (Thread thread: threadPool) {
            thread.join();
        }
    }

    private void runTasks() {
        try {
            Runnable task = threadQueue.take();
            task.run();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }
}
