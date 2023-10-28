package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BackwardIterator<T> implements Iterator {

    private final List<T> list;
    private int currentIndex;
    private final static String NO_ELEMENT = "No element found";

    public BackwardIterator(List<T> list) {
        Objects.requireNonNull(list);
        this.list = list.reversed();
        currentIndex = 0;
    }
    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException(NO_ELEMENT);
        }
        return list.get(currentIndex++);
    }
}
