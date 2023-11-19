package edu.project3.LogReceiver;

import edu.project3.LogReader.NginxLogReader;
import edu.project3.Models.NginxLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LocalLogReceiver implements AbstractReceiver {
    @Override
    public List<NginxLog> receive(String link) throws IOException {
        List<String> files = new ArrayList<>();
        List<String> logs = new ArrayList<>();
        NginxLogReader reader = new NginxLogReader();

        try {
            Path path = Paths.get(link);

            if (Files.isDirectory(path)) {
                try (DirectoryStream<Path> paths = Files.newDirectoryStream(path)) {
                    for (Path newFile: paths) {
                        files.add(path.getFileName().toString());
                        logs.addAll(readSingleFile(path));
                    }
                }
            } else {
                logs = readSingleFile(path);
            }
        } catch (IOException ignore) {
            throw new IOException();
        }

        return reader.readLogs(logs);
    }

    private List<String> readSingleFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
