package edu.hw3.Task4;

import java.util.Map;
import java.util.TreeMap;

public class RomanConverter {

    private final static TreeMap<Integer, String> romanMap = new TreeMap<>(){
        {
            put(1000, "M");
            put(900, "CM");
            put(500, "D");
            put(400, "CD");
            put(100, "C");
            put(90, "XC");
            put(50, "L");
            put(40, "XL");
            put(10, "X");
            put(9, "IX");
            put(5, "V");
            put(4, "IV");
            put(1, "I");
        }};

    public String convertToRoman(int arabic) {

        if (isNotValid(arabic)) {
            return "";
        }

        int copiedArabic = arabic;
        StringBuilder romanNumber = new StringBuilder();

        for (Map.Entry<Integer, String> entry: romanMap.descendingMap().entrySet()) {
            while (copiedArabic >= entry.getKey()) {
                copiedArabic -= entry.getKey();
                romanNumber.append(entry.getValue());
            }
        }

        return romanNumber.toString();
    }

    private static boolean isNotValid(int arabic) {
        return (arabic < 0) || (arabic > 3999);
    }
}
