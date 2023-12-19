package edu.hw10.Task1;

import edu.hw10.Task1.RandomGenerator.DoubleRandomGenerator;
import edu.hw10.Task1.RandomGenerator.IntRandomGenerator;
import edu.hw10.Task1.RandomGenerator.RandomGenerator;
import edu.hw10.Task1.RandomGenerator.StringRandomGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ObjectGenerator {

    private final static Map<Class<?>, RandomGenerator<?>> GENERATORS = new HashMap<>();

    static {
        GENERATORS.put(int.class, new IntRandomGenerator());
        GENERATORS.put(String.class, new StringRandomGenerator());
        GENERATORS.put(double.class, new DoubleRandomGenerator());
    }

    public <T> T nextObject(Class<T> classObj)
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return getInstance(classObj, null);
    }

    public <T> T nextObject(Class<T> classObj, String methodFabric)
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return getInstance(classObj, methodFabric);
    }

    private <T> T getInstance(Class<T> classObj, String methodFabric)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        if (methodFabric != null && !methodFabric.isBlank()) {
            return getInstanceFabric(classObj, methodFabric);
        }

        Constructor<T> constructor = getMaxConstructor(classObj);
        constructor.setAccessible(true);
        Object[] arguments = setFields(constructor.getParameters());
        return classObj.cast(constructor.newInstance(arguments));
    }

    private <T> Constructor<T> getMaxConstructor(Class<T> classObj) {
        return  (Constructor<T>) Arrays.stream(classObj.getDeclaredConstructors())
            .max(Comparator.comparing(Constructor::getParameterCount))
            .orElseThrow(() -> new RuntimeException("No constructor was found"));
    }

    private <T> T getInstanceFabric(Class<T> classObj, String methodFabric)
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = classObj.getDeclaredMethod(methodFabric);
        method.setAccessible(true);
        return  (T) method.invoke(null);
    }

    private Object[] setFields(Parameter[] parameters) throws IllegalAccessException {
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < args.length; i++) {
            args[i] = GENERATORS.get(parameters[i].getType()).generate(parameters[i]);
        }

        return args;
    }
}
