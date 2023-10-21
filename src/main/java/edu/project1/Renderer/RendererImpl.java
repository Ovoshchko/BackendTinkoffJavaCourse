package edu.project1.Renderer;

import edu.project1.Results.ResultInterface;
import java.util.Set;

@SuppressWarnings("RegexpSinglelineJava")
public class RendererImpl implements Renderer {

    public RendererImpl() {}

    private final static String[] STATE_PICTURES = new String[]{
            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
    };

    public void render(ResultInterface newAttempt) {
        System.out.println();
        System.out.println(newAttempt.message());
        System.out.println();
        System.out.println(STATE_PICTURES[newAttempt.failedAttempts() % STATE_PICTURES.length]);
        System.out.println();
        System.out.println("The word is: " + getEncryptedWord(newAttempt.wordToGuess(), newAttempt.userAnswers()));
        System.out.println();
    }

    public String getEncryptedWord(String initialWord, Set<Character> userLetters) {

        StringBuilder encryptedString = new StringBuilder();

        for (char current: initialWord.toCharArray()) {
            if (userLetters.contains(current)) {
                encryptedString.append(current);
            } else {
                encryptedString.append("*");
            }
        }

        return encryptedString.toString();
    }
}
