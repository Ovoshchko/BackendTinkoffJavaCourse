package edu.hw3.Task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FreqDictionary {

    public Map<String, Integer> freqDict(String[] list) {

        Map<String, Integer> freqDict = new HashMap<>();

        if (isValid(list)) {
            freqDict = Arrays.stream(list)
                .collect(Collectors.groupingBy(s -> s, Collectors.summingInt(s -> 1)));
        }

        return freqDict;
    }

    private boolean isValid(String[] list) {
        return (list != null) && (list.length > 0);
    }
}
