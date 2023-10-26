package edu.hw3.Task1;

import java.util.HashMap;
import java.util.Map;

public class AtbashCipher {

    public final static Map<Character, Character> ATBASH_MAP = new HashMap<>();

    static {
        for (char c = 'a'; c <= 'z'; c++) {
            char reversedC = (char) ('z' - (c - 'a'));
            ATBASH_MAP.put(c, reversedC);
            ATBASH_MAP.put(Character.toUpperCase(c), Character.toUpperCase(reversedC));
        }
    }

    public String getEncrypted(String sentence) {

        if(isNotValid(sentence)) {
            return "";
        }

        StringBuilder sentenceEncrypted = new StringBuilder();

        for (char letter: sentence.toCharArray()) {

            if (Character.isLetter(letter)) {
                sentenceEncrypted.append(ATBASH_MAP.get(letter));
            } else {
                sentenceEncrypted.append(letter);
            }
        }

        return sentenceEncrypted.toString();
    }

    private boolean isNotValid(String sentence) {
        return (sentence == null) || sentence.isEmpty();
    }
}
