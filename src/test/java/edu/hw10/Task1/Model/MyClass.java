package edu.hw10.Task1.Model;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;
import edu.hw10.Task1.ObjectGenerator;
import lombok.Getter;

@Getter
public class MyClass {

    private final static int BORDER = 1;
    private int anInt;
    private double aDouble;
    private String aString;

    public MyClass(@Min(-BORDER) @Max(BORDER) int anInt, @Min(-BORDER) @Max(BORDER) double aDouble, @NotNull @Min(BORDER) @Max(BORDER+1) String aString) {
        this.anInt = anInt;
        this.aDouble = aDouble;
        this.aString = aString;
    }

    public static MyClass create() {
        return new MyClass(1, 1.0, "1");
    }

    public static int getBorder() {
        return BORDER;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        MyClass comparableO = (MyClass) o;

        return (this.aDouble == comparableO.aDouble) && (this.anInt == comparableO.anInt)
            && (this.aString.equals(comparableO.aString));
    }
}
