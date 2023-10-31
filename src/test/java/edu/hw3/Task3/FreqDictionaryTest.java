package edu.hw3.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FreqDictionaryTest {

    private final FreqDictionary freqDictionary = new FreqDictionary();

    @ParameterizedTest
    @DisplayName("--Valid String array input test")
    @MethodSource("getValidStringArrays")
    void validStringArrayTest(String[] input, Map<String, Integer> answer) {
        Map<String, Integer> prediction = freqDictionary.freqDict(input);

        for (String key: answer.keySet()) {
            assertEquals(answer.get(key), prediction.get(key));
        }
    }

    @ParameterizedTest
    @DisplayName("--Invalid String array input test")
    @NullAndEmptySource
    void invalidStringArrayTest(String[] input) {
        assertTrue(freqDictionary.freqDict(input).isEmpty());
    }

    private static Stream<Arguments> getValidStringArrays() {
        return Stream.of(
            Arguments.of(new String[]{"aaa", "aaaa", "fr", "aaa"},
                new HashMap<String, Integer>(){{
                    put("aaa", 2);
                    put("aaaa", 1);
                    put("fr", 1);
                }}),

            Arguments.of(new String[]{"aaa", "aaa", "aaa", "aaa"},
                new HashMap<String, Integer>(){{
                    put("aaa", 4);
                }}),

            Arguments.of(new String[]{"3", "4", "5", "6"},
                new HashMap<String, Integer>(){{
                    put("3", 1);
                    put("4", 1);
                    put("5", 1);
                    put("6", 1);
                }})
        );
    }
}
