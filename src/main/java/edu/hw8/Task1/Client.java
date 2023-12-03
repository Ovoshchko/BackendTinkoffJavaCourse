package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import static java.lang.System.exit;

@SuppressWarnings("RegexpSinglelineJava")
public class Client extends Thread {

    private final static String HOST = "localhost";
    private final static int PORT = 8080;
    private final static String EXIT_COMMAND = "exit";

    public Client() {}

    @Override
    public void run() {

        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
             Scanner sc = new Scanner(System.in)) {

            System.out.println(socket.getLocalSocketAddress());

            String input = null;

            while (!EXIT_COMMAND.equals(input) && !socket.isClosed()) {
                input = sc.nextLine();

                writer.write((input + System.lineSeparator()).getBytes());
                writer.flush();

                System.out.println("Server response: " + reader.readLine());

            }

        } catch (IOException exception) {
            System.out.println("Server was shot downed");
            exit(0);
        }
    }

}
