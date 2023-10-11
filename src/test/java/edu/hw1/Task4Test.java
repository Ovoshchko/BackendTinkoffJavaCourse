package edu.hw1;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for Task 4")
public class Task4Test {

    @Test
    @DisplayName("--Valid even input")
    void validEvenInputTest() {

        Map<String, String> inputStrings = new HashMap<>();
        inputStrings.put("oN", "No");
        inputStrings.put("hTsii  s aimex dpus rtni.g", "This is a mixed up string.");
        inputStrings.put("21436587", "12345678");

        for (var entry: inputStrings.entrySet()) {
            assertEquals(Task4.fixString(entry.getKey()), entry.getValue());
        }

    }

    @Test
    @DisplayName("--Valid odd input")
    void validOddInputTest() {

        Map<String, String> inputStrings = new HashMap<>();
        inputStrings.put("eYs", "Yes");
        inputStrings.put("yMs neetcne", "My sentence");
        inputStrings.put("12345", "21435");

        for (var entry: inputStrings.entrySet()) {
            assertEquals(Task4.fixString(entry.getKey()), entry.getValue());
        }

    }

    @Test
    @DisplayName("Valid empty input")
    void validEmptyInputTest() {

        String inputString = "";

        assertEquals(Task4.fixString(inputString), inputString);
    }

    @Test
    @DisplayName("Valid input with length = 1")
    void validSingleInputTest() {

        String inputString = "1";

        assertEquals(Task4.fixString(inputString), inputString);
    }

    @Test
    @DisplayName("Invalid input")
    void invalidInputTest() {

        String inputString = null;

        assertEquals(Task4.fixString(inputString), "");
    }
}
