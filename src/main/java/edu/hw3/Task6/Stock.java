package edu.hw3.Task6;

//Акция конечно должна иметь обновляемую цену, но в рамках задания пусть будет рекорд
public record Stock(String name, Integer value) implements Comparable<Stock> {

    @Override
    public int compareTo(Stock other) {
        return Integer.compare(other.value(), this.value());
    }

}
