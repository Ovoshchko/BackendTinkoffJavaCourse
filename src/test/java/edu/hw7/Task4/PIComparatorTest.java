package edu.hw7.Task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PIComparatorTest {

    @Test
    void compare() {
        PIComparator comparator = new PIComparator();
        String report = comparator.compare(10000);

        assertFalse(report.isEmpty());
    }
}
