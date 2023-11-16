package edu.hw6.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AbstractFilterTest {

    private static final String FILTERED_FILE_NAME = "Test.png";
    private final Path directoryPath = Paths.get("src/test/resources/edu/hw6/Task3");
    private final Logger logger = LogManager.getLogger(AbstractFilterTest.class);

    @Test
    @DisplayName("--Chain Filters Test")
    void chainFilter() {
        AbstractFilter regularFile = Files::isRegularFile;

        DirectoryStream.Filter<Path> filter = regularFile
            .and(AbstractFilter.IS_READABLE)
            .and(AbstractFilter.isLargerThan(1000))
            .and(AbstractFilter.magicNumbers((byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47))
            .and(AbstractFilter.globMatches("*.png"))
            .and(AbstractFilter.regexContains("Test"));

        List<Path> filteredFiles = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Paths.get("src/test/resources/edu/hw6/Task3"), filter)) {
            entries.forEach(filteredFiles::add);
        } catch (IOException exception) {
            logger.error(exception);
        }

        assertEquals(1, filteredFiles.size());
        assertEquals(FILTERED_FILE_NAME, filteredFiles.get(0).getFileName().toString());
    }

}
