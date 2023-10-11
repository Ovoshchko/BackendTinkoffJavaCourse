package edu.hw1;

@SuppressWarnings("LineLength")
public final class Task7 {

    private static final int RADIX = 2;

    private Task7() {}

    public static int rotateLeft(int number, int shift) {

        if (!isValid(number)) {
            return 0;
        }

        String numberString = getBinaryNumber(number);
        int shiftShorten = shift % numberString.length();
        numberString = numberString.substring(shiftShorten) + numberString.substring(0, shiftShorten);
        return Integer.parseInt(numberString, RADIX);
    }

    public static int rotateRight(int number, int shift) {

        if (!isValid(number)) {
            return 0;
        }

        String numberString = getBinaryNumber(number);
        int shiftShorten = shift % numberString.length();
        numberString = numberString.substring(numberString.length() - shiftShorten) + numberString.substring(0, numberString.length() - shiftShorten);
        return Integer.parseInt(numberString, RADIX);
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

    private static boolean isValid(int number) {
        return number > 0;
    }

}
