package edu.hw7.Task4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;

class MainLikeClassTest {

    @Test
    void runComparator() throws IOException {
        MainLikeClass mainClass = new MainLikeClass();
        mainClass.runComparator();
        assertTrue(Files.exists(mainClass.getReportPath()));
    }
}
