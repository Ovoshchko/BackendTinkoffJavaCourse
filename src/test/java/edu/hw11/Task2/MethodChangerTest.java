package edu.hw11.Task2;

import edu.hw11.Task2.Model.ArithmeticUtils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodChangerTest {

    @Test
    void changeMethod() {
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(named("sum"))
            .intercept(MethodDelegation.to(Multiplication.class))
            .make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        ArithmeticUtils utils = new ArithmeticUtils();
        assertEquals(utils.sum(10, 35), 350);
        assertEquals(utils.sum(0, 35), 0);
    }

    public class Multiplication {
        @RuntimeType
        public static int multiply(int a, int b) {
            return a * b;
        }
    }
}
