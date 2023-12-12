package edu.hw8.Task1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {

    private static Server server;

    @BeforeAll
    static void init() throws InterruptedException {
        server = new Server();
        server.start();
        Thread.sleep(5000);
    }

    @AfterAll
    static void close() {
        server.interrupt();
    }

    @Test
    void testSingleClientServer() throws InterruptedException {

        String input = "личности" + System.lineSeparator();
        Pattern expected = Pattern.compile("^(?!null$).+");

        try (Socket socket = new Socket("localhost", 8080)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            outputStream.write(input.getBytes());
            outputStream.flush();

            Thread.sleep(2000);

            String response = bufferedReader.readLine();

            assertTrue(expected.matcher(response).matches());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } finally {
            Thread.sleep(2000);
        };

    }

    @Test
    void testMultipleClientServer() throws InterruptedException, IOException {

        String[] inputs = new String[]{"личности" + System.lineSeparator(), "аупкеику" + System.lineSeparator()};
        Pattern expected = Pattern.compile("^(?!null$).+");

        try (Socket socket1 = new Socket("localhost", 8080);
                Socket socket2 = new Socket("localhost", 8080)) {

            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            DataOutputStream outputStream1 = new DataOutputStream(socket1.getOutputStream());
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
            DataOutputStream outputStream2 = new DataOutputStream(socket2.getOutputStream());

            outputStream1.write(inputs[0].getBytes());
            outputStream1.flush();
            outputStream2.write(inputs[1].getBytes());
            outputStream2.flush();

            Thread.sleep(2000);

            String response1 = bufferedReader1.readLine();
            String response2 = bufferedReader2.readLine();

            assertTrue(expected.matcher(response1).matches());
            assertTrue(expected.matcher(response2).matches());
            assertNotEquals(response1, response2);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } finally {
            Thread.sleep(2000);
        };

    }
}
