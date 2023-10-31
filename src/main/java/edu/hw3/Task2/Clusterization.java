package edu.hw3.Task2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Clusterization {

    public final static String UNRECOGNISED = "Ваша строка содержит посторонние символы";
    private final static String UNBALANCED = "Ваша строка содержит несбалансированное сочетания";
    private static final Map<Character, Character> BRACKET_PAIRS = new HashMap<>();

    static {
        BRACKET_PAIRS.put('(', ')');
        BRACKET_PAIRS.put('[', ']');
        BRACKET_PAIRS.put('{', '}');
        BRACKET_PAIRS.put('<', '>');
    }

    public String[] clusterize(String input) {

        List<String> clusters = new LinkedList<>();

        if (isNotValid(input)) {
            return clusters.toArray(new String[0]);
        }

        Stack<Character> brackets = new Stack<>();
        StringBuilder currentCluster = new StringBuilder();

        for (char bracket : input.toCharArray()) {
            if (BRACKET_PAIRS.containsKey(bracket)) {
                currentCluster.append(bracket);
                brackets.push(bracket);
            } else if (BRACKET_PAIRS.containsValue(bracket)) {
                if ((!brackets.isEmpty()) && (BRACKET_PAIRS.get(brackets.pop()) == bracket)) {
                    currentCluster.append(bracket);
                } else {
                    throw new IllegalArgumentException(UNBALANCED);
                }
            } else {
                throw new IllegalArgumentException(UNRECOGNISED);
            }

            if (brackets.isEmpty()) {
                clusters.add(currentCluster.toString());
                currentCluster.delete(0, currentCluster.length());
            }

        }

        //не придумал без инверсии
        if (!brackets.isEmpty()) {
            throw new IllegalArgumentException(UNBALANCED);
        }

        return clusters.toArray(new String[0]);
    }

    private boolean isNotValid(String input) {
        return (input == null) || input.isEmpty();
    }

}
