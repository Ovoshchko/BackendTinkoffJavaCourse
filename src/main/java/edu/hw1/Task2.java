package edu.hw1;

@SuppressWarnings("MagicNumber")
public final class Task2 {

    private Task2() {}

    public static int countDigits(int number) {
        int copiedNumber = Math.abs(number);
        int count = (copiedNumber == 0) ? 1 : 0;

        while (copiedNumber > 0) {
            count++;
            copiedNumber /= 10;
        }

        return count;
    }

}
