package edu.hw3.Task6;

import java.util.Queue;

public interface StockMarket {
    void add(Stock stock);

    void remove(Stock stock);

    Stock mostValuableStock();

    Queue<Stock> getStockMarket();
}
