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

class EnormousDirectorySearchTest {

    private final static String STRING_PATH = "src/test/resources";
    private final static Path PATH = Path.of(STRING_PATH);
    private ForkJoinPool forkJoinPool;

    @BeforeEach
    void create() {
        forkJoinPool = new ForkJoinPool();
    }

    public static Stream<Arguments> provideDirectories() {
        return Stream.of(
            Arguments.of(150, List.of(Path.of("src/test/resources/edu/hw9/Task2/EnormousDirectorySearchTest"))),
            Arguments.of(100000, List.of())
        );
    }

    @ParameterizedTest
    @DisplayName("--More than N files directory search test")
    @MethodSource("provideDirectories")
    void searchBigDirectories(int fileAmount, List<Path> answer) throws ExecutionException, InterruptedException {
        EnormousDirectorySearch searchTask = new EnormousDirectorySearch(PATH, fileAmount);

        List<Path> result = forkJoinPool.invoke(searchTask);

        assertEquals(answer, result);
    }


}
