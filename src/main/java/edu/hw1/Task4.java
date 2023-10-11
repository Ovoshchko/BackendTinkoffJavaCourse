package edu.hw1;

@SuppressWarnings("ConstantName")
public final class Task4 {

    private static final int MINLENGTH = 2;

    private Task4() {}

    public static String fixString(String input) {

        if (!isValid(input)) {
            return "";
        }

        if (input.length() < MINLENGTH) {
            return input;
        }

        StringBuilder copiedInput = new StringBuilder(input);
        char letter;

        for (int index = 1; index < input.length(); index = index + 2) {
            letter = copiedInput.charAt(index);
            copiedInput.setCharAt(index, copiedInput.charAt(index - 1));
            copiedInput.setCharAt(index - 1, letter);
        }

        return copiedInput.toString();
    }

    private static boolean isValid(String input) {
        return !(input == null || input.isEmpty());
    }

}
