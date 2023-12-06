package edu.hw9.Task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class SizeExtensionFileSearchTest {

    private final static String STRING_PATH = "src/test/resources/edu/hw9/Task2/SizeExtensionSearchTest";
    private final static String EXTENSION = "jpg";
    private final static Path PATH = Path.of(STRING_PATH);
    private ForkJoinPool forkJoinPool;

    @BeforeEach
    void create() {
        forkJoinPool = new ForkJoinPool();
    }

    public static Stream<Arguments> provideDirectories() {
        return Stream.of(
            Arguments.of(10000, List.of(Path.of("src/test/resources/edu/hw9/Task2/SizeExtensionSearchTest/Package2/HeavyEnough.jpg"))),
            Arguments.of(100000000, List.of())
        );
    }

    @ParameterizedTest
    @DisplayName("--More than N size and with X extension file search test")
    @MethodSource("provideDirectories")
    void searchBigDirectories(int size, List<Path> answer) throws ExecutionException, InterruptedException {
        SizeExtensionFileSearch searchTask = new SizeExtensionFileSearch(PATH, EXTENSION, size);

        List<Path> result = forkJoinPool.invoke(searchTask);

        assertEquals(answer, result);
    }

}
