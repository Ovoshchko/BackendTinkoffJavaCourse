package edu.hw10.Task2.Model;

import edu.hw10.Task2.Annotations.Cache;

public interface FibCalculator {

    @Cache(persist = true)
    public long fib(int number);
}
