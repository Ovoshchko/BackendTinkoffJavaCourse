package edu.hw5.Task7;

import java.util.Objects;
import java.util.regex.Pattern;

public class ZeroOneDictionaryChecker {

    private final static String BETWEEN_ONE_THREE_REGEX = "^[01]{1,3}$";
    private final static String SAME_END_REGEX = "^([01])[01]*\\1$|^[01]$";
    private final static String THREE_MORE_THIRD_ZERO_REGEX = "^[01]{2}0[01]*$";
    private final Pattern betweenOneThreeRegex = Pattern.compile(BETWEEN_ONE_THREE_REGEX);
    private final Pattern sameEndRegex = Pattern.compile(SAME_END_REGEX);
    private final Pattern threeMoreThirdZeroPattern = Pattern.compile(THREE_MORE_THIRD_ZERO_REGEX);

    public boolean matchesBetweenOneThree(String string) {
        Objects.requireNonNull(string);

        return betweenOneThreeRegex.matcher(string).matches();
    }

    public boolean matchesSameEndRegex(String string) {
        Objects.requireNonNull(string);

        return sameEndRegex.matcher(string).matches();
    }

    public boolean matchesThreeMoreThirdZero(String string) {
        Objects.requireNonNull(string);

        return threeMoreThirdZeroPattern.matcher(string).matches();
    }
}
