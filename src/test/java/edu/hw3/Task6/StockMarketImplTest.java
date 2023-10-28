package edu.hw3.Task6;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockMarketImplTest {

    private final StockMarket stockM = new StockMarketImpl();

    @ParameterizedTest
    @DisplayName("--Valid add input test")
    @MethodSource("getValidAddInput")
    void validAddInputTest(Stock[] input, Queue<Stock> answer) {
        for (Stock stock: input) {
            stockM.add(stock);
        }
        assertArrayEquals(answer.toArray(), stockM.getStockMarket().toArray());
    }

    @ParameterizedTest
    @DisplayName("--Invalid add input test")
    @NullSource
    void validAddInputTest(Stock input) {
        assertThrows(NullPointerException.class, () -> stockM.remove(input));
    }

    @ParameterizedTest
    @DisplayName("--Get Max Value test")
    @MethodSource("getMaxStock")
    void getMaxStockTest(Stock[] input, Stock answer) {
        for (Stock current: input) {
            stockM.add(current);
        }
        assertEquals(answer, stockM.mostValuableStock());
    }

    @ParameterizedTest
    @DisplayName("--Valid delete input test")
    @MethodSource("getValidDeleteInput")
    void validDeleteInputTest(Stock[] toAdd, Stock[] input, Queue<Stock> answer) {
        for (Stock current: toAdd) {
            stockM.add(current);
        }
        for (Stock stock: input) {
            stockM.remove(stock);
        }
        assertArrayEquals(answer.toArray(), stockM.getStockMarket().toArray());
    }

    @ParameterizedTest
    @DisplayName("--Invalid delete input test")
    @NullSource
    void validDeleteInputTest(Stock input) {
        assertThrows(NullPointerException.class, () -> stockM.add(input));
    }

    private static Stream<Arguments> getValidAddInput() {
        return Stream.of(
            Arguments.of(
                new Stock[] {
                    new Stock("1", 1),
                    new Stock("2", 2),
                    new Stock("3456", 3456)
                },
                new PriorityQueue<>(List.of(
                    new Stock("3456", 3456),
                    new Stock("1", 1),
                    new Stock("2", 2)
                ))
            )
        );
    }

    private static Stream<Arguments> getMaxStock() {
        return Stream.of(
            Arguments.of(
                new Stock[] {
                    new Stock("1", 1),
                    new Stock("2", 2),
                    new Stock("3456", 3456)
                },
                new Stock("3456", 3456)
            )
        );
    }

    private static Stream<Arguments> getValidDeleteInput() {
        return Stream.of(
            Arguments.of(
                new Stock[] {
                    new Stock("1", 1),
                    new Stock("2", 2),
                    new Stock("3456", 3456),
                    new Stock("34", 34)
                },
                new Stock[] {
                    new Stock("1", 1),
                    new Stock("2", 2),
                    new Stock("3456", 3456)
                },
                new PriorityQueue<>(List.of(new Stock("34", 34)))
            )
        );
    }
}
