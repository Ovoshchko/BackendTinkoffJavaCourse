package edu.hw8.Task1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WordDatabase {

    private final static String BASE_PATH = "src/main/resources/edu/hw8/Task1/word_database.json";
    private final static String DEFAULT_ANSWERS = "default";

    private final static Random RANDOM = new Random();
    private final static Lock READ_LOCK = new ReentrantReadWriteLock().readLock();
    private final static Map<String, List<String>> WORD_DATABASE;

    static {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(BASE_PATH));
            WORD_DATABASE =
                new Gson().fromJson(new String(jsonData), new TypeToken<Map<String, List<String>>>() {}.getType());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private WordDatabase() {}

    public static String getBackPhrase(String key) {
        try {
            READ_LOCK.lock();

            List<String> responses = WORD_DATABASE.containsKey(key)
                ? WORD_DATABASE.get(key)
                : WORD_DATABASE.get(DEFAULT_ANSWERS);

            return responses.get(RANDOM.nextInt(responses.size()));

        } finally {
            READ_LOCK.unlock();
        }
    }
}
