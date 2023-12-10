package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("RegexpSinglelineJava")
public class Client extends Thread {

    public static final int WAIT_TIME = 1000;
    private final Logger logger = LogManager.getLogger(Client.class);
    private final static String HOST = "localhost";
    private final static int PORT = 8080;
    private final static String EXIT_COMMAND = "exit";

    public Client() {}

    @Override
    public void run() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            try (Socket socket = new Socket(HOST, PORT);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 DataOutputStream writer = new DataOutputStream(socket.getOutputStream())) {

                System.out.println(socket.getLocalSocketAddress());

                String input = null;

                while (!socket.isClosed() && !EXIT_COMMAND.equals(input)) {
                    if (!sc.hasNextLine()) {
                        continue;
                    }

                    input = sc.nextLine();

                    writer.write((input + System.lineSeparator()).getBytes());
                    writer.flush();

                    System.out.println("Server response: " + reader.readLine());

                }


            } catch (IOException exception) {
                logger.error("Server is not available.");

                try {
                    Thread.sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
