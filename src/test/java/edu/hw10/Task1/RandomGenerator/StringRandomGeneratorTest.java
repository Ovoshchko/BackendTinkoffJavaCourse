package edu.hw10.Task1.RandomGenerator;

import edu.hw10.Task1.Model.MyClass;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import static org.junit.jupiter.api.Assertions.*;

class StringRandomGeneratorTest {

    private final static StringRandomGenerator generator = new StringRandomGenerator();

    @Test
    void generate() {
        Constructor<MyClass> myClassConstructor = (Constructor<MyClass>) MyClass.class.getConstructors()[0];
        Parameter parameter = myClassConstructor.getParameters()[2];
        String answer = generator.generate(parameter);
        assertEquals(answer.length(), MyClass.getBorder());
    }
}
