package edu.hw5.Task4;

import java.util.regex.Pattern;

public class PasswordSymbolChecker {

    private final static String REGEX = ".*[~!@#$%^&*|].*";
    private final Pattern patternVariant = Pattern.compile(REGEX);

    public boolean hasSpecialSymbols(String password) {

        if (isValidString(password)) {
            return patternVariant.matcher(password).matches();
        }

        return false;
    }

    private boolean isValidString(String input) {
        return input != null;
    }
}
