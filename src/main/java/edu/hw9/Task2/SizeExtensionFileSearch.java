package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SizeExtensionFileSearch extends RecursiveTask<List<Path>> {

    private final Path directory;
    private final String extension;
    private final int size;

    public SizeExtensionFileSearch(Path directory, String extension, int size) {
        this.directory = directory;
        this.extension = extension;
        this.size = size;
    }

    @Override
    protected List<Path> compute() {
        List<Path> foundFiles = new ArrayList<>();
        List<SizeExtensionFileSearch> subdirectories = new ArrayList<>();

        runThrowSubdirectories(foundFiles, subdirectories);

        for (var tasksResult: subdirectories) {
            foundFiles.addAll(tasksResult.join());
        }

        return foundFiles;
    }

    private void runThrowSubdirectories(List<Path> foundFiles, List<SizeExtensionFileSearch> subdirectories) {
        try {
            List<Path> paths = Files.list(directory).toList();

            for (Path path: paths) {
                if (Files.isDirectory(path)) {
                    SizeExtensionFileSearch task = new SizeExtensionFileSearch(path, extension, size);
                    task.fork();
                    subdirectories.add(task);
                } else if (Files.isRegularFile(path) && (Files.size(path) > size)
                    && (path.getFileName().toString().endsWith(extension))) {
                    foundFiles.add(path);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
