package edu.hw5.Task5;

import java.util.regex.Pattern;

public class SignChecker {

    private final static String REGEX = "^[А-Я]\\d{3}[А-Я]{2}\\d{3}$";
    private final Pattern pattern = Pattern.compile(REGEX);

    public boolean checkSign(String sign) {

        if (isNotValid(sign)) {
            return false;
        }

        return pattern.matcher(sign).matches();
    }

    private boolean isNotValid(String input) {
        return input == null;
    }
}
