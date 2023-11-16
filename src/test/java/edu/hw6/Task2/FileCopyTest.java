package edu.hw6.Task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class FileCopyTest {

    private final static String COPY_TEMPLATE = " - копия";
    private final static String COPY_NUMBER = " (%d)";
    private final static String PACKAGE_PATH = "src/test/resources/edu/hw6/Task2";
    private final FileCopy fileCopy = new FileCopy();
    private final String extension = ".txt";
    private final String fileName = "NewFile";
    private final String filePathString = PACKAGE_PATH + "/" + fileName + extension;
    private final Path path = Paths.get(filePathString);

    @BeforeAll
    static void init() {
        try {
            Files.createDirectories(Path.of(PACKAGE_PATH));
        } catch (IOException exception) {
            fail();
        }
    }
    @BeforeEach
    void create() {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            fail();
        }
    }

    @AfterEach
    void delete() {
        for (File file: new File(path.getParent().toString()).listFiles()) {
            file.delete();
        }
    }

    @Test
    @DisplayName("--File exists copies Test")
    void cloneFile() {

        int numberOfCopies = 7;

        String finalCopyPath = PACKAGE_PATH + "/" + fileName + COPY_TEMPLATE
            + String.format(COPY_NUMBER, numberOfCopies) + extension;

        Path result = path;

        for (int i = 0; i < numberOfCopies; i++) {
            result = fileCopy.cloneFile(path);
        }

        assertEquals(Paths.get(finalCopyPath), result);
    }

    @Test
    @DisplayName("--File do not exists")
    void fileDoNotExists() {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            fail();
        }
        assertEquals(path, fileCopy.cloneFile(path));
    }
}
