package edu.hw1;

public final class Task7 {

    private static final int RADIX = 2;

    private Task7() {}

    public static int rotateLeft(int number, int shift) {

        if (isNotValid(number)) {
            return 0;
        }

        String numStr = getBinaryNumber(number);
        int shiftMin = shift % numStr.length();
        numStr = numStr.substring(shiftMin) + numStr.substring(0, shiftMin);
        return Integer.parseInt(numStr, RADIX);
    }

    public static int rotateRight(int number, int shift) {

        if (isNotValid(number)) {
            return 0;
        }

        String numStr = getBinaryNumber(number);
        int shiftMin = shift % numStr.length();
        numStr = numStr.substring(numStr.length() - shiftMin) + numStr.substring(0, numStr.length() - shiftMin);
        return Integer.parseInt(numStr, RADIX);
    }

    private static String getBinaryNumber(int number) {

        StringBuilder stringNumber = new StringBuilder();
        int copiedNumber = number;

        while (copiedNumber > 0) {
            stringNumber.append(copiedNumber % 2);
            copiedNumber /= 2;
        }

        return stringNumber.reverse().toString();
    }

    private static boolean isNotValid(int number) {
        return number <= 0;
    }

}
