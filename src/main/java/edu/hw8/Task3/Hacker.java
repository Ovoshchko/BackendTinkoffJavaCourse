package edu.hw8.Task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.Math.pow;

public class Hacker {

    public static final int MIN_LENGTH = 4;
    public static final int MAX_LENGTH = 6;
    public static final int MD_NUMBER = 0xff;

    public Hacker() {}

    public Map<String, String> hackOneThread(Map<String, String> passwordDB) {
        Map<String, String> hackedPasswords = new HashMap<>();
        int unguessedPasswords = passwordDB.size();

        for (int length = MIN_LENGTH; length < MAX_LENGTH; length++) {
            PasswordGenerator passwordGenerator =
                createNewPasswordGenerator(length, 0, (long) pow(AlphabetStorage.ALPHABET_LENGTH, length));
            String password;
            String hash;
            while (passwordGenerator.hasNextPassword()) {
                password = passwordGenerator.generatePassword();
                hash = createMd5Hash(password);
                if (passwordDB.containsKey(hash)) {
                    hackedPasswords.put(passwordDB.get(hash), password);
                    unguessedPasswords--;
                }
                if (unguessedPasswords <= 0) {
                    break;
                }
            }

            if (unguessedPasswords <= 0) {
                break;
            }
        }

        return hackedPasswords;
    }

    public Map<String, String> hackMultipleThreads(Map<String, String> passwordDB) {
        Map<String, String> hackedPasswords = new ConcurrentHashMap<>();
        AtomicInteger unguessedPasswords = new AtomicInteger(passwordDB.size());
        int threadAmount = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(threadAmount);

        for (int length = MIN_LENGTH; length < MAX_LENGTH; length++) {
            long combinations = (long) pow(AlphabetStorage.ALPHABET_LENGTH, length);
            long partionedCombinations = combinations / threadAmount;
            for (int serial = 0; serial < threadAmount; serial++) {
                threadPool.submit(
                    getHackThread(
                        createNewPasswordGenerator(length, partionedCombinations * serial, partionedCombinations),
                        passwordDB,
                        hackedPasswords,
                        unguessedPasswords
                    )
                );
            }
        }

        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        threadPool.close();

        return hackedPasswords;
    }

    private static Thread getHackThread(PasswordGenerator passwordGenerator, Map<String, String> passwordDB,
        Map<String, String> hackedPasswords, AtomicInteger unguessedPasswords) {
        return new Thread(() -> {
            String password;
            String hash;
            while (passwordGenerator.hasNextPassword()) {
                password = passwordGenerator.generatePassword();
                hash = createMd5Hash(password);
                if (passwordDB.containsKey(hash)) {
                    synchronized (unguessedPasswords) {
                        hackedPasswords.put(passwordDB.get(hash), password);
                        unguessedPasswords.decrementAndGet();
                    }
                }
                if (unguessedPasswords.get() <= 0) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });
    }

    private PasswordGenerator createNewPasswordGenerator(int length, long start, long amount) {
        return new PasswordGenerator(length, start, amount);
    }

    private static String createMd5Hash(String input) {
        try {
            var md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(MD_NUMBER & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
