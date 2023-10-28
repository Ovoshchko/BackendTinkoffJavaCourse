package edu.hw3.Task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class NullComparatorTest {

    private final NullComparator nullComparator = new NullComparator();

    @Test
    @DisplayName("--Null first test")
    void nullFirstTest() {
        Map<String, String> treeMap = new TreeMap<>(nullComparator.getComparator());
        treeMap.put(null, "1");
        treeMap.put("1", "2");
        treeMap.put("erfd", "dfg");
        assertEquals("1", treeMap.get(null));
    }

    @Test
    @DisplayName("--Null after test")
    void nullAfterTest() {
        Map<String, String> treeMap = new TreeMap<>(nullComparator.getComparator());
        treeMap.put("1", "1");
        treeMap.put("3", "2");
        treeMap.put(null, "dfg");
        assertEquals("dfg", treeMap.get(null));
    }
}

