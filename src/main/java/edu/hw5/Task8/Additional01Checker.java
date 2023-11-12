package edu.hw5.Task8;

import java.util.Objects;
import java.util.regex.Pattern;

public class Additional01Checker {

    private final static String IS_ODD_REGEX = "^[01]([01]{2})*$";
    private final static String NO_CONNECTED_ONE_REGEX = "^(?!.*11)[01]+$";
    private final static String ODD_ONE_REGEX = "^(1[01]?)*$";
    private final static String ODD_ZERO_EVEN_ONE_REGEX = "^1[01]([01]{2})*$|^0([01]{2})*$";
    private final static String ZERO_MULTIPLY_THREE_REGEX = "^(01*01*01*)*$";
    private final Pattern isOddPattern = Pattern.compile(IS_ODD_REGEX);
    private final Pattern noConnectedOnePattern = Pattern.compile(NO_CONNECTED_ONE_REGEX);
    private final Pattern oddOnePattern = Pattern.compile(ODD_ONE_REGEX);
    private final Pattern oddZeroEvenOnePattern = Pattern.compile(ODD_ZERO_EVEN_ONE_REGEX);
    private final Pattern zeroMultiplyThreePattern = Pattern.compile(ZERO_MULTIPLY_THREE_REGEX);

    public boolean isOdd(String input) {
        Objects.requireNonNull(input);

        return isOddPattern.matcher(input).matches();
    }

    public boolean noConnectedOnes(String input) {
        Objects.requireNonNull(input);

        return noConnectedOnePattern.matcher(input).matches();
    }

    public boolean oddOne(String input) {
        Objects.requireNonNull(input);

        return oddOnePattern.matcher(input).matches();
    }

    public boolean oddZeroEvenOne(String input) {
        Objects.requireNonNull(input);

        return oddZeroEvenOnePattern.matcher(input).matches();
    }

    public boolean zeroMultiplyThree(String input) {
        Objects.requireNonNull(input);

        return zeroMultiplyThreePattern.matcher(input).matches();
    }
}
