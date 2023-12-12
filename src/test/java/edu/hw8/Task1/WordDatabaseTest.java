package edu.hw8.Task1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class WordDatabaseTest {

    private final static String BASE_PATH = "src/main/resources/edu/hw8/Task1/word_database.json";
    private final static Map<String, List<String>> WORD_DATABASE;

    static {
        try {
            WORD_DATABASE =
                new Gson().fromJson(new String(Files.readAllBytes(Paths.get(BASE_PATH))),
                    new TypeToken<Map<String, List<String>>>() {}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @DisplayName("--Response phrases test")
    @ValueSource(strings = {"nhftdbzsrgFE", "default", "глупый"})
    void getBackPhrase(String input) {
        String response = WordDatabase.getBackPhrase(input);


        List<String> answers = WORD_DATABASE.containsKey(input) ?
            WORD_DATABASE.get(input) :
            WORD_DATABASE.get("default");

        assertTrue(answers.contains(response));
    }
}
