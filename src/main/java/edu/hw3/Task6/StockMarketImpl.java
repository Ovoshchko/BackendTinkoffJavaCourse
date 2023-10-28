package edu.hw3.Task6;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class StockMarketImpl implements StockMarket {

    private final Queue<Stock> stockMarket = new PriorityQueue<>(
        Collections.reverseOrder(Comparator.comparingInt(Stock::value))
    );

    @Override
    public void add(Stock stock) {
        Objects.requireNonNull(stock);
        stockMarket.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        Objects.requireNonNull(stock);
        stockMarket.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockMarket.peek();
    }

    public Queue<Stock> getStockMarket() {
        return stockMarket;
    }
}
