package edu.project1;

import edu.project1.Exception.InvalidLengthException;
import edu.project1.Renderer.RendererImpl;
import edu.project1.Results.ResultInterface;
import edu.project1.Session.Session;
import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class HangmanGame {

    private final Session session;
    private final RendererImpl renderer;
    private ResultInterface result;
    private final Scanner sc = new Scanner(System.in);


    HangmanGame(String wordToGuess) {
        session = new Session(wordToGuess);
        renderer = new RendererImpl();
    }

    public void run() throws InvalidLengthException {

        if (session.wordIsNotValid()) {
            throw new InvalidLengthException();
        }

        String input;

        while (session.haveAttempts()) {

            System.out.println("Попробуйте угадать букву или введите EXIT для выхода");

            try {
                input  = sc.nextLine();

                if (input.equalsIgnoreCase("exit")) {
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

            } catch (NullPointerException e) {
                System.out.println("Пожалуйста не вводите пустоту. Виселица не любит такое.\n");
            }
        }

        System.out.println("ПОРАЖЕНИЕ!!! Повезет в другой раз.");

    }

    private static boolean isLetter(String input) {
        return (input.length() == 1) && (Character.isLetter(input.charAt(0)));
    }


}
