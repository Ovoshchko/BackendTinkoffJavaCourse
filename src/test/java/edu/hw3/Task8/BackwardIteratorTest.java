package edu.hw3.Task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BackwardIteratorTest {

    @ParameterizedTest
    @DisplayName("--Valid lists input")
    @MethodSource("getValidLists")
    void validListsTest(List<?> list, List<?> answer) {
        BackwardIterator<?> iterator = new BackwardIterator<>(list);
        List<Object> result = new ArrayList<>();
        while(iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertArrayEquals(answer.toArray(), result.toArray());
    }

    @ParameterizedTest
    @DisplayName("--Invalid list test")
    @NullSource
    void invalidListTest(List<?> list) {
        assertThrows(NullPointerException.class, () -> new BackwardIterator<>(list));
    }

    private static Stream<Arguments> getValidLists() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7, 8)),
                new ArrayList<Integer>(List.of(8, 7, 6, 5, 4, 3, 2, 1))
            ),
            Arguments.of(
                new ArrayList<Character>(List.of('q', 'w', 'e', 'r', 't', 'y')),
                new ArrayList<Character>(List.of('y', 't', 'r', 'e', 'w', 'q'))
            ),
            Arguments.of(
                new LinkedList<String>(List.of("qwe", "asd", "zxc")),
                new LinkedList<String>(List.of("zxc", "asd", "qwe"))
            )
        );
    }
}
