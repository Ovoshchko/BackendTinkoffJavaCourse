package edu.hw1;

public final class Task5 {

    private static final int MIN_LENGTH = 2;
    private static final int UNITS = 10;
    private static final int  TENS = 100;

    private Task5() {}

    public static boolean isPalindromeDescendant(Integer number) {

        Integer copiedNumber = number;

        while (isValid(copiedNumber)) {

            if (isPalindrome(copiedNumber)) {
                return true;
            }

            copiedNumber = getDescendant(copiedNumber);
        }

        return false;
    }

    private static int getNumberLength(Integer number) {
        String numberString = number.toString();
        return numberString.length();
    }

    private static Integer getDescendant(Integer number) {

        Integer copiedNumber = number;
        StringBuilder numberString = new StringBuilder();

        while (copiedNumber > 0) {
            numberString.insert(0, copiedNumber % UNITS + (copiedNumber % TENS) / UNITS);
            copiedNumber /= TENS;
        }

        return Integer.parseInt(numberString.toString());
    }

    private static boolean isValid(Integer number) {
        return !((number == null) || (getNumberLength(number) < MIN_LENGTH));
    }

    private static boolean isPalindrome(Integer number) {
        StringBuilder numberString = new StringBuilder(number.toString());
        return numberString.toString().equals(numberString.reverse().toString());
    }

}
