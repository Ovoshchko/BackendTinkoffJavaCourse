package edu.project1.Dictionary;

import java.util.Random;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public class WordBank implements Dictionary {

    private final static int MAX_WORD_LENGTH = 30;
    private final static Random RANDOM = new Random();
    private final static Pattern PATTERN = Pattern.compile("^[A-Z]*$");
    private final static String[] DICTIONARY = {
        "WORD", "SWORD", "STICK", "BALLOON", "MASTER",
        "DUNGEON", "FINAL", "LION", "PROGRAMMING"
    };

    public WordBank() {}

    @NotNull
    public String getRandomWord() {
        return DICTIONARY[RANDOM.nextInt(DICTIONARY.length)];
    }

    public boolean notValidLength(@NotNull String word) {
        return (word.length() > MAX_WORD_LENGTH) || (word.isEmpty()) || (!PATTERN.matcher(word).matches());
    }
}
