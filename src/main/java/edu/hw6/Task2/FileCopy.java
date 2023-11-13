package edu.hw6.Task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCopy {

    private final static String COPY_TEMPLATE = " - копия";
    private final static String COPY_NUMBER = " (%d)";
    private final Logger logger = LogManager.getLogger(FileCopy.class);

    public Path cloneFile(Path path) {

        if (!Files.exists(path)) {
            return path;
        }

        String fileName = path.getFileName().toString();
        String headName = fileName.substring(0, fileName.indexOf("."));
        String extension = fileName.substring(fileName.indexOf("."));
        Path parent = path.getParent();

        int count = 1;
        Path newFileName = parent.resolve(headName + COPY_TEMPLATE + extension);

        while (Files.exists(newFileName)) {
            newFileName = parent.resolve(
                headName + COPY_TEMPLATE + String.format(COPY_NUMBER, ++count) + extension
            );
        }

        try {
            Files.copy(path, newFileName);
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }

        return newFileName;
    }

}
