package edu.hw1;

public final class Task3 {

    private Task3() {}

    public static boolean isNestable(int[] nestedArray, int[] investedArray) {

        if (isNotValid(nestedArray, investedArray)) {
            return false;
        }

        if (nestedArray.length == 0) {
            return true;
        }

        Borders nestedBorders = getBorders(nestedArray);
        Borders investedBorders = getBorders(investedArray);

        return (nestedBorders.min() > investedBorders.min()) && (nestedBorders.max() < investedBorders.max());
    }

    private static boolean isNotValid(int[] array1, int[] array2) {

        if (array1 == null || array2 == null) {
            return true;
        }

        if (array1.length != 0 && array2.length == 0) {
            return true;
        }

        return false;
    }

    private static Borders getBorders(int[] array) {

        int min = array[0];
        int max = array[0];

        for (int index = 1; index < array.length; index++) {
            if (array[index] > max) {
                max = array[index];
            } else if (array[index] < min) {
                min = array[index];
            }
        }

        return new Borders(min, max);
    }

    private static record Borders(int min, int max) {}

}
