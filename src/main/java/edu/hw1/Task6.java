package edu.hw1;

import java.util.Arrays;
import java.util.regex.Pattern;

public final class Task6 {

    private static final int KAPREKAR_CONST = 6174;
    private static final int RADIX = 10;
    private static final int LOWER_NUMBER_LIMIT = 1000;
    private static final Pattern PATTERN = Pattern.compile("^(?!(\\d)\\1{3}$)\\d{4}$");

    private Task6() {}

    public static int countK(int number) {

        if (isNotValid(number)) {
            return -1;
        }

        return countKConst(number);
    }

    private static int countKConst(int number) {

        if (number == KAPREKAR_CONST) {
            return 0;
        }

        return 1 + countKConst(getAscendingNumber(number) - getDescendingNumber(number));
    }

    private static boolean isNotValid(int number) {
        String numberString = Integer.toString(number);
        return !PATTERN.matcher(numberString).matches() || (number <= LOWER_NUMBER_LIMIT);
    }

    private static int getAscendingNumber(int number) {
        return sortDigits(number, false);
    }

    private static int getDescendingNumber(int number) {
        return sortDigits(number, true);
    }

    private static int sortDigits(int number, boolean asc) {

        char[] digits = String.valueOf(number).replace("-", "").toCharArray();
        Arrays.sort(digits);

        if (!asc) {
            digits = reverseCharArray(digits);
        }

        String numberString = new String(digits);

        return Integer.parseInt(numberString, RADIX);
    }

    private static char[] reverseCharArray(char[] array) {
        char current;

        for (int i = 0; i < array.length / 2;  i++) {
            current = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = current;
        }

        return array;
    }

}
