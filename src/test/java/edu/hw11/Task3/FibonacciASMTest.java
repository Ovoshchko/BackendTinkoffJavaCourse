package edu.hw11.Task3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArgument;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciASMTest {

    @Test
    void fibTest()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> fibonacciASM = new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciASM")
            .defineMethod("fib", long.class, Modifier.PUBLIC).withParameters(int.class)
            .intercept(new Implementation() {

                @Override
                public ByteCodeAppender appender(Target target) {
                    return ((methodVisitor, context, methodDescription) -> {
                        Label l1 = new Label();

                        methodVisitor.visitCode();
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_2);
                        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, l1);
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.I2L);
                        methodVisitor.visitInsn(Opcodes.LRETURN);
                        methodVisitor.visitLabel(l1);
                        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_1);
                        methodVisitor.visitInsn(Opcodes.ISUB);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "FibonacciASM", "fib", "(I)J");
                        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_2);
                        methodVisitor.visitInsn(Opcodes.ISUB);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "FibonacciASM", "fib", "(I)J");
                        methodVisitor.visitInsn(Opcodes.LADD);
                        methodVisitor.visitInsn(Opcodes.LRETURN);
                        methodVisitor.visitEnd();
                        return new ByteCodeAppender.Size(5, 2);
                    });
                }

                @Override
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }
            })
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        assertEquals(fib(10), fibonacciASM.getDeclaredMethod("fib", int.class).invoke(fibonacciASM.getDeclaredConstructor().newInstance(), 10));
    }

    private long fib(int n) {
        if (n <= 1) {
            return n;
        }

        return fib(n - 1) + fib(n - 2);
    }
}
