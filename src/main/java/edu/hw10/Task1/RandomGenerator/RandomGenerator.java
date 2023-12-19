package edu.hw10.Task1.RandomGenerator;

import java.lang.reflect.Parameter;

public interface RandomGenerator<T> {

    T generate(Parameter parameter);
}

