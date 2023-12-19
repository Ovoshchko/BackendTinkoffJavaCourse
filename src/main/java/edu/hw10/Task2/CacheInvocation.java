package edu.hw10.Task2;

import edu.hw10.Task2.Annotations.Cache;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheInvocation implements InvocationHandler {

    private final static Map<Method, Map<Object[], Object>> DISC = new HashMap<>();
    private final static Logger LOGGER = LogManager.getLogger(CacheInvocation.class);
    private Object target;

    public CacheInvocation(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            if (method.getAnnotation(Cache.class).persist()) {
                return cacheToLocal(method, args);
            }
        }
        return method.invoke(target, args);
    }

    private Object cacheToLocal(Method method, Object[] args) {
        String log;
        var hash = Arrays.hashCode(args);

        if (!DISC.containsKey(method)) {
            DISC.put(method, new HashMap<>());
        }

        if (!DISC.get(method).containsKey(args)) {
            log = "New value - ";
            DISC.get(method).put(args, getMethodResult(method, args));
        } else {
            log = "Old value - ";
        }

        LOGGER.info(log + "Method: " + method.toString() + " Args: " + Arrays.stream(args).toList()
            + " Result: " + DISC.get(method).get(args));
        return DISC.get(method).get(args);
    }

    private Object getMethodResult(Method method, Object[] args) {
        try {
            return method.invoke(target, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Was not able to invoke method");
        }
    }
}
