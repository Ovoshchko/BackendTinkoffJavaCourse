package edu.project1;

import edu.project1.Exception.InvalidLengthException;
import edu.project1.Renderer.RendererImpl;
import edu.project1.Results.ResultInterface;
import edu.project1.Session.Session;
import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class HangmanGame {

    private final String exitCommand = "EXIT";
    private final Session session;
    private final RendererImpl renderer;
    private final Scanner sc;
    private ResultInterface result;


    HangmanGame(String wordToGuess) {
        session = new Session(wordToGuess);
        renderer = new RendererImpl();
        sc = new Scanner(System.in);
    }

    public void run() throws InvalidLengthException {

        if (session.wordIsNotValid()) {
            throw new InvalidLengthException();
        }

        String input;

        while (session.haveAttempts()) {

            System.out.println("Попробуйте угадать букву или введите EXIT для выхода");

            input  = sc.nextLine();

            if (input == null) {
                System.out.println("Прошу, введите букву. Виселица не любит пустоту.");
                continue;
            }

            if (exitCommand.equalsIgnoreCase(input)) {
                session.giveUp();
            } else {

                if (isLetter(input)) {
                    result = session.guess(input.toUpperCase().charAt(0));
                    renderer.render(result);
                } else {
                    System.out.println("Пожалуйста, введите букву или команду EXIT.\n");
                }

                if (session.isWordGuessed()) {
                    System.out.println("ПОБЕДА!!!");
                    return;
                }
            }
        }

        System.out.println("ПОРАЖЕНИЕ!!! Повезет в другой раз.");

    }

    private static boolean isLetter(String input) {
        return (input.length() == 1) && (Character.isLetter(input.charAt(0)));
    }

}
