package edu.hw10.Task2;

import edu.hw10.Task2.Model.FibCalculator;
import edu.hw10.Task2.Model.FibCalculatorRealised;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheProxyTest {

    @Test
    void create() {
        FibCalculator calculator = CacheProxy.create(new FibCalculatorRealised(), FibCalculatorRealised.class);
        long result = calculator.fib(20);
        long cached = calculator.fib(20);
        assertEquals(result, cached);
    }
}
