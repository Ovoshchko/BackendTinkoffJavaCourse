package edu.hw3.Task8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BackwardIterator<T> implements Iterator {

    private final static String NO_ELEMENT = "No element found";
    private final List<T> list;
    private int current;

    public BackwardIterator(List<T> list) {
        Objects.requireNonNull(list);
        this.list = new ArrayList<>(list.reversed());
        current = 0;
    }

    @Override
    public boolean hasNext() {
        return list.size() > current;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException(NO_ELEMENT);
        }
        return list.get(current++);
    }
}
