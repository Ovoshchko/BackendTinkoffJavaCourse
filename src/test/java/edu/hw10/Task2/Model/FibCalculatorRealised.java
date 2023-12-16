package edu.hw10.Task2.Model;

public class FibCalculatorRealised implements FibCalculator {
    @Override
    public long fib(int number) {
        if (number == 1 || number == 0) {
            return number;
        }
        return fib(number - 1) + fib(number - 2);
    }
}
