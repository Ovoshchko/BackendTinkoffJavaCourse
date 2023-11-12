package edu.hw5.Task6;

import java.util.regex.Pattern;

public class SubPatternChecker {

    private final static String EMPTY_FORMAT_REGEX = ".*%1$s.*";

    public boolean isSubstring(String string, String substring) {

        if ((isNotValid(string)) || (isNotValid(substring))) {
            return false;
        }

        Pattern subString = Pattern.compile(String.format((EMPTY_FORMAT_REGEX), (Pattern.quote(substring))));

        return subString.matcher(string).matches();
    }

    private boolean isNotValid(String input) {
        return input == null;
    }
}
