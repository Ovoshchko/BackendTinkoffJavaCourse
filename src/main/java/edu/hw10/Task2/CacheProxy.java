package edu.hw10.Task2;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private CacheProxy() {}

    public static <T> T create(T object, Class<T> classObj) {
        return (T) Proxy.newProxyInstance(
            classObj.getClassLoader(),
            classObj.getInterfaces(),
            new CacheInvocation(object)
        );
    }
}
