package edu.hw2.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Task1 Constant Number Test")
public class Task1ConstantTest {

    @ParameterizedTest
    @ValueSource(doubles = {1, 345, 234.4, -4})
    void constantTest(double input) {
        Constant constant = new Constant(input);
        assertEquals(input, constant.evaluate());
    }
}
