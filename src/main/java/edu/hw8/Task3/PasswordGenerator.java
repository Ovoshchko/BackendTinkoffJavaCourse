package edu.hw8.Task3;

public class PasswordGenerator {

    private final String alphabet;
    private final int alphabetLength;
    private final int[] indexes;
    private long passwordCount;

    public PasswordGenerator(int length, long index, long passwordCount) {
        long localIndex = index;
        alphabet = AlphabetStorage.ALPHABET;
        alphabetLength = alphabet.length();
        indexes = new int[length];
        this.passwordCount = passwordCount;
        for (int i = length - 1; i >= 0; i--) {
            indexes[i] = (int) localIndex % alphabetLength;
            localIndex /= alphabetLength;
        }
    }

    public boolean hasNextPassword() {
        return (passwordCount > 0) && (indexes[0] < alphabetLength);
    }

    public String generatePassword() {
        StringBuilder password = new StringBuilder();

        for (int index: indexes) {
            password.append(alphabet.charAt(index));
        }

        getNextCombination();

        return password.toString();
    }

    private void getNextCombination() {
        passwordCount--;
        int prevIndex;
        int reminder = (++indexes[indexes.length - 1]) / alphabetLength;
        indexes[indexes.length - 1] %= alphabetLength;
        for (int i = indexes.length - 2; i >= 0; i--) {
            prevIndex = indexes[i] + reminder;
            indexes[i] = (indexes[i] + reminder) % alphabetLength;
            reminder = prevIndex / alphabetLength;
            if (reminder == 0) {
                break;
            }
        }
    }
}
