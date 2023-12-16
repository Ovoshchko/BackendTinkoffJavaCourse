package edu.hw10.Task1;

import edu.hw10.Task1.Model.MyClass;
import edu.hw10.Task1.Model.MyRecord;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;

class ObjectGeneratorTest {

    private final static ObjectGenerator GENERATOR = new ObjectGenerator();

    @RepeatedTest(20)
    void nextObject() {
        try {
            MyClass myClass = GENERATOR.nextObject(MyClass.class);
            MyRecord myRecord = GENERATOR.nextObject(MyRecord.class);
            assertTrue((myClass.getADouble() >= -MyClass.getBorder()) && (myClass.getADouble() <= MyClass.getBorder()));
            assertTrue((myClass.getAnInt() >= -MyClass.getBorder()) && (myClass.getAnInt() <= MyClass.getBorder()));
            assertEquals(myClass.getAString().length(), MyClass.getBorder());
            assertTrue(myRecord.anInt() >= 0);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            fail();
        }
    }

    @Test
    void fabricNextObject() {
        MyClass myClass = null;
        try {
            myClass = GENERATOR.nextObject(MyClass.class, "create");
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            fail();
        }
        assertEquals(new MyClass(1, 1.0, "1"), myClass);
    }
}
