package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    @Override
    boolean accept(Path entry) throws IOException;

    default AbstractFilter and(AbstractFilter filter) {
        return (entry) -> this.accept(entry) && filter.accept(entry);
    }

    AbstractFilter IS_READABLE = Files::isReadable;
    AbstractFilter IS_WRITABLE = Files::isWritable;
    AbstractFilter IS_EXECUTABLE = Files::isExecutable;

    static AbstractFilter isLargerThan(int size) {
        return (entry) -> {
            try {
                return Files.size(entry) > size;
            } catch (IOException exception) {
                return false;
            }
        };
    }

    static AbstractFilter globMatches(String regex) {
        return (entry) -> {
            String extension = regex.substring(regex.indexOf(".") + 1);
            return Pattern.compile(".*[.]" + extension).matcher(entry.getFileName().toString()).matches();
        };
    }

    static AbstractFilter regexContains(String regex) {
        return (entry) -> Pattern.compile(regex + ".*").matcher(entry.getFileName().toString()).matches();
    }

    static AbstractFilter magicNumbers(byte... magicNumbers) {
        return (entry) -> {
            byte[] fileContent = Files.readAllBytes(entry);

            if (fileContent.length < magicNumbers.length) {
                return false;
            }

            for (int i = 0; i < magicNumbers.length; i++) {
                if (fileContent[i] != magicNumbers[i]) {
                    return false;
                }
            }

            return true;
        };
    }

}
