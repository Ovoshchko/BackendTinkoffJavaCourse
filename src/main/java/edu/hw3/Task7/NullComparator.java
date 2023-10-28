package edu.hw3.Task7;

import java.util.Comparator;

public class NullComparator {

    private final Comparator<String> comparator;

    public NullComparator() {
        comparator = Comparator.nullsFirst(Comparator.naturalOrder());
    }
    public Comparator<String> getComparator() {
        return comparator;
    }
}
