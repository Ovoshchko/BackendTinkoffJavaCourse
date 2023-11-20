package edu.hw6.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class HackerNewsTest {

    private final HackerNews hackerNews = new HackerNews();

    //А как здесь написать тест? Заглушку на адрес, где всегда лежат одни и те же значения?
    @Test
    void hackerNewsTopStories() {
        long[] topNews = hackerNews.hackerNewsTopStories();

        for (long news: topNews) {
            assertTrue(news > 0);
        }
    }

    @ParameterizedTest
    @DisplayName("--Certain news Test")
    @MethodSource("provideCertainNewsTitle")
    void news(long id, String answer) {
        assertEquals(answer, hackerNews.news(id));
    }

    private static Stream<Arguments> provideCertainNewsTitle() {
        return Stream.of(
            Arguments.of(37570037, "JDK 21 Release Notes"),
            Arguments.of(4567876, "The Saddest Story in the History of Computer Science")
        );
    }
}
