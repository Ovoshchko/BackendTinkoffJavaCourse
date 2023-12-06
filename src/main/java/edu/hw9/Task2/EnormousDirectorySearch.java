package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class EnormousDirectorySearch extends RecursiveTask<List<Path>> {

    private final Path directory;
    private final int filesSize;

    public EnormousDirectorySearch(Path directory, int filesSize) {
        this.directory = directory;
        this.filesSize = filesSize;
    }

    @Override
    protected List<Path> compute() {
        List<Path> foundDirectories = new ArrayList<>();
        List<EnormousDirectorySearch> subdirectories = new ArrayList<>();
        int count = runThrowSubdirectories(subdirectories);

        if (count >= filesSize) {
            foundDirectories.add(directory);
        }

        for (var tasksResult: subdirectories) {
            foundDirectories.addAll(tasksResult.join());
        }

        return foundDirectories;
    }

    private int runThrowSubdirectories(List<EnormousDirectorySearch> subdirectories) {
        int count = 0;

        try {
            List<Path> paths = Files.list(directory).toList();

            for (Path path: paths) {
                if (Files.isDirectory(path)) {
                    EnormousDirectorySearch task = new EnormousDirectorySearch(path, filesSize);
                    task.fork();
                    subdirectories.add(task);
                } else if (Files.isRegularFile(path)) {
                    count++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return count;
    }
}
