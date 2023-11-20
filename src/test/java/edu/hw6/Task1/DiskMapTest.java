package edu.hw6.Task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class DiskMapTest {

    private final static String TEST_FILE = "src/main/resources/edu/hw6/Task1/Disk.txt";
    private DiskMap diskMap;

    @BeforeEach
    void setUp() {
        try {
            Files.deleteIfExists(Paths.get(TEST_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        diskMap = new DiskMap();
    }

    @Test
    @DisplayName("--Size method Test")
    void testSize() {
        assertEquals(0, diskMap.size());
        diskMap.put("file1.txt", "word");
        assertEquals(1, diskMap.size());
    }

    @Test
    @DisplayName("--IsEmpty method Test")
    void testIsEmpty() {
        assertTrue(diskMap.isEmpty());
        diskMap.put("file1.txt", "word");
        assertFalse(diskMap.isEmpty());
    }

    @Test
    @DisplayName("--ContainsKey method Test")
    void testContainsKey() {
        assertFalse(diskMap.containsKey("file1.txt"));
        diskMap.put("file1.txt", "word");
        assertTrue(diskMap.containsKey("file1.txt"));
    }

    @Test
    @DisplayName("--ContainsValue method Test")
    void testContainsValue() {
        assertFalse(diskMap.containsValue("word"));
        diskMap.put("file1.txt", "word");
        assertTrue(diskMap.containsValue("word"));
    }

    @Test
    @DisplayName("--Get method Test")
    void testGet() {
        assertNull(diskMap.get("file1.txt"));
        diskMap.put("file1.txt", "word");
        assertEquals("word", diskMap.get("file1.txt"));
    }

    @Test
    @DisplayName("--Put method Test")
    void testPut() {
        assertNull(diskMap.put("file1.txt", "word"));
        assertEquals("word", diskMap.get("file1.txt"));
    }

    @Test
    @DisplayName("--Remove method Test")
    void testRemove() {
        diskMap.put("file1.txt", "word");
        assertEquals("word", diskMap.remove("file1.txt"));
        assertNull(diskMap.get("file1.txt"));
    }

    @Test
    @DisplayName("--PutAll method Test")
    void testPutAll() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("file1.txt", "word1");
        testMap.put("file2.txt", "word2");

        diskMap.putAll(testMap);

        assertEquals("word1", diskMap.get("file1.txt"));
        assertEquals("word2", diskMap.get("file2.txt"));
    }

    @Test
    @DisplayName("--Clear method Test")
    void testClear() {
        diskMap.put("file1.txt", "word");
        diskMap.clear();
        assertTrue(diskMap.isEmpty());
    }

    @Test
    @DisplayName("--KeySet method Test")
    void testKeySet() {
        diskMap.put("file1.txt", "word1");
        diskMap.put("file2.txt", "word2");
        assertEquals(Set.of("file1.txt", "file2.txt"), diskMap.keySet());
    }

    @Test
    @DisplayName("--Values method Test")
    void testValues() {
        diskMap.put("file1.txt", "word1");
        diskMap.put("file2.txt", "word2");
        assertEquals(Set.of("word1", "word2"), new HashSet<>(diskMap.values()));
    }

    @Test
    @DisplayName("--EntrySet method Test")
    void testEntrySet() {
        diskMap.put("file1.txt", "word1");
        diskMap.put("file2.txt", "word2");

        Set<Map.Entry<String, String>> entrySet = diskMap.entrySet();
        assertEquals(2, entrySet.size());
        assertTrue(entrySet.contains(Map.entry("file1.txt", "word1")));
        assertTrue(entrySet.contains(Map.entry("file2.txt", "word2")));
    }

    @Test
    @DisplayName("--File creation Test")
    void testFileCreation() {
        assertTrue(Files.exists(Paths.get(TEST_FILE)));
    }
}
