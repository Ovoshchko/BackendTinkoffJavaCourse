package edu.project1.Dictionary;

import org.jetbrains.annotations.NotNull;

public interface Dictionary {

    @NotNull static String getRandomWord() {
        return "";
    }

    static boolean notValidLength(@NotNull String word) {
        return false;
    }
}
