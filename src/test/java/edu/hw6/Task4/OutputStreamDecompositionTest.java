package edu.hw6.Task4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class OutputStreamDecompositionTest {
    private final static String FILE_CONTENT = "Programming is learned by writing programs. ― Brian Kernighan";
    private final static String PACKAGE_PATH_STRING = "src/test/resources/edu/hw6/Task4";
    private final String fileName = "Task4File.txt";
    private final Path filePath = Paths.get(PACKAGE_PATH_STRING + "/" + fileName);
    private final OutputStreamDecomposition outputStreamDecomposition = new OutputStreamDecomposition();

    @BeforeAll
    static void init() {
        try {
            Files.createDirectories(Path.of(PACKAGE_PATH_STRING));
        } catch (IOException exception) {
            fail();
        }
    }

    @BeforeEach
    void create() {
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException exception) {
            fail();
        }
    }

    @AfterEach
    void delete() {
        try {
            Files.delete(filePath);
        } catch (IOException exception) {
            fail();
        }
    }
    @Test
    @DisplayName("--Valid file test")
    void decompose() {
        try {
            outputStreamDecomposition.decompose(filePath);

            assertEquals(FILE_CONTENT, readSingleStringFromFile());
        } catch (IOException exception) {
            fail();
        }
    }

    private String readSingleStringFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()));
        String currentLine = reader.readLine();
        reader.close();
        return currentLine;
    }
}
