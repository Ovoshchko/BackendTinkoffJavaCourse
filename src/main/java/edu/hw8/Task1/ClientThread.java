package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable {

    private final Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream writer = new DataOutputStream(socket.getOutputStream())) {

            String line;

            while ((line = reader.readLine()) != null) {
                writer.write((WordDatabase.getBackPhrase(line) + System.lineSeparator()).getBytes());
                writer.flush();
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } finally {
            close();
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException ignore) {
            throw new RuntimeException(ignore);
        }
    }

}
