package edu.hw1;

import java.util.Arrays;

public final class Task3 {

    private Task3() {}

    private static boolean isValid(int[] array1, int[] array2) {

        if (array1 == null || array2 == null) {
            return false;
        }

        if (array1.length != 0 && array2.length == 0) {
            return false;
        }

        return true;
    }

    public static boolean isNestable(int[] nestedArray, int[] investedArray) {

        if (!isValid(nestedArray, investedArray)) {
            return false;
        }

        if (nestedArray.length == 0) {
            return true;
        }

        int maxNested = Arrays.stream(nestedArray).max().getAsInt();
        int minNested = Arrays.stream(nestedArray).min().getAsInt();
        int maxInvested = Arrays.stream(investedArray).max().getAsInt();
        int minInvested = Arrays.stream(investedArray).min().getAsInt();

        return (minNested > minInvested) && (maxNested < maxInvested);
    }

}
