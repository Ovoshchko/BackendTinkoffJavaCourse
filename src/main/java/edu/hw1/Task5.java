package edu.hw1;

import java.util.logging.Logger;

@SuppressWarnings({"ConstantName", "MagicNumber"})
public final class Task5 {

    private static final int MINLENGTH = 2;
    private static final Logger log = Logger.getLogger(Task5.class.getName());

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
            numberString.insert(0, copiedNumber % 10 + (copiedNumber % 100) / 10);
            copiedNumber /= 100;
        }

        return Integer.parseInt(numberString.toString());
    }

    private static boolean isValid(Integer number) {
        return !((number == null) || (getNumberLength(number) < MINLENGTH));
    }

    private static boolean isPalindrome(Integer number) {
        StringBuilder numberString = new StringBuilder(number.toString());
        return numberString.toString().equals(numberString.reverse().toString());
    }

}
