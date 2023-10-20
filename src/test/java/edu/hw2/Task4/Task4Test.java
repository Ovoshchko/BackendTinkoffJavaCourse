package edu.hw2.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for Task 4")
public class Task4Test {

    @Nested
    class FirstTestClass {

        @Test
        @DisplayName("--Calling from FirstTestClass.getCallingInfo")
        void getCallingInfo() {
            CallingInfo callInfo = StackCallInfo.getLastStackInfo();
            assertEquals(FirstTestClass.class.getName(), callInfo.className());
            assertEquals("getCallingInfo", callInfo.methodName());
        }
    }
}
