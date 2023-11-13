package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiskMap implements Map<String, String> {

    private final static String LINE_SEPARATOR = "\n";
    private final static String VALUE_SEPARATOR = "=";
    private final String file = "src/main/resources/edu/hw6/Task1/Disk.txt";
    private final Logger logger = LogManager.getLogger(DiskMap.class);
    private final Path pathToFile = Paths.get(file);

    public DiskMap() {
        createIfNotExist();
    }

    @Override public int size() {
        return readFromFile().size();
    }

    @Override public boolean isEmpty() {
        return readFromFile().isEmpty();
    }

    @Override public boolean containsKey(Object key) {
        return readFromFile().containsKey(key);
    }

    @Override public boolean containsValue(Object value) {
        return readFromFile().containsValue(value);
    }

    @Override public String get(Object key) {
        return readFromFile().get(key);
    }

    @Override public String put(String key, String value) {
        Map<String, String> files = readFromFile();
        String returnValue = files.put(key, value);
        saveToFile(files);
        return returnValue;
    }

    @Override public String remove(Object key) {
        Map<String, String> files = readFromFile();
        String deleted = files.remove(key);
        saveToFile(files);
        return deleted;
    }

    @Override public void putAll(Map<? extends String, ? extends String> files) {
        saveToFile(files);
    }

    @Override public void clear() {
        saveToFile(new HashMap<>());
    }

    @Override public Set<String> keySet() {
        return readFromFile().keySet();
    }

    @Override public Collection<String> values() {
        return readFromFile().values();
    }

    @Override public Set<Entry<String, String>> entrySet() {
        return readFromFile().entrySet();
    }

    private void createIfNotExist() {
        try {
            if (!Files.exists(pathToFile)) {
                Files.createFile(pathToFile);
            }
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
    }

    private Map<String, String> readFromFile() {
        Map<String, String> files = new HashMap<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(pathToFile)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] valueKey = line.trim().split(VALUE_SEPARATOR);
                if (valueKey.length == 2) {
                    files.put(valueKey[0], valueKey[1]);
                }
            }
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }

        return files;
    }

    private void saveToFile(Map<? extends String, ? extends String> files) {
        try (BufferedWriter writer = Files.newBufferedWriter(pathToFile)) {
            String nextFile;
            for (Entry<? extends String, ? extends String> curFile : files.entrySet()) {
                nextFile = curFile.getKey() + VALUE_SEPARATOR + curFile.getValue() + LINE_SEPARATOR;
                writer.write(nextFile);
            }
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
    }
}
