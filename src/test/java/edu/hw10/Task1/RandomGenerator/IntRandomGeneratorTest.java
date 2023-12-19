package edu.hw10.Task1.RandomGenerator;

import edu.hw10.Task1.Model.MyClass;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import static org.junit.jupiter.api.Assertions.*;

class IntRandomGeneratorTest {

    private final static IntRandomGenerator generator = new IntRandomGenerator();

    @Test
    void generate() {
        Constructor<MyClass> myClassConstructor = (Constructor<MyClass>) MyClass.class.getConstructors()[0];
        Parameter parameter = myClassConstructor.getParameters()[0];
        int answer = generator.generate(parameter);
        assertTrue((answer >= -MyClass.getBorder()) && (answer <= MyClass.getBorder()));
    }
}
