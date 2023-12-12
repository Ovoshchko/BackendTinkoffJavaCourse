package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.lang.System.exit;

@SuppressWarnings("RegexpSinglelineJava")
public class Server extends Thread {

    private final static Logger LOGGER = LogManager.getLogger(Server.class);
    private final static int THREAD_COUNT = 3;
    private final static String EXIT_COMMAND = "exit";
    private final static int PORT = 8080;

    public Server() {}

    @Override
    public void run() {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        try (ServerSocket serverSocket = new ServerSocket()) {

            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(PORT));

            System.out.println("Started server:");
            System.out.println(serverSocket.getLocalSocketAddress().toString());

            Thread consoleInputThread = new Thread(() -> {
                try (Scanner sc = new Scanner(System.in)) {
                    while (true) {
                        String input = sc.next();
                        if (EXIT_COMMAND.equals(input)) {
                            executorService.shutdownNow();
                            serverSocket.close();
                            exit(0);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            consoleInputThread.start();

            while (!serverSocket.isClosed()) {

                Socket client = serverSocket.accept();

                System.out.println("New client connected: "
                    + client.getInetAddress()
                    .getHostAddress());

                ClientThread clientThread = new ClientThread(client);

                executorService.submit(clientThread);
            }
        } catch (SocketException exception) {
            LOGGER.info("Server was stopped");
        } catch (IOException e) {
            LOGGER.error(e);
        } finally {
            for (var thread: executorService.shutdownNow()) {
                ((ClientThread) thread).close();
            }
            executorService.close();
        }
    }

}
