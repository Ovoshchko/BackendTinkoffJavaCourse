package edu.project3.LogReceiver;

import edu.project3.LogReader.NginxLogReader;
import edu.project3.Models.DateLimits;
import edu.project3.Models.FileList;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LocalLogReceiver implements Receiver {

    public LocalLogReceiver() {

    }

    @Override
    public FileList receive(String link, DateLimits dateLimits) throws IOException {
        List<String> files = new ArrayList<>();
        List<String> logs = new ArrayList<>();
        NginxLogReader reader = new NginxLogReader();

        try {
            Path path = Paths.get(link);

            if (Files.isDirectory(path)) {
                try (DirectoryStream<Path> paths = Files.newDirectoryStream(path)) {
                    for (Path newFile: paths) {
                        files.add(newFile.getFileName().toString());
                        logs.addAll(readSingleFile(newFile));
                    }
                }
            } else {
                files.add(path.getFileName().toString());
                logs = readSingleFile(path);
            }
        } catch (IOException exception) {
            throw new IOException();
        }

        return new FileList(files, reader.readLogs(logs, dateLimits));
    }

    private List<String> readSingleFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
