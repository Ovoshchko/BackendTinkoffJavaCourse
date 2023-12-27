package edu.hw10.Task1.RandomGenerator;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class StringRandomGenerator implements RandomGenerator<String> {

    private final static int MAX_LENGTH = 10;
    private final static String CHARS = "ABCDEFGHIJKLMNOPKRSTUVWXYZabcdefghijklmnopkrstuvwxyz";
    private final static int CHARS_LENGTH = CHARS.length();

    @Override
    public String generate(Parameter parameter) {

        String generatedString = parameter.getAnnotation(NotNull.class) != null ? "" : null;
        int min = parameter.getAnnotation(Min.class) != null
            ? parameter.getAnnotation(Min.class).value() : 0;
        int max = parameter.getAnnotation(Max.class) != null
            ? parameter.getAnnotation(Max.class).value() : MAX_LENGTH;

        return fillString(generatedString, min, max);
    }

    private String fillString(String initialString, int min, int max) {
        int length = ThreadLocalRandom.current().nextInt(min, max);

        if (length <= 0) {
            return initialString;
        }

        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            newString.append(CHARS.charAt(ThreadLocalRandom.current().nextInt(CHARS_LENGTH)));
        }

        return newString.toString();
    }
}
