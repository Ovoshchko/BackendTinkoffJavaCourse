package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OutputStreamDecomposition {

    private final static String MESSAGE = "Programming is learned by writing programs. ― Brian Kernighan";
    private final Logger logger = LogManager.getLogger(OutputStreamDecomposition.class);

    public  void decompose(Path path) {
        try (OutputStream newOutputStream = Files.newOutputStream(path);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(newOutputStream, new CRC32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter =
                 new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {

            printWriter.println(MESSAGE);
        } catch (IOException exception) {
           logger.error(exception.getMessage());
        }
    }

}
